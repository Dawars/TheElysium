package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumGrapesBush extends ElysiumBlockBush implements IShearable, IGrowable
{
	public ElysiumGrapesBush()
	{
		super();
	}
	
	public static final String[] names = new String[] {"bottom", "empty", "white", "blue"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.icons = new IIcon[names.length];
		
		for (int i = 0; i < this.names.length; ++i)
		{
            this.icons[i] = register.registerIcon(Elysium.MODID + ":grapes_" + names[i]);
        }
	}
	
	/**
     * is the block grass, dirt or farmland
     */
	@Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == Elysium.blockGrass || block == Elysium.blockDirt;
    }
	
	/**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
	@Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
		int meta = world.getBlockMetadata(x, y, z);
		
        if(meta == 0)
        {
        	return canPlaceBlockOn(world.getBlock(x, y-1, z)) && world.getBlock(x, y+1, z) == this;
        } else
        {
        	return world.getBlock(x, y-1, z) == this && world.getBlockMetadata(x, y-1, z) == 0;
        }
    }
    
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && this.canPlaceBlockOn(world.getBlock(x, y-1, z));
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icons[meta];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
    	list.add(new ItemStack(item, 1, 0));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.updateTick(world, x, y, z, rand);
    	
    	if(world.getBlockMetadata(x, y, z) == 1/* && rand.nextInt(400) == 0*/)
    	{
    		System.out.println("grow");
			world.setBlockMetadataWithNotify(x, y, z, 2 + rand.nextInt(), 2);
    	}
    }

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
    @Override
	public int damageDropped(int meta)
	{
		return 0;
    }
    
    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return meta == 0 ? Item.getItemFromBlock(this) : Item.getItemById(0);
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta)
	{
		world.setBlock(x, y+1, z, this, 1, 2);
		world.setBlockMetadataWithNotify(x, y+1, z, 1, 2);
	}

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) > 1;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
//        int type = func_149890_d(world.getBlockMetadata(x, y, z));
//        if (type == 3 || type == 2)
//            ret.add(new ItemStack(Blocks.tallgrass, 2, type == 3 ? 2 : 1));
        return ret;
    }
    
    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote)
    {
    	return world.getBlockMetadata(x, y, z) == 1;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
    	return rand.nextInt(4) == 0;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
		world.setBlockMetadataWithNotify(x, y, z, 3, 2);
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }
}
