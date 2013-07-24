package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;

public class ElysianWaterBlock extends BlockFluidClassic {
	private Icon[] icon;

	public ElysianWaterBlock(int id) {
		super(id, Elysium.elysianWaterFluid, Material.water);
		Elysium.elysianWaterFluid.setBlockID(id);
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	/**
//	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
//	 * is the only chance you get to register icons.
//	 */
//	public void registerIcons(IconRegister iconRegister) {
//		this.icon = new Icon[] {
//			iconRegister.registerIcon(DefaultProps.modId + ":elysian_water"),
//			iconRegister.registerIcon(DefaultProps.modId + ":elysian_water_flow")
//		};
//		
//        this.blockIcon = iconRegister.registerIcon(DefaultProps.modId + ":elysian_water");
//
//	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public Icon getIcon(int side, int meta) {
//		return meta == 0 ? icon[0] : icon[1];
//	}


	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return Block.waterMoving.getIcon(side, meta);
	}
	
	
	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z) {
		return 0x8CF4FF;
	}
}