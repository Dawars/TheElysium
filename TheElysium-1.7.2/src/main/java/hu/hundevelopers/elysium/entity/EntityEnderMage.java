package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.entity.projectile.EntityBlockProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityFireballProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityIceProjectile;
import hu.hundevelopers.elysium.item.ElysiumStaffItem;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEnderMage extends EntityMob implements IRangedAttackMob
{

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
    
    public EntityEnderMage(World world)
	{
		super(world);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

        if (world != null && !world.isRemote)
        {
//            this.setCombatTask();
        }
	}

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte)0));
    }
    
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float damage)
	{
		int type = this.getMageType();
		if(type == 0)
    	{
	    	Block block = Staff.getBlockHolding(entity.getEquipmentInSlot(0));
	
	    	if(block != null)
			{
				EntityBlockProjectile entityprojectile = new EntityBlockProjectile(worldObj, this, block);
	            worldObj.spawnEntityInWorld(entityprojectile);
				
	            Staff.setBlockHolding(entity.getEquipmentInSlot(0), null);
			} else {
				// get throwable block nearby
//				for(int i = 0; )
				
			}
    	} else if(type == 1)
    	{
				EntityIceProjectile entityprojectile = new EntityIceProjectile(worldObj, this);
				worldObj.spawnEntityInWorld(entityprojectile);
    	} else if(type == 2)
    	{
			//TODO: ender
    	} else
    	{
    			EntityFireballProjectile entityFireball = new EntityFireballProjectile(worldObj, this);
    			worldObj.spawnEntityInWorld(entityFireball);
    	}
	}

    protected Item getDropItem()
    {
        return Item.getItemById(0);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean recentPlayerHit, int looting)
    {
        this.dropItem(Item.getItemFromBlock(Elysium.blockEnergyCrystal), 1);
        this.dropItem(Elysium.itemStaff, this.getMageType());
    }

    protected void dropRareDrop(int par1)
    {
//        if (this.getMageType() == 1)
//        {
//            this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
//        }
    }

    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Elysium.itemStaff, this.getMageType()));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
    {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        
        this.tasks.addTask(4, this.aiArrowAttack);

//        this.tasks.addTask(4, this.aiAttackOnCollide);
        this.setMageType(this.getRNG().nextInt(4));
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.addRandomArmor();
//        this.enchantEquipment();

        return par1EntityLivingData;
    }

    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
//        this.tasks.removeTask(this.aiAttackOnCollide);
//        this.tasks.removeTask(this.aiArrowAttack);
//        ItemStack itemstack = this.getHeldItem();
//
//        if (itemstack != null && itemstack.getItem() == Items.bow)
//        {
//            this.tasks.addTask(4, this.aiArrowAttack);
//        }
//        else
//        {
//            this.tasks.addTask(4, this.aiAttackOnCollide);
//        }
    }

	
	 /**
     * Return this skeleton's type.
     */
    public int getMageType()
    {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    /**
     * Set this skeleton's type.
     */
    public void setMageType(int par1)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)par1));

        if (par1 == 1)
        {
            this.setSize(0.72F, 2.34F);
        }
        else
        {
            this.setSize(0.6F, 1.8F);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("MageType", 99))
        {
            byte b0 = par1NBTTagCompound.getByte("MageType");
            this.setMageType(b0);
        }

        this.setCombatTask();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("MageType", (byte)this.getMageType());
    }

    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int slot, ItemStack item)
    {
        super.setCurrentItemOrArmor(slot, item);

        if (!this.worldObj.isRemote && slot == 0)
        {
            this.setCombatTask();
        }
    }

}
