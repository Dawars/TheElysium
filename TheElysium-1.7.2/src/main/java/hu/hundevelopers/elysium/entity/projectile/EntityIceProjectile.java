package hu.hundevelopers.elysium.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityIceProjectile extends EntityThrowable
{
	public EntityIceProjectile(World world)
    {
        super(world);
    }
	
	public EntityIceProjectile(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}
	
	 /**
     * Called when this EntityThrowable hits a block or entity.
     */
	@Override
    protected void onImpact(MovingObjectPosition movingObjPos)
    {
        if (movingObjPos.entityHit != null)
        {
            float b0 = 0.5F;

            if (movingObjPos.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            movingObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), b0);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        
        if(worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) == Blocks.fire)
        	worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Block.getBlockById(0));
        
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
