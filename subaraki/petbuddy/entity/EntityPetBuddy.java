package subaraki.petbuddy.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.mod.PetBuddy;

public class EntityPetBuddy extends EntityTameable {

	private EntityPlayer owner;
	boolean invulnerable = false;

	public EntityPetBuddy(World world) {
		super(world);
		this.setSize(0.5F, 0.7f);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.3F, true));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 0.3F, 10.0F, 2.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6,new EntityAIWatchClosest(this, Entity.class, 5.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this,EntityLiving.class, 0, true, false, new Predicate<Entity>()
		{
			public boolean apply(@Nullable Entity target)
			{
				if(target instanceof EntityTameable)
					if(((EntityTameable)target).getOwner() != null && ((EntityTameable)target).getOwner().equals(getOwner()))
						return false;
					else
						return true;
				if(target instanceof IMob)
					return true;
				if(target instanceof EntityPlayer)
					return target.equals(owner) ? false : true;
				return false;
			}
		}));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
	}
	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
		if(!player.equals(getMaster()))
			return false;

		if(stack != null && stack.getItem() != null)
			if(stack.getItem().equals(Items.SADDLE)){
				if(!this.isRiding()){
					this.startRiding(player);
					return true;
				}else{
					this.dismountRidingEntity();
					return true;
				}
			}
			else if (stack.getItem().equals(Items.NAME_TAG)){
				//naming is handled by tag itself. only here to prevent gui from opening
				return false;
			}

		if(this.isRiding()){
			this.dismountRidingEntity();
			return true;
		}

		if(!player.getCapability(PetInventoryCapability.CAPABILITY, null).canAccesStorage()){
			if(stack!=null)
				if(stack.getItem()!= null)
					if(stack.getItem() instanceof ItemBlock)
						if(Block.getBlockFromItem(stack.getItem()).equals(Blocks.CHEST) || Block.getBlockFromItem(stack.getItem()).equals(Blocks.TRAPPED_CHEST)){
							player.getCapability(PetInventoryCapability.CAPABILITY, null).setHoldingChest();
							player.getHeldItem(hand).stackSize--;
							return true;
						}
		}else{
			getNavigator().setPath(getNavigator().getPathToEntityLiving(getOwner()), 1.0);
			FMLNetworkHandler.openGui(player, PetBuddy.instance, 0, worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
			return true;
		}

		return super.processInteract(player, hand, stack);
	}

	private EntityPlayer getMaster(){
		return getOwner() instanceof EntityPlayer ? (EntityPlayer)getOwner() : null ;
	}

	public void setInvulnerable(boolean val) {
		invulnerable = val;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity victim) {
		return victim.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return getHealth() < 0f ? false : true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(getMaster() == null)
			setDead();

	}
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////

	private void Following() {
		//		float distanceToMaster = 10.0F;
		//		if(this.getAttackTarget() == null){
		//			if (getMaster() != null) {
		//				distanceToMaster = this.getDistanceToEntity(getMaster());
		//
		//				if ((distanceToMaster > 3.0F) && (distanceToMaster < 10.0F)) {
		//					Path var2 = getNavigator().getPathToEntityLiving(owner);
		//					this.getNavigator().setPath(var2, this.getAIMoveSpeed()*1.5f);
		//
		//				} else {
		//					this.getNavigator().setPath(null, 0);
		//				}
		//			}
		//		}
	}
}
