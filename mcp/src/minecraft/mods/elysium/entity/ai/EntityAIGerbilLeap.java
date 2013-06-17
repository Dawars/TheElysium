package mods.elysium.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAIGerbilLeap extends EntityAIBase
{
    /** The entity that is leaping. */
    EntityLiving leaper;

    /** The entity that the leaper is leaping towards. */
    EntityLiving leapTarget;

    /** The entity's motionY after leaping. */
    float leapMotionY;

    public EntityAIGerbilLeap(EntityLiving par1EntityLiving, float leapmotion)
    {
        this.leaper = par1EntityLiving;
        this.leapMotionY = leapmotion;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        this.leapTarget = this.leaper.getAttackTarget();

        if (this.leapTarget == null)
        {
            return false;
        }
        else
        {
            double d0 = this.leaper.getDistanceSqToEntity(this.leapTarget);
            return d0 >= 4.0D && d0 <= 16.0D ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(5) == 0) : false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	if(this.leaper.onGround)
    	{
    		
    	}
    	
        return !this.leaper.onGround;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        double d0 = this.leapTarget.posX - this.leaper.posX;
        double d1 = this.leapTarget.posZ - this.leaper.posZ;
        float f = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
        this.leaper.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
        this.leaper.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
        this.leaper.motionY = (double)this.leapMotionY;
        
        //TODO add smoke effect here
        this.leaper.worldObj.spawnParticle("smoke", this.leaper.posX, this.leaper.posY+0.25D, this.leaper.posZ, 0D, 0.5D, 0D);
    }
}
