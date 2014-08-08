package hu.hundevelopers.elysium.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityVoidSpecter extends EntityLiving implements IMob
{
    public double targetX;
    public double targetY;
    public double targetZ;
    private Entity target;
    public int deathTicks;
    /** The current endercrystal that is healing this dragon */
    public EntityEnderCrystal healingEnderCrystal;

    public EntityVoidSpecter(World par1World)
    {
        super(par1World);
        this.setHealth(this.getMaxHealth());
        this.setSize(2.0F, 2.0F);
        this.noClip = false;
        this.isImmuneToFire = true;
        this.targetY = 100.0D;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        float f;
        float f1;

    	this.updateDragonEnderCrystal();
        f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
        f *= (float)Math.pow(2.0D, this.motionY);


        this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

        double d0;
        double d1;
        double distSq;
        double d10;
        float f12;

        if (this.worldObj.isRemote)
        {
            if (this.newPosRotationIncrements > 0)
            {
                d10 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                distSq = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + distSq / (double)this.newPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                --this.newPosRotationIncrements;
                this.setPosition(d10, d0, d1);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
        }
        else
        {
            d10 = this.targetX - this.posX;
            d0 = this.targetY - this.posY;
            d1 = this.targetZ - this.posZ;
            distSq = d10 * d10 + d0 * d0 + d1 * d1;

            if (this.target != null)
            {
                this.targetX = this.target.posX;
                this.targetZ = this.target.posZ;
                double dx = this.targetX - this.posX;
                double dz = this.targetZ - this.posZ;
                double d7 = Math.sqrt(dx * dx + dz * dz);
                double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;

                if (d8 > 10.0D)
                {
                    d8 = 10.0D;
                }

                this.targetY = this.target.boundingBox.minY + d8;
            }
            else
            {
                this.targetX += this.rand.nextGaussian() * 2.0D;
                this.targetZ += this.rand.nextGaussian() * 2.0D;
            }

            if (distSq < 100.0D || distSq > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
            {
                this.setNewTarget();
            }

            d0 /= (double)MathHelper.sqrt_double(d10 * d10 + d1 * d1);
            f12 = 0.6F;

            if (d0 < (double)(-f12))
            {
                d0 = (double)(-f12);
            }

            if (d0 > (double)f12)
            {
                d0 = (double)f12;
            }

            this.motionY += d0 * 0.10000000149011612D;
            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
            double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
            double d6 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);

            if (d6 > 50.0D)
            {
                d6 = 50.0D;
            }

            if (d6 < -50.0D)
            {
                d6 = -50.0D;
            }

            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
            Vec3 vec32 = this.worldObj.getWorldVec3Pool().getVecFromPool((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F))).normalize();
            float f5 = (float)(vec32.dotProduct(vec3) + 0.5D) / 1.5F;

            if (f5 < 0.0F)
            {
                f5 = 0.0F;
            }

            this.randomYawVelocity *= 0.8F;
            float f6 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
            double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;

            if (d9 > 40.0D)
            {
                d9 = 40.0D;
            }

            this.randomYawVelocity = (float)((double)this.randomYawVelocity + d6 * (0.699999988079071D / d9 / (double)f6));
            this.rotationYaw += this.randomYawVelocity * 0.1F;
            float f7 = (float)(2.0D / (d9 + 1.0D));
            float f8 = 0.06F;
            this.moveFlying(0.0F, -1.0F, f8 * (f5 * f7 + (1.0F - f7)));

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.motionX, this.motionY, this.motionZ).normalize();
            float f9 = (float)(vec31.dotProduct(vec32) + 1.0D) / 2.0F;
            f9 = 0.8F + 0.15F * f9;
            this.motionX *= (double)f9;
            this.motionZ *= (double)f9;
            this.motionY *= 0.9100000262260437D;
        }
    }

    /**
     * Updates the state of the enderdragon's current endercrystal.
     */
    private void updateDragonEnderCrystal()
    {
        if (this.healingEnderCrystal != null)
        {
            if (this.healingEnderCrystal.isDead)
            {
                if (!this.worldObj.isRemote)
                {
//                    this.attackEntityFromPart(this.dragonPartHead, DamageSource.setExplosionSource((Explosion)null), 10.0F);
                }

                this.healingEnderCrystal = null;
            }
            else if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth())
            {
                this.setHealth(this.getHealth() + 1.0F);
            }
        }

        if (this.rand.nextInt(10) == 0)
        {
            float f = 32.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, this.boundingBox.expand((double)f, (double)f, (double)f));
            EntityEnderCrystal entityendercrystal = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityEnderCrystal entityendercrystal1 = (EntityEnderCrystal)iterator.next();
                double d1 = entityendercrystal1.getDistanceSqToEntity(this);

                if (d1 < d0)
                {
                    d0 = d1;
                    entityendercrystal = entityendercrystal1;
                }
            }

            this.healingEnderCrystal = entityendercrystal;
        }
    }


    /**
     * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
     */
    private void setNewTarget()
    {
        if (this.rand.nextInt(2) == 0 && !this.worldObj.playerEntities.isEmpty())
        {
            this.target = (Entity)this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));
        }
        else
        {
            boolean flag = false;

            do
            {
                this.targetX = 0.0D;
                this.targetY = (double)(70.0F + this.rand.nextFloat() * 50.0F);
                this.targetZ = 0.0D;
                this.targetX += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                this.targetZ += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                double d0 = this.posX - this.targetX;
                double d1 = this.posY - this.targetY;
                double d2 = this.posZ - this.targetZ;
                flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            }
            while (!flag);

            this.target = null;
        }
    }

    /**
     * Simplifies the value of a number by adding/subtracting 180 to the point that the number is between -180 and 180.
     */
    private float simplifyAngle(double par1)
    {
        return (float)MathHelper.wrapAngleTo180_double(par1);
    }

    /**
     * Creates the ender portal leading back to the normal world after defeating the enderdragon.
     */
    private void createEnderPortal(int par1, int par2)
    {
        byte b0 = 64;
        BlockEndPortal.field_149948_a = true;
        byte b1 = 4;

        for (int k = b0 - 1; k <= b0 + 32; ++k)
        {
            for (int l = par1 - b1; l <= par1 + b1; ++l)
            {
                for (int i1 = par2 - b1; i1 <= par2 + b1; ++i1)
                {
                    double d0 = (double)(l - par1);
                    double d1 = (double)(i1 - par2);
                    double d2 = d0 * d0 + d1 * d1;

                    if (d2 <= ((double)b1 - 0.5D) * ((double)b1 - 0.5D))
                    {
                        if (k < b0)
                        {
                            if (d2 <= ((double)(b1 - 1) - 0.5D) * ((double)(b1 - 1) - 0.5D))
                            {
                                this.worldObj.setBlock(l, k, i1, Blocks.bedrock);
                            }
                        }
                        else if (k > b0)
                        {
                            this.worldObj.setBlock(l, k, i1, Blocks.air);
                        }
                        else if (d2 > ((double)(b1 - 1) - 0.5D) * ((double)(b1 - 1) - 0.5D))
                        {
                            this.worldObj.setBlock(l, k, i1, Blocks.bedrock);
                        }
                        else
                        {
                            this.worldObj.setBlock(l, k, i1, Blocks.end_portal);
                        }
                    }
                }
            }
        }

        this.worldObj.setBlock(par1, b0 + 0, par2, Blocks.bedrock);
        this.worldObj.setBlock(par1, b0 + 1, par2, Blocks.bedrock);
        this.worldObj.setBlock(par1, b0 + 2, par2, Blocks.bedrock);
        this.worldObj.setBlock(par1 - 1, b0 + 2, par2, Blocks.torch);
        this.worldObj.setBlock(par1 + 1, b0 + 2, par2, Blocks.torch);
        this.worldObj.setBlock(par1, b0 + 2, par2 - 1, Blocks.torch);
        this.worldObj.setBlock(par1, b0 + 2, par2 + 1, Blocks.torch);
        this.worldObj.setBlock(par1, b0 + 3, par2, Blocks.bedrock);
        this.worldObj.setBlock(par1, b0 + 4, par2, Blocks.dragon_egg);
        BlockEndPortal.field_149948_a = false;
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