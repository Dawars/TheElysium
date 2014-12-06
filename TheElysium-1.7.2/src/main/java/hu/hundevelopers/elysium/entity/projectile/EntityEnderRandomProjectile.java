package hu.hundevelopers.elysium.entity.projectile;


import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEnderRandomProjectile extends EntityThrowable
{
	public EntityEnderRandomProjectile(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
    }
	
	public EntityEnderRandomProjectile(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}
	
	public EntityEnderRandomProjectile(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float par4, float par5)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;

        this.posY = shootingEntity.posY + (double)shootingEntity.getEyeHeight() - 0.10000000149011612D;
        double d0 = target.posX - shootingEntity.posX;
        double d1 = target.boundingBox.minY + (double)(target.height / 3.0F) - this.posY;
        double d2 = target.posZ - shootingEntity.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shootingEntity.posX + d4, this.posY, shootingEntity.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, par5);
        }
    }
	
	 /**
     * Called when this EntityThrowable hits a block or entity.
     */
	@Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
		if (par1MovingObjectPosition.entityHit != null)
        {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, par1MovingObjectPosition.entityHit), 0.0F);
       

	        for (int i = 0; i < 32; ++i)
	        {
	            this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
	        }
	
//	        if (!this.worldObj.isRemote)
//	        {
	            if (par1MovingObjectPosition.entityHit instanceof EntityPlayerMP)
	            {
	                EntityPlayerMP entityplayermp = (EntityPlayerMP)par1MovingObjectPosition.entityHit;
	
	                if (entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen() && entityplayermp.worldObj == this.worldObj)
	                {
	                    EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
	                    if (!MinecraftForge.EVENT_BUS.post(event))
	                    { // Don't indent to lower patch size
		                    if (entityplayermp.isRiding())
		                    {
		                    	entityplayermp.mountEntity((Entity)null);
		                    }
		                    double d0 = event.entityLiving.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		    		        double d1 = event.entityLiving.posY + (double)(this.rand.nextInt(64) - 32);
		    		        double d2 = event.entityLiving.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		    		        teleportTo(entityplayermp, d0, d1, d2);
		    		        
		                    entityplayermp.fallDistance = 0.0F;
		                    entityplayermp.attackEntityFrom(DamageSource.fall, event.attackDamage);
	                    }
	                }
	            } else if(par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
	            {
	            	EntityLivingBase entityplayermp = (EntityLivingBase)par1MovingObjectPosition.entityHit;
	
	                EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
	                if (!MinecraftForge.EVENT_BUS.post(event))
	                { // Don't indent to lower patch size
	                    if (entityplayermp.isRiding())
	                    {
	                    	entityplayermp.mountEntity((Entity)null);
	                    }
	                    
	                    double d0 = event.entityLiving.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
	    		        double d1 = event.entityLiving.posY + (double)(this.rand.nextInt(64) - 32);
	    		        double d2 = event.entityLiving.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
	    		        teleportTo(entityplayermp, d0, d1, d2);
	    		        
	                    entityplayermp.fallDistance = 0.0F;
	                    entityplayermp.attackEntityFrom(DamageSource.fall, event.attackDamage);
	                }
	            }
	            this.setDead();
//	    	}
        }
    }
	
	/**
    * Teleport the enderman
    */
   protected boolean teleportTo(EntityLivingBase entity, double targetX, double targetY, double targetZ)
   {
       double d3 = entity.posX;
       double d4 = entity.posY;
       double d5 = entity.posZ;
       entity.posX = targetX;
       entity.posY = targetY;
       entity.posZ = targetZ;
       boolean flag = false;
       int i = MathHelper.floor_double(entity.posX);
       int j = MathHelper.floor_double(entity.posY);
       int k = MathHelper.floor_double(entity.posZ);

       if (entity.worldObj.blockExists(i, j, k))
       {
           boolean flag1 = false;

           while (!flag1 && j > 0)
           {
               Block block = entity.worldObj.getBlock(i, j - 1, k);

               if (block.getMaterial().blocksMovement())
               {
                   flag1 = true;
               }
               else
               {
                   --entity.posY;
                   --j;
               }
           }

           if (flag1)
           {
        	   entity.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);

               if (entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox))
               {
                   flag = true;
               }
           }
       }

       if (!flag)
       {
       	entity.setPositionAndUpdate(d3, d4, d5);
           return false;
       }
       else
       {
           short short1 = 128;

           for (int l = 0; l < short1; ++l)
           {
               double d6 = (double)l / ((double)short1 - 1.0D);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (entity.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
               double d8 = d4 + (entity.posY - d4) * d6 + this.rand.nextDouble() * (double)entity.height;
               double d9 = d5 + (entity.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
               entity.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
           }

           entity.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
           entity.playSound("mob.endermen.portal", 1.0F, 1.0F);
           return true;
       }
   }
}
