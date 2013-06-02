package mods.elysium.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class ElysianBlockPalestone extends ElysianBlock  {

	private String iconName;

	public ElysianBlockPalestone(int id, Material mat) {
		super(id, mat);
	}
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Elysium.paleCobbletone.blockID;
    }
}
