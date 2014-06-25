package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumWaterBlock extends BlockFluidClassic
{
	 @SideOnly(Side.CLIENT)
     protected IIcon stillIcon;
     @SideOnly(Side.CLIENT)
     protected IIcon flowingIcon;
     
	public ElysiumWaterBlock(Fluid fluid, Material material)
	{
		super(fluid, material);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if(!world.isRemote && entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity).curePotionEffects(new ItemStack(Items.milk_bucket));
		}
	}
		
	
     @Override
     public IIcon getIcon(int side, int meta)
     {
         return (side == 0 || side == 1) ? stillIcon : flowingIcon;
     }
     
     @SideOnly(Side.CLIENT)
     @Override
     public void registerBlockIcons(IIconRegister register)
     {
         stillIcon = register.registerIcon(Elysium.MODID + ":elysian_water_still");
         flowingIcon = register.registerIcon(Elysium.MODID + ":elysian_water_flow");
     }
     
     @Override
     public boolean canDisplace(IBlockAccess world, int x, int y, int z)
     {
         if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
        	 return false;
         
         return super.canDisplace(world, x, y, z);
     }
     
     @Override
     public boolean displaceIfPossible(World world, int x, int y, int z)
     {
         if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
        	 return false;
         return super.displaceIfPossible(world, x, y, z);
     }

}
