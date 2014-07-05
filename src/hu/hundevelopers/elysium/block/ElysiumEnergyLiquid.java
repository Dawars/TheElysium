package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class ElysiumEnergyLiquid extends BlockFluidClassic
{

	public ElysiumEnergyLiquid(Fluid fluid, Material material)
	{
		super(fluid, material);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(Elysium.MODID + ":energy_liquid");
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
