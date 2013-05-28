package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class ElysiumBlock extends Block 
{
	public ElysiumBlock(int id, Material mat)
	{
		super(id, mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":" + getUnlocalizedName().substring(5));
    }
}
