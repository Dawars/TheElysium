package hu.hundevelopers.elysium.entity.projectile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityFireballProjectile extends EntityThrowable
{

	public EntityFireballProjectile(World world)
	{
		super(world);
	}
	
	public EntityFireballProjectile(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjPos)
	{
		if (!this.worldObj.isRemote)
        {
            if (movingObjPos.entityHit != null)
            {
                if (!movingObjPos.entityHit.isImmuneToFire() && movingObjPos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(new EntitySmallFireball(worldObj, this.getThrower(), 0, 0, 0), this.getThrower()), 5.0F))
                {
                    movingObjPos.entityHit.setFire(1);
                }
            }
            else
            {
                int i = movingObjPos.blockX;
                int j = movingObjPos.blockY;
                int k = movingObjPos.blockZ;

                switch (movingObjPos.sideHit)
                {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }

                if (this.worldObj.isAirBlock(i, j, k))
                {
                    this.worldObj.setBlock(i, j, k, Blocks.fire);
                }
            }

            this.setDead();
        }
	}
   
	 /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
    	super.onUpdate();

        this.setFire(1);
        this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);

    }
}