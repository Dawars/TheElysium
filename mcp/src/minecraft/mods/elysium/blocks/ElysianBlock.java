package mods.elysium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class ElysianBlock extends Block  {

	private String iconName;

	public ElysianBlock(int id, Material mat) {
		super(id, mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Block setUnlocalizedName(String name) {
		iconName = name;
		return super.setUnlocalizedName(name);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":" + iconName);
    }

}
