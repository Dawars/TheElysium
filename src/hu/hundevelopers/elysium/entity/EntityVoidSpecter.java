package hu.hundevelopers.elysium.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityVoidSpecter extends EntityLiving
{
    public double targetX;
    public double targetY;
    public double targetZ;
    private Entity target;
    
    public EntityVoidSpecter(World par1World)
    {
        super(par1World);
        this.setSize(2.0F, 2.0F);
        this.isImmuneToFire = true;
        this.targetY = 100.0D;
        this.targetX = this.posX + this.rand.nextGaussian() * 2.0F;
        this.targetZ = this.posZ + this.rand.nextGaussian() * 2.0F;
        this.noClip = true;

    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }
    /**
     * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
     */
    private void setNewTarget()
    {
        if (this.rand.nextInt(2) == 0 && !this.worldObj.playerEntities.isEmpty())//TODO: add heroes
        {
            this.target = (Entity)this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));
        }
        else
        {
            this.targetX = this.posX + this.rand.nextInt(20) - 1;
            this.targetZ = this.posZ + this.rand.nextInt(20) - 1;
            this.targetY = this.worldObj.getTopSolidOrLiquidBlock((int) this.targetX, (int) this.targetZ) + 20.0F + this.rand.nextInt(10);
//            double dx = this.posX - this.targetX;
//            double dy = this.posY - this.targetY;
//            double dz = this.posZ - this.targetZ;

            this.target = null;
        }
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 1.2F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        }
    }
    
//    /**
//     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
//     * use this to react to sunlight and start to burn.
//     */
//    public void onLivingUpdate()
//    {
//    	double dx;
//    	double dy;
//    	double dz;
//    	double dist;
//         
//    	if (this.worldObj.isRemote)
//        {
//            if (this.newPosRotationIncrements > 0)
//            {
//                dx = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
//                dy = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
//                dz = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
//                dist = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
//                this.rotationYaw = (float)((double)this.rotationYaw + dist / (double)this.newPosRotationIncrements);
//                this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
//                --this.newPosRotationIncrements;
//                this.setPosition(dx, dy, dz);
//                this.setRotation(this.rotationYaw, this.rotationPitch);
//            }
//        }
//        else
//        {
//            dx = this.targetX - this.posX;
//            dy = this.targetY - this.posY;
//            dz = this.targetZ - this.posZ;
//            dist = dx * dx + dy * dy + dz * dz;
//
//            if (this.target != null)
//            {
//                this.targetX = this.target.posX;
//                this.targetZ = this.target.posZ;
//                double d3 = this.targetX - this.posX;
//                double d5 = this.targetZ - this.posZ;
//                double d7 = Math.sqrt(d3 * d3 + d5 * d5);
//                double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;
//
//                if (d8 > 10.0D)
//                {
//                    d8 = 10.0D;
//                }
//
//                this.targetY = this.target.boundingBox.minY + d8;
//            }
//            else
//            {
//                this.targetX += this.rand.nextGaussian() * 2.0D;
//                this.targetZ += this.rand.nextGaussian() * 2.0D;
//            }
//
//            if (dist < 2 || dist > 16*16.0D || this.isCollidedHorizontally || this.isCollidedVertically)
//            {
//                this.setNewTarget();
//            }
//            
//
//            dx /= (double)MathHelper.sqrt_double(dx * dx + dy * dy);
//            float f12 = 0.6F;
//
//            if (dy < (double)(-f12))
//            {
//                dy = (double)(-f12);
//            }
//
//            if (dy > (double)f12)
//            {
//                dy = (double)f12;
//            }
//
//            this.motionY += dy * 0.10000000149011612D;
//            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
//            double d4 = 90.0D - Math.atan2(dx, dz) * 180.0D / Math.PI;
//            double d6 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);
//
//            if (d6 > 50.0D)
//            {
//                d6 = 50.0D;
//            }
//
//            if (d6 < -50.0D)
//            {
//                d6 = -50.0D;
//            }
//
//            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
//            Vec3 vec32 = this.worldObj.getWorldVec3Pool().getVecFromPool((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F))).normalize();
//            float f5 = (float)(vec32.dotProduct(vec3) + 0.5D) / 1.5F;
//
//            if (f5 < 0.0F)
//            {
//                f5 = 0.0F;
//            }
//
//            this.randomYawVelocity *= 0.8F;
//            float f6 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
//            double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;
//
//            if (d9 > 40.0D)
//            {
//                d9 = 40.0D;
//            }
//
//            this.randomYawVelocity = (float)((double)this.randomYawVelocity + d6 * (0.699999988079071D / d9 / (double)f6));
//            this.rotationYaw += this.randomYawVelocity * 0.1F;
//            float f7 = (float)(2.0D / (d9 + 1.0D));
//            float f8 = 0.06F;
//            this.moveFlying(0.0F, -1.0F, f8 * (f5 * f7 + (1.0F - f7)));
//
//            this.moveEntity(this.motionX, this.motionY, this.motionZ);
//
//            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.motionX, this.motionY, this.motionZ).normalize();
//            float f9 = (float)(vec31.dotProduct(vec32) + 1.0D) / 2.0F;
//            f9 = 0.8F + 0.15F * f9;
//            this.motionX *= (double)f9;
//            this.motionZ *= (double)f9;
//            this.motionY *= 0.9100000262260437D;
//        }
//    }

    /**
     * Simplifies the value of a number by adding/subtracting 180 to the point that the number is between -180 and 180.
     */
    private float simplifyAngle(double par1)
    {
        return (float)MathHelper.wrapAngleTo180_double(par1);
    }


    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.enderdragon.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.enderdragon.hit";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 2.0F;
    }
}