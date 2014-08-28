package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.api.Staff;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlockProjectile extends EntityThrowable
{
	
	public EntityBlockProjectile(World world)
    {
    	super(world);
    }
	Block block;
	
	public EntityBlockProjectile(World world, Block block)
	{
		super(world);
		this.block = block;
	}
    private EntityLivingBase thrower;

	public EntityBlockProjectile(World world, EntityLivingBase entity, Block block)
	{
		super(world, entity);
		this.block = block;
		this.thrower = entity;
	}

	public EntityBlockProjectile(World world, double x, double y, double z, Block block)
	{
		super(world, x, y, z);
		this.block = block;
	}

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition movingObjPos)
    {
        if (movingObjPos.entityHit != null)
        {
        	if(this.thrower instanceof EntityPlayer)
        		movingObjPos.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) this.thrower), Staff.getDamageForBlock(block));
        	else
        		movingObjPos.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.thrower), Staff.getDamageForBlock(block));

        }

        if (!this.worldObj.isRemote)
        {
        	if (!this.worldObj.setBlock(movingObjPos.blockX, movingObjPos.blockY + 1, movingObjPos.blockZ, block))
        	{
                EntityItem entityitem = new EntityItem(this.worldObj);
                entityitem.setEntityItemStack(new ItemStack(block));
                entityitem.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                this.worldObj.spawnEntityInWorld(entityitem);
        	}
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    public Block getBlock()
    {
    	return block;
    }
}
