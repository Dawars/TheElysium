package mods.elysium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class FostimberLogBlock extends ElysianBlock{
	/** The type of tree this log came from. */
//    public static final String[] woodType = new String[] {"fostimber"};
//    public static final String[] treeTextureTypes = new String[] {"fostimber_log_side"};

    @SideOnly(Side.CLIENT)
    private Icon tree_top;
    
    @SideOnly(Side.CLIENT)
    private Icon tree_side;
    
	public FostimberLogBlock(int id, Material mat) {
		super(id, mat);
	}
	
	@SideOnly(Side.CLIENT)
	/**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);

                        if (Block.blocksList[j2] != null)
                        {
                            Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int meta)
    {
		switch(side){
			case 0:
			case 1:
				return this.tree_top;
			default:
				return this.tree_side;
		}
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.tree_top = par1IconRegister.registerIcon(DefaultProps.modId + ":fostimber_log_top");
        this.tree_side = par1IconRegister.registerIcon(DefaultProps.modId + ":fostimber_log_side");

    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return true;
    }

}
