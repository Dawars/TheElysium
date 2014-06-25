package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumBlockLeaves extends BlockLeaves
{
	public ElysiumBlockLeaves()
    {
		super();
		this.setCreativeTab(Elysium.tabElysium);
		
    }

	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.MODID  + ":" + texture;
        return this;
    }
	
    public static final String[] name = new String[] {"fostimber"};
	private IIcon[][] icon = new IIcon[2][name.length];

	/**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

	@Override
    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
    {
        return Item.getItemFromBlock(Elysium.blockSapling);
    }
    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int p_149741_1_)
    {
        return 0xffffff;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	int color = 0xffffff;
        return color;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube()
    {
        return !FMLClientHandler.instance().getClient().isFancyGraphicsEnabled();
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return super.damageDropped(meta) + 4;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 3;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return FMLClientHandler.instance().getClient().isFancyGraphicsEnabled() ? this.icon[0][meta & 3] : this.icon[1][meta & 3];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs p_149666_2_, List list)
    {
    	for(int i = 0; i < name.length; i++)
    		list.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < name.length; ++i)
        {
            this.icon[0][i] = iconRegister.registerIcon(this.getTextureName() + "_" + name[i]);
            this.icon[1][i] = iconRegister.registerIcon(this.getTextureName() + "_" + name[i] + "_opaque");
        }
    }

    @Override
    public String[] func_150125_e()
    {
        return name;
    }
}
