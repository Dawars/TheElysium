package mods.elysium.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.BlockItemIDs;
import mods.elysium.Elysium;
import mods.elysium.client.particle.ElysianEntityFX;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ElysianBlockExpeller extends ElysianBlock
{
	@SideOnly(Side.CLIENT)
	private Icon front;
	
	public ElysianBlockExpeller(int id, Material mat)
	{
		super(id, mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	 /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * sets Dispenser block direction so that the front faces an non-opaque block; chooses west to be direction if all
     * surrounding blocks are opaque.
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        int k = par2 & 7;
        return par1 == k ? (k != 1 && k != 0 ? this.front : this.front) : (k != 1 && k != 0 ? (par1 != 1 && par1 != 0 ? this.blockIcon : this.blockIcon) : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(BlockItemIDs.modId + ":" + "palestoneslab");
        this.front = par1IconRegister.registerIcon(BlockItemIDs.modId + ":" + "expeller");
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	ElysianEntityFX particle = new ElysianEntityFX(world, x, y, z + 1, 0, 0, 0);
    	particle.setTextureFile("/mods/elysium/textures/misc/particles/milksand.png");
    	particle.multipleParticleScaleBy(0.5F);
    	particle.setRBGColorF(1, 1, 1);
    	particle.setBrightness(240);
    	ModLoader.getMinecraftInstance().effectRenderer.addEffect(particle);
    }
}
