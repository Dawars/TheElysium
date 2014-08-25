package hu.hundevelopers.elysium.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityVoidSpecter extends EntityFlying implements IMob
{

	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	
	public Entity targetedEntity;
	/** Cooldown time between target loss and new target aquirement. */
	private int aggroCooldown;
	public int prevAttackCounter;
	public int spawnCounter;

	public EntityVoidSpecter(World par1World)
	{
		super(par1World);
		this.setSize(2.0F, 2.0F);
		this.isImmuneToFire = true;

        this.ignoreFrustumCheck = true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean func_110182_bF()
	{
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }

	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }
	
    @Override
	protected void updateEntityActionState()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }

        this.despawnEntity();
        this.prevAttackCounter = this.spawnCounter;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.motionX += d0 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }

        if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
        {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

            if (this.targetedEntity != null)
            {
//            	spawnX = (int) (this.targetedEntity.posX + this.rand.nextInt(16) - 8);
//            	spawnZ = (int) (this.targetedEntity.posZ + this.rand.nextInt(16) - 8);
//                 
//            	spawnY = this.worldObj.getTopSolidOrLiquidBlock((int) spawnX, (int) spawnZ);
      
                this.aggroCooldown = 20;
            }
        }

        double d4 = 32.0D;

        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
        {
 
//           this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(spawnX, spawnZ)) * 180.0F / (float)Math.PI;

            if (this.canEntityBeSeen(this.targetedEntity))
            {
                if (this.spawnCounter == 10)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }

                ++this.spawnCounter;

                if (this.spawnCounter == 20)
                {
                	//TODO: spawn ground mobs
//                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
//                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
//                    entitylargefireball.field_92057_e = this.explosionStrength;
//                    double d8 = 4.0D;
//                    Vec3 vec3 = this.getLook(1.0F);
//                    entitylargefireball.posX = this.posX + vec3.xCoord * d8;
//                    entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
//                    entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
                	EntityEnderman entity = new EntityEnderman(worldObj);
//                	entity.setPosition(spawnX, spawnY, spawnZ);
                    this.worldObj.spawnEntityInWorld(entity);
                    this.spawnCounter = -40;
                }
            }
            else if (this.spawnCounter > 0)
            {
                --this.spawnCounter;
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

            if (this.spawnCounter > 0)
            {
                --this.spawnCounter;
            }
        }

        if (!this.worldObj.isRemote)
        {
            byte b1 = this.dataWatcher.getWatchableObjectByte(16);
            byte b0 = (byte)(this.spawnCounter > 10 ? 1 : 0);

            if (b1 != b0)
            {
                this.dataWatcher.updateObject(16, Byte.valueOf(b0));
            }
        }
    }

	/**
	 * True if the ghast has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
	{
		double d4 = (this.waypointX - this.posX) / par7;
		double d5 = (this.waypointY - this.posY) / par7;
		double d6 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; (double) i < par7; ++i)
		{
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Simplifies the value of a number by adding/subtracting 180 to the point
	 * that the number is between -180 and 180.
	 */
	private float simplifyAngle(double par1)
	{
		return (float) MathHelper.wrapAngleTo180_double(par1);
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