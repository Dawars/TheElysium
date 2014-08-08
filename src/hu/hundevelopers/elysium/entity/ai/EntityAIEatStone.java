package hu.hundevelopers.elysium.entity.ai;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIEatStone extends EntityAIBase
{
    private EntityLiving entity;
    private World world;
    int execute_progress;
    
	private int targetX;
	private int targetY;
	private int targetZ;
	private double speed;

    public EntityAIEatStone(EntityLiving entity, double speed)
    {
        this.entity = entity;
        this.world = entity.worldObj;
        this.speed = speed;
        this.setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.entity.getRNG().nextInt(50) != 0)
        {
            return false;
        }
        else
        {
        	Vec3 vec3 = RandomPositionGenerator.findRandomTarget((EntityCreature) this.entity, 5, 4);

            if(vec3 != null && this.world.getBlock((int) vec3.xCoord, (int) vec3.yCoord, (int) vec3.zCoord) == Elysium.blockPalestone)
        	{
            	this.targetX = (int) vec3.xCoord;
            	this.targetY = (int) vec3.yCoord;
            	this.targetZ = (int) vec3.zCoord;
            	
            	System.out.println("aistone can start");
            	if(this.entity.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed))
            		return true;
                
            	return false;
        	} else
        	{
        		return false;
        	}
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.execute_progress = 40;
        this.world.setEntityState(this.entity, (byte)10);
//        this.entity.getNavigator().clearPathEntity();
        
    	System.out.println("aistone start exec");

    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.execute_progress = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.execute_progress > 0 && !this.entity.getNavigator().noPath();
    }

    public int getAIProgress()
    {
        return this.execute_progress;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.execute_progress = Math.max(0, this.execute_progress - 1);

        if (this.execute_progress == 4)
        {
            int i = MathHelper.floor_double(this.entity.posX);
            int j = MathHelper.floor_double(this.entity.posY);
            int k = MathHelper.floor_double(this.entity.posZ);

            if (world.getBlock(targetX, targetY, targetZ) == Elysium.blockPalestone && entity.getDistanceSq(this.targetX, this.targetY, this.targetZ) < 1.5D /* || API contains ore*/)
            {
            	System.out.println("aistone close enough");
                if (this.world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                {
                    this.world.func_147480_a(this.targetX, this.targetY, this.targetZ, false);
                }

                this.entity.eatGrassBonus();
            }
        }
    }
}