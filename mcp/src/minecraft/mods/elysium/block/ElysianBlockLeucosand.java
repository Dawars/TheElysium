package mods.elysium.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.world.World;

public class ElysianBlockLeucosand extends ElysianBlock
{
    /** Do blocks fall instantly to where they stop or do they fall over time */
    public static boolean fallInstantly = false;

    public ElysianBlockLeucosand(int par1)
    {
        super(par1, Material.sand);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public ElysianBlockLeucosand(int par1, Material par2Material)
    {
        super(par1, par2Material);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int par2, int par3, int par4)
    {
        world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(world));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int par2, int par3, int par4, int par5)
    {
        world.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(world));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int par2, int par3, int par4, Random par5Random)
    {
        if (!world.isRemote)
        {
            this.tryToFall(world, par2, par3, par4);
        }
    }

    /**
     * If there is space to fall below will start this block falling
     */
    private void tryToFall(World world, int i, int j, int k)
    {
        if (canFallBelow(world, i, j - 1, k) && j >= 0)
        {
            byte b0 = 32;

            if (!fallInstantly && world.checkChunksExist(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0))
            {
                if (!world.isRemote)
                {
                    EntityFallingSand entityfallingsand = new EntityFallingSand(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), this.blockID, world.getBlockMetadata(i, j, k));
                    this.onStartFalling(entityfallingsand);
                    world.spawnEntityInWorld(entityfallingsand);
                }
            }
            else
            {
                world.setBlockToAir(i, j, k);

                while (canFallBelow(world, i, j - 1, k) && j > 0)
                {
                    --j;
                }

                if (j > 0)
                {
                    world.setBlock(i, j, k, this.blockID);
                    onFinishFalling(world, i, j, k);
                }
            }
        }
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand par1EntityFallingSand) {}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World world)
    {
        return 2;
    }

    /**
     * Checks to see if the sand can fall into the block below it
     */
    public static boolean canFallBelow(World world, int par1, int par2, int par3)
    {
        int l = world.getBlockId(par1, par2, par3);

        if (l == 0)
        {
            return true;
        }
        else if (l == Block.fire.blockID)
        {
            return true;
        }
        else
        {
            Material material = Block.blocksList[l].blockMaterial;
            return material == Material.water ? true : material == Material.lava;
        }
    }

    /**
     * Called when the falling block entity for this block hits the ground and turns back into a block
     */
    public void onFinishFalling(World world, int i, int j, int k) {}
}
