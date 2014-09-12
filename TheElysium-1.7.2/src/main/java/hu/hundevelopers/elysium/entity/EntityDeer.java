package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.Elysium;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityDeer extends EntityAnimal
{
    public EntityDeer(World par1World)
    {
        super(par1World);
        this.setSize(1.4F, 1.6F);
        this.isImmuneToFire = false;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 24.0F, 0.4D, 0.8D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.7D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {    
        return super.getCanSpawnHere() && this.rand.nextInt(100) == 0;
    }

	@Override
    protected Item getDropItem()
    {
        return Elysium.itemAntler;
    }

    /**
     * Drop 0-2 items of this living's type. 
     * @param par1 - Whether this entity has recently been hit by a player. 
     * @param par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

        for (int k = 0; k < j; ++k)
        {
            this.dropItem(Elysium.itemDeerPelt, 1);
        }
        
        this.dropItem(Item.getItemFromBlock(Elysium.blockEnergyCrystal), 1);
        this.dropItem(Elysium.itemAntler, 1);
    }
    
	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return null;
	}

}