package hu.hundevelopers.elysium.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ElysiumBlockFlower extends BlockBush
{
	private static final String[] names = {"asphodel"};
	private static final IIcon[] icons = new IIcon[names.length];
	
	public ElysiumBlockFlower()
	{
		super(Material.plants);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setCreativeTab(Elysium.tabElysium);
	}


	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.MODID + ":" + texture;
        return this;
    }
	
	/**
     * is the block grass, dirt or farmland
     */
	@Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return super.canPlaceBlockOn(block) || block == Elysium.blockDirt || block == Elysium.blockGrass;
    }
	

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.icons.length)
        {
            meta = 0;
        }

        return this.icons[meta];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < names.length; i++)
        	list.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + names[i]);
        }
    }
}
