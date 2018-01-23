package subaraki.petbuddy.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.PetForm.EnumPetform;
import subaraki.petbuddy.mod.PetBuddy;

public class EntityPetBuddy extends EntityTameable {

	private EntityPlayer owner;

	private EntityAIAttackMelee AIAttack = new EntityAIAttackMelee(this, 0.3F, true);
	private EntityAIOwnerHurtByTarget AIOwnerHurt = new EntityAIOwnerHurtByTarget(this);
	private EntityAIOwnerHurtTarget AIOwnerCommands = new EntityAIOwnerHurtTarget(this);
	private EntityAIHurtByTarget AIHurtBy = new EntityAIHurtByTarget(this, true, new Class[0]);

	protected static final DataParameter<String> SKINNED = EntityDataManager.<String> createKey(EntityPetBuddy.class,
			DataSerializers.STRING);
	protected static final DataParameter<Boolean> UPDATE = EntityDataManager.<Boolean> createKey(EntityPetBuddy.class,
			DataSerializers.BOOLEAN);

	public ResourceLocation FRIENDSKIN = PetBuddy.proxy.getFriendSkin();

	public EntityPetBuddy(World world) {
		super(world);
		this.setSize(0.5F, 0.7f);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 0.3F, 7.0F, 2.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 5.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	///// INITS/////
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SKINNED, "");
		this.dataManager.register(UPDATE, false);

	}

	///// AI AND INVENTORY RELATED//////
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	private void initAttackTasks() {
		tasks.addTask(2, AIAttack);
		this.targetTasks.addTask(1, AIOwnerHurt);
		this.targetTasks.addTask(2, AIHurtBy);
		this.targetTasks.addTask(3, AIOwnerCommands);
	}

	private void removeAttackTasks() {
		tasks.removeTask(AIAttack);
		this.targetTasks.removeTask(AIOwnerHurt);
		this.targetTasks.removeTask(AIHurtBy);
		this.targetTasks.removeTask(AIOwnerCommands);
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		super.setItemStackToSlot(slotIn, stack);
		switch (slotIn) {
		case MAINHAND:
			if (stack.isEmpty())
				removeAttackTasks();
			else
				initAttackTasks();
			break;
		default:
			break;
		}
	}

	///// ATTACK LOGIC/////
	@Override
	public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase player) {
		if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
			if (target instanceof EntityTameable) {
				EntityTameable buddy = (EntityTameable) target;
				if (buddy.getOwner() == player)
					return false;
			}
			return target instanceof EntityPlayer && player instanceof EntityPlayer
					&& !((EntityPlayer) player).canAttackPlayer((EntityPlayer) target) ? false
							: !(target instanceof EntityHorse) || !((EntityHorse) target).isTame();
		} else
			return false;
	}

	@Override
	public boolean attackEntityAsMob(Entity victim) {
		boolean flag;

		//closes #9
		if (!this.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
			if(!(this.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword))
				return victim.attackEntityFrom(DamageSource.causeMobDamage(this), 2);

			float damage = (((ItemSword) (this.getHeldItemMainhand().getItem())).getDamageVsEntity() + 3.0f);
			flag = victim.attackEntityFrom(DamageSource.causeMobDamage(this), damage / 2f); // cannot
			// retrieve attack damage, which is set as 3+material.damage vs entity
			if (flag && getMaster() != null) {
				victim.attackEntityFrom(DamageSource.causePlayerDamage(getMaster()), damage / 2f);
				getHeldItemMainhand().damageItem(1, this);
				getMaster().getCapability(PetInventoryCapability.CAPABILITY, null).setStackInSlot(13,
						getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
			}
			return flag;
		}

		return victim.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		Integer petID = null;

		//solves buddy dying from friendly fire
		if(source.getTrueSource() instanceof EntityPlayer)
			petID = PetInventory.get((EntityPlayer)source.getTrueSource()).getPetID();
			if(petID != null && petID.intValue() == getEntityId())
				return false;
		if(source.getTrueSource() instanceof EntityTameable)
			if(((EntityTameable)source.getTrueSource()).getOwnerId().equals(this.getOwnerId()))
				return false;

		// health before attack
		int armorHealth = getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() ? 0
				: getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItemDamage();
		boolean isattacked = super.attackEntityFrom(source, amount);
		// health after attack
		int armorHealth2 = getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() ? 0
				: getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItemDamage();

		if (armorHealth2 < armorHealth)
			if (getMaster() != null)
				getMaster().getCapability(PetInventoryCapability.CAPABILITY, null).setStackInSlot(12,
						getItemStackFromSlot(EntityEquipmentSlot.HEAD).copy());
		return isattacked;
	}

	@Override
	protected void damageArmor(float damage) {
		float theDamage = damage / 4f < 1f ? 1f : damage / 4f;
		if (!getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
			getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int) theDamage, this);

			if (getItemStackFromSlot(EntityEquipmentSlot.HEAD).getCount() == 0) {
				setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {

		ItemStack stack = player.getHeldItem(hand);

		if (!player.equals(getMaster()))
			return false;

		if (!stack.isEmpty())
			if (stack.getItem().equals(Items.SADDLE)) {
				if (!this.isRiding()) {
					this.startRiding(player);
					return true;
				} else {
					this.dismountRidingEntity();
					return true;
				}
			} else if (stack.getItem().equals(Items.NAME_TAG)) {
				if (stack.hasDisplayName())
					setCustomNameTag(stack.getDisplayName());
				// return true so the item does not get consumed. setting the
				// name is done above
				return true;
			} else if (stack.getItem() instanceof ItemFood) {
				if (getHealth() < getMaxHealth()) {
					heal(((ItemFood) stack.getItem()).getHealAmount(stack));
					stack.shrink(1);
					if (stack.getCount() == 0)
						stack = ItemStack.EMPTY;
					if (world.isRemote)
						world.spawnParticle(EnumParticleTypes.HEART, posX, posY + 0.5f, posZ, 0, 0, 0, new int[0]);
				}
				return true;
			}

		if (this.isRiding()) {
			this.dismountRidingEntity();
			return true;
		}

		if (!player.getCapability(PetInventoryCapability.CAPABILITY, null).canAccesStorage()) {
			if (!stack.isEmpty() && stack.getItem() instanceof ItemBlock)
				if (Block.getBlockFromItem(stack.getItem()).equals(Blocks.CHEST)
						|| Block.getBlockFromItem(stack.getItem()).equals(Blocks.TRAPPED_CHEST)) {
					player.getCapability(PetInventoryCapability.CAPABILITY, null).setHoldingChest();
					player.getHeldItem(hand).shrink(1);
					return true;
				}
		} else {
			getNavigator().setPath(getNavigator().getPathToEntityLiving(getOwner()), 1.0);
			FMLNetworkHandler.openGui(player, PetBuddy.instance, 0, world, (int) player.posX, (int) player.posY,
					(int) player.posZ);
			return true;
		}

		return true;
	}

	@Override
	public int getTotalArmorValue() {
		if (!getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
			ItemStack helm = getItemStackFromSlot(EntityEquipmentSlot.HEAD);

			if(helm.getItem() instanceof ItemArmor)
			{
				int armorValue = ((ItemArmor) helm.getItem()).damageReduceAmount * 8;
				return armorValue;
			}
		}
		return super.getTotalArmorValue();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getMaster() != null) {
			if (!world.isRemote && (PetInventory.get(getMaster()).getPetID() == null
					|| (int) PetInventory.get(getMaster()).getPetID() != getEntityId()))
				setDead();
		} else
			setDead();

	}

	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (getMaster() != null) {
			getMaster().getCapability(PetInventoryCapability.CAPABILITY, null).setCooldown(2500 + rand.nextInt(15000));
			getMaster().getCapability(PetInventoryCapability.CAPABILITY, null).setPetID((Integer) null);
		}
	}

	@Override
	public void setCustomNameTag(String name) {
		if (getMaster() != null)
			getMaster().getCapability(PetInventoryCapability.CAPABILITY, null).setPetName(name);
		super.setCustomNameTag(name);
	}

	@Override
	protected boolean canDropLoot() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return this.isRiding() ? false : true; // pet must despawn when too far
		// away
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	// @Override
	// public boolean isAIDisabled() {
	// return false;
	// }

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return false;
	}

	////////////////////////////////////////////////////////////
	/////////////// PET BUDDY UNIQUE SPECIFICS///////////////////
	////////////////////////////////////////////////////////////

	private EntityPlayer getMaster() {
		return getOwner() instanceof EntityPlayer ? (EntityPlayer) getOwner() : null;
	}

	public EnumPetform getForm() {
		return PetForm.getFormFromItem(getStackDefiningForm());
	}

	public ItemStack getStackDefiningForm() {
		EntityLivingBase owner = getOwner();
		if (owner instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) owner;
			return player.getCapability(PetInventoryCapability.CAPABILITY, null).getInventoryHandler()
					.getStackInSlot(14);
		}
		return ItemStack.EMPTY;
	}

	private static final EntitySheep dyePlaceHolder = new EntitySheep(null);

	public float[] getColorFromDye(EnumDyeColor dye) {
		return dyePlaceHolder.getDyeRgb(dye);
	}

	private int index;

	public int setIndex(int lenght) {
		if (lenght > 0)
			index = rand.nextInt(lenght);
		else
			index = 0;

		return index;
	}

	public void forceIndex(int i){
		index = i;
		setForceRender(true); /*needs to be forced to rerender so the model fits with the skin. most cases for zombies*/
	}

	public int getTextureIndex() {
		return index;
	}

	private String modeltype = "default";

	public String getModelType() {
		return modeltype;
	}

	public void setModelType(String type) {
		modeltype = type;
	}

	public void setNameForNameTag(String s) {
		dataManager.set(SKINNED, s);
	}

	public String getNameFromTag() {
		return dataManager.get(SKINNED);
	}

	public boolean shouldForceRenderUpdate() {
		return dataManager.get(UPDATE);
	}

	public void setForceRender(boolean flag) {
		dataManager.set(UPDATE, flag);
	}
}
