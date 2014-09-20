package hu.hundevelopers.elysium.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ElysiumQuartzFence extends BlockFence
{

	public static final String[] texture_names = {"quartz_mossy", "quartz_clean"};
	public static final IIcon[] fence_icons = new IIcon[texture_names.length];

	public ElysiumQuartzFence(Material mat)
	{
		super("", mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return meta == 0 ? Blocks.quartz_block.getIcon(0, 0) : this.blockIcon;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void registerBlockIcons(IIconRegister p_149651_1_)
//    {
//    	for(int i = 0; i < texture_names.length; i++)
//    		fence_icons[i] = p_149651_1_.registerIcon(Elysium.MODID + ":" + texture_names[i]);
//    }
//    

	  @SideOnly(Side.CLIENT)
	  @Override
	  public void registerBlockIcons(IIconRegister p_149651_1_)
	  {
	  		this.blockIcon = p_149651_1_.registerIcon(Elysium.MODID + ":" + fence_icons[0]);
	  }
	  
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < texture_names.length; i++)
        	list.add(new ItemStack(item, 1, i));
    }
}
