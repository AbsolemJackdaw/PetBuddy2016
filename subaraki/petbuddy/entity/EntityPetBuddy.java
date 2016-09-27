package subaraki.petbuddy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPetBuddy extends EntityTameable{

	public EntityPetBuddy(World worldIn) {
		super(worldIn);
		this.setSize(0.25F, 0.5F);

		this.getNavigator().setPath((Path) null, 0D);
		this.Following();
		this.setAttackTarget((EntityLivingBase) null);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 0.5F, 10.0F, 2.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 0.5F));
		this.tasks.addTask(4,new EntityAIWatchClosest(this, Entity.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	private void Following() {
		float distanceToMaster = 10.0F;
		if (getMaster() != null) {
			distanceToMaster = this.getDistanceToEntity(getMaster());

			if ((distanceToMaster > 3.0F) && (distanceToMaster < 10.0F)) {
				Path var2 = getNavigator().getPathToEntityLiving(getOwner());
				this.getNavigator().setPath(var2, this.getAIMoveSpeed()*1.5f);

			} else {
				this.getNavigator().setPath(null, 0);
			}
		}
	}
	
	private EntityPlayer getMaster(){
		return getOwner() instanceof EntityPlayer ? (EntityPlayer)getOwner() : null ;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return true;
	}
}
