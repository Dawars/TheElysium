package mods.elysium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class PalestoneBlock extends ElysianBlock  {

	private String iconName;

	public PalestoneBlock(int id, Material mat) {
		super(id, mat);
	}

}
