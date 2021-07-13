package subaraki.petbuddy.server.entity;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.PetForms;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.client.ClientReferences;
import subaraki.petbuddy.mod.ConfigHandler;
import subaraki.petbuddy.server.inventory.PetBuddyContainer;
import subaraki.petbuddy.server.inventory.PetBuddyInventory;

public class PetBuddyEntity extends TameableEntity {

    private PetBuddyInventory inventory;
    public GameProfile skinForm = null;
    private GameProfile ownerSkin = null;

    public Goal attack_owner_target = new OwnerHurtTargetGoal(this);

    @OnlyIn(Dist.CLIENT)
    private IPetFormBase petForm;

    public PetBuddyEntity(EntityType<? extends TameableEntity> type, World world) {

        super(type, world);
        this.setHealth(this.getMaxHealth());

        inventory = new PetBuddyInventory(this);

    }

    @Override
    public boolean wantsToAttack(LivingEntity p_142018_1_, LivingEntity p_142018_2_)
    {

        if (!(p_142018_1_ instanceof CreeperEntity) && !(p_142018_1_ instanceof GhastEntity))
        {
            if (p_142018_1_ instanceof WolfEntity)
            {
                WolfEntity wolfentity = (WolfEntity) p_142018_1_;
                return !wolfentity.isTame() || wolfentity.getOwner() != p_142018_2_;
            }
            else
                if (p_142018_1_ instanceof PlayerEntity && p_142018_2_ instanceof PlayerEntity
                        && !((PlayerEntity) p_142018_2_).canHarmPlayer((PlayerEntity) p_142018_1_))
                {
                    return false;
                }
                else
                    if (p_142018_1_ instanceof AbstractHorseEntity && ((AbstractHorseEntity) p_142018_1_).isTamed())
                    {
                        return false;
                    }
                    else
                    {
                        return !(p_142018_1_ instanceof TameableEntity) || !((TameableEntity) p_142018_1_).isTame();
                    }
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void registerGoals()
    {

        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 3.0F, 2.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));

        this.goalSelector.addGoal(5, new PanicGoal(this, 1.0));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));

        if (ConfigHandler.allowAttackProvoked)
            this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));

    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {

        ItemStack heldItem = player.getItemInHand(hand);
        if (!heldItem.isEmpty() && this.getHealth() < this.getMaxHealth())
        {
            if (heldItem.getItem().getFoodProperties() != null)
            {
                this.heal(heldItem.getItem().getFoodProperties().getNutrition());
                if (heldItem.hasContainerItem())
                    player.inventory.add(heldItem.getContainerItem());
                player.getItemInHand(hand).grow(-1);
                level.playSound(player, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundCategory.PLAYERS, 0.2f + this.random.nextFloat() * 0.75f,
                        0.2f + this.random.nextFloat() * 0.75f);
                if (level.isClientSide())
                {
                    for (int i = 0; i < 10; i++)
                        level.addParticle(i % 5 == 0 ? ParticleTypes.HEART : ParticleTypes.HAPPY_VILLAGER, this.position().x() + this.random.nextFloat() - 0.5f,
                                this.position().y() + 0.5d + this.random.nextFloat() - 0.5f, this.position().z() + this.random.nextFloat() - 0.5f, 0, 0, 0);
                }
            }

            return ActionResultType.CONSUME;
        }
        else
            if (player.getUUID().equals(getOwnerUUID()) && !level.isClientSide)
                if (player instanceof ServerPlayerEntity)
                {
                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                    IContainerProvider provider = PetBuddyContainer.getServerContainerProvider();
                    INamedContainerProvider namedProvider = new SimpleNamedContainerProvider(provider, PetBuddyContainer.TITLE);
                    NetworkHooks.openGui(serverPlayer, namedProvider);
                    return ActionResultType.SUCCESS;
                }

        return super.mobInteract(player, hand);
    }

    @Override
    protected void hurtArmor(DamageSource p_230294_1_, float p_230294_2_)
    {

        super.hurtArmor(p_230294_1_, p_230294_2_);
        if (!level.isClientSide())
            if (!getInventory().getStackInSlot(0).isEmpty())
                getInventory().getStackInSlot(0).hurt(random.nextInt(2), random, null);
    }

    @Override
    public void swing(Hand p_184609_1_)
    {

        super.swing(p_184609_1_);
        if (!level.isClientSide())
            getInventory().getStackInSlot(1).hurt(1, random, null);
    }

    @Override
    public void aiStep()
    {

        super.aiStep();
        updateSwingTime();
    }

    @Override
    protected void customServerAiStep()
    {

        super.customServerAiStep();
        if (getOwnerUUID() != null)
        {
            PlayerEntity owner = level.getPlayerByUUID(getOwnerUUID());
            if (owner != null)
            {
                if (this.isInWater())
                    this.setPose(Pose.SWIMMING);
                else
                    if (owner.isCrouching())
                        this.setPose(Pose.CROUCHING);
                    else
                        this.setPose(Pose.STANDING);
            }
        }

    }

    @Override
    public void tick()
    {

        super.tick();

        if (level.isClientSide())
        {
            getPetForm().tick(this);
            return;
        }

        if (!getInventory().getStackInSlot(0).isEmpty())
        {
            if (getInventory().getStackInSlot(0).getItem().equals(Items.TURTLE_HELMET))
                this.setAirSupply(this.getMaxAirSupply());
        }

        // if the owner can't be found or the pet has no owner.
        if (getOwnerUUID() == null)
            this.remove();

        // if the pet has an owner, but the owner stowed the pet, or the owner isn't in
        // the world, despawn entity
        // this is a check for logged out players. pet stowing happens on key press
        if (getOwnerUUID() != null)
        {
            PlayerEntity owner = level.getPlayerByUUID(getOwnerUUID());
            if (owner != null)
            {
                BuddyData.get(owner).ifPresent((data) -> {
                    if (!data.hasBuddyOut())
                        data.removeBuddyFromWorld();
                });

            }
            else
            {
                remove();
            }

        }

    }

    @Override
    public void die(DamageSource p_70645_1_)
    {

        if (!level.isClientSide())
        {
            if (level.getPlayerByUUID(getOwnerUUID()) != null)
            {
                BuddyData.get(level.getPlayerByUUID(getOwnerUUID())).ifPresent((data) -> {
                    data.onBuddyKilled();
                });
            }
        }
        super.die(p_70645_1_);
    }

    @Override
    public void load(CompoundNBT nbt)
    {

        inventory.deserializeNBT(nbt);
        super.load(nbt);
    }

    @Override
    public boolean save(CompoundNBT nbt)
    {

        return super.save(nbt.merge(inventory.serializeNBT()));
    }

    @Override
    public ITextComponent getCustomName()
    {

        return super.getCustomName();
    }

    @OnlyIn(Dist.CLIENT)
    public GameProfile getClientOwnerSkin()
    {

        if (ownerSkin == null)
        {
            if (getOwnerUUID() != null)
            {
                PlayerEntity owner = ClientReferences.getClientWorld().getPlayerByUUID(getOwnerUUID());
                ownerSkin = SkullTileEntity.updateGameprofile(owner.getGameProfile());
            }
        }

        return ownerSkin;
    }

    @OnlyIn(Dist.CLIENT)
    public IPetFormBase getPetForm()
    {

        return petForm == null ? PetForms.DEFAULT : petForm;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPetForm(IPetFormBase petForm)
    {

        this.petForm = petForm;
    }

    @Override
    public boolean isTame()
    {

        return true;
    }

    @Override
    public boolean isBaby()
    {

        return false;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
    {

        return null;
    }

    @Override
    protected boolean shouldDropExperience()
    {

        return false;
    }

    public ItemStackHandler getInventory()
    {

        return inventory;
    }

    public GameProfile getSkinForm()
    {

        return skinForm;
    }
}
