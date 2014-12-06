package me.dawars.CraftingPillars.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class BaseItem extends Item
{
	public BaseItem()
	{
		super();
		this.setCreativeTab(CraftingPillars.tabPillar);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister itemIcon)
	{
		this.itemIcon = itemIcon.registerIcon(CraftingPillars.ID + ":" + this.getUnlocalizedName().substring(5));
	}
}
