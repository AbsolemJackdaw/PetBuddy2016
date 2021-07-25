package subaraki.petbuddy.capability;

import java.util.Collection;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.NameTagItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.petbuddy.api.PetForms;
import subaraki.petbuddy.client.ClientReferences;
import subaraki.petbuddy.mod.ConfigHandler;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.client.CPacketSyncArmorSlots;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BuddyData {

    private PetBuddyEntity buddy;
    private PlayerEntity player;
    private boolean hasBuddyOut = false;
    private int respawnCoolDown = 0;
    private CompoundNBT buddyNBT = new CompoundNBT();

    public static LazyOptional<BuddyData> get(PlayerEntity player)
    {

        return player.getCapability(BuddyCapability.CAPABILITY, null);
    }

    public INBT writeData()
    {

        if (buddy == null)
        {
            buddy = new PetBuddyEntity(PetBuddy.ENTITY_PETBUDDY_TYPE.get(), player.level);
            buddy.setOwnerUUID(player.getUUID());
        }

        CompoundNBT tag = buddyNBT;
        tag.putBoolean("data_hasPetOut", hasBuddyOut);
        tag.putInt("cooldown", respawnCoolDown);

        return tag;
    }

    public void readData(INBT nbt)
    {

        CompoundNBT tag = (CompoundNBT) nbt;

        if (buddy == null)
        {
            buddy = new PetBuddyEntity(PetBuddy.ENTITY_PETBUDDY_TYPE.get(), player.level);
            buddy.setOwnerUUID(player.getUUID());
        }

        buddy.load(tag);
        buddyNBT = tag;

        hasBuddyOut = tag.getBoolean("data_hasPetOut");
        respawnCoolDown = tag.getInt("cooldown");
    }

    public void setPlayer(PlayerEntity player)
    {

        this.player = player;
    }

    public void summonBuddy(ServerWorld world, ServerPlayerEntity player)
    {

        if (respawnCoolDown > 0)
        {
            int minutes = respawnCoolDown / 20 / 60;
            int seconds = respawnCoolDown / 20 % 60;
            String time = minutes + ":" + seconds;
            player.sendMessage(new TranslationTextComponent("buddy.recovering").append(time), player.getUUID());
            return;
        }

        if (!hasBuddyOut)
        {
            buddy = new PetBuddyEntity(PetBuddy.ENTITY_PETBUDDY_TYPE.get(), player.level);
            buddy.setOwnerUUID(player.getUUID());
            buddy.load(buddyNBT);

            buddy.moveTo(player.position().x, player.position().y, player.position().z, -player.yRot, 0.0F);
            world.addFreshEntity(buddy);
            world.sendParticles(ParticleTypes.HEART, this.buddy.position().x(), buddy.position().y() + 0.5d, buddy.position().z(), 1, 0.0D, 0.0D, 0.0D, 0.0D);

            // update armor client side

            BuddyData.get(player).ifPresent((data) -> {
                for (int slot = 0; slot < 3; slot++)
                {
                    NetworkHandler.NETWORK.send(PacketDistributor.PLAYER.with(() -> player),
                            new CPacketSyncArmorSlots(getPetInventory().getStackInSlot(slot), slot, buddy.getId()));
                    reinstateGoals(slot);

                }
            });

            if (!buddy.hasCustomName())
                buddy.setCustomName(new StringTextComponent(player.getGameProfile().getName()));

        }
        else
        {
            // write to empty nbt to prevent old stuff from staying !
            buddyNBT = new CompoundNBT();
            buddy.save(buddyNBT);
            buddy.remove();
            world.sendParticles(ParticleTypes.ANGRY_VILLAGER, this.buddy.position().x(), buddy.position().y() + 0.5d, buddy.position().z(), 1, 0.0D, 0.0D, 0.0D,
                    0.0D);
        }

        world.sendParticles(ParticleTypes.POOF, this.buddy.position().x(), buddy.position().y() + 0.5d, buddy.position().z(), 10, 0.0D, 0.0D, 0.0D, 0.0D);

        hasBuddyOut = !hasBuddyOut;

    }

    public void reinstateGoals(int slot)
    {

        ItemStack equipment = getPetInventory().getStackInSlot(slot);

        if (!equipment.isEmpty())
        {
            if (slot == 0)
            {
                for (AttributeModifier mod : buddy.getAttribute(Attributes.ARMOR).getModifiers())
                {
                    if (mod.getName().equals("held_armor"))
                    {
                        buddy.getAttribute(Attributes.ARMOR).removeModifier(mod);
                        break;
                    }
                }
                if (equipment.getItem() instanceof ArmorItem)
                    buddy.getAttribute(Attributes.ARMOR).addTransientModifier(
                            new AttributeModifier("held_armor", ((ArmorItem) equipment.getItem()).getDefense() * 1.75D, Operation.MULTIPLY_BASE));
            }
            if (slot == 1)
            {
                buddy.targetSelector.addGoal(5, buddy.attack_owner_target);

                for (AttributeModifier mod : buddy.getAttribute(Attributes.ATTACK_DAMAGE).getModifiers())
                {
                    if (mod.getName().equals("held_weapon"))
                    {
                        buddy.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(mod);
                        break;
                    }
                }
                if (equipment.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE))
                {
                    Collection<AttributeModifier> o = equipment.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(Attributes.ATTACK_DAMAGE);

                    for (AttributeModifier am : o)
                        buddy.getAttribute(Attributes.ATTACK_DAMAGE)
                                .addTransientModifier(new AttributeModifier("held_weapon", am.getAmount(), Operation.ADDITION));

                }
            }
        }
        else
            if (slot == 1)
                buddy.targetSelector.removeGoal(buddy.attack_owner_target);

    }

    public boolean hasBuddyOut()
    {

        return hasBuddyOut;
    }

    public void removeBuddyFromWorld()
    {

        if (buddy != null)
        {
            buddyNBT = new CompoundNBT();
            buddy.save(buddyNBT);
            buddy.remove();
        }

        this.hasBuddyOut = false;
    }

    public void onBuddyKilled()
    {

        buddy.setHealth(buddy.getMaxHealth());

        buddy.clearFire();
        buddy.fallDistance = 0;
        buddy.setAirSupply(buddy.getMaxAirSupply());
        buddy.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

        buddy.goalSelector.getRunningGoals().filter((goal) -> {
            return goal.isRunning();

        }).forEach(Goal::stop);

        buddyNBT = new CompoundNBT();
        buddy.save(buddyNBT);
        this.hasBuddyOut = false;
        respawnCoolDown = ConfigHandler.timer * 20;
    }

    public void coolDown()
    {

        if (respawnCoolDown > 0)
            respawnCoolDown--;
    }

    @OnlyIn(Dist.CLIENT)
    public void setClientArmorSlot(ItemStack equipment, int slot, int entityID)
    {

        Entity e = ClientReferences.getClientWorld().getEntity(entityID);

        if (e instanceof PetBuddyEntity)
        {
            buddy = (PetBuddyEntity) e;
            if (slot == 0)
            {
                buddy.setItemSlot(EquipmentSlotType.HEAD, equipment);

            }
            if (slot == 1)
            {
                buddy.setItemSlot(EquipmentSlotType.MAINHAND, equipment);
            }
            
            buddy.getInventory().setStackInSlot(slot, equipment);
        }

    }

    public void syncPetName(String name)
    {

        if (name != null && name.length() > 0)
            buddy.setCustomName(new StringTextComponent(name));
    }

    public void setPetForm(ItemStack stack)
    {

        if (stack.isEmpty())
        {
            buddy.setPetForm(PetForms.DEFAULT);
            setSkinForm("");
        }
        else
        {
            buddy.setPetForm(PetForms.getFormFromItem(stack.getItem()));
            if (stack.getItem() instanceof NameTagItem)
                setSkinForm(stack.getHoverName().getString());
        }

    }

    public ItemStackHandler getPetInventory()
    {

        if (buddy != null)
            return buddy.getInventory();
        return new ItemStackHandler(16);
    }

    public void setSkinForm(String skinFormName)
    {

        if (skinFormName.isEmpty())
            buddy.skinForm = null;
        else
            buddy.skinForm = SkullTileEntity.updateGameprofile(new GameProfile((UUID) null, skinFormName));
    }

    public int getBuddyID()
    {

        return buddy.getId();
    }
}
