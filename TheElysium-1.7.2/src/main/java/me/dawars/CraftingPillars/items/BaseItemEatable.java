package me.dawars.CraftingPillars.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import me.dawars.CraftingPillars.CraftingPillars;

public class BaseItemEatable extends ItemFood
{
	public BaseItemEatable(int heal, float saturation)
	{
		super(heal, saturation, false);
		this.setCreativeTab(CraftingPillars.tabPillar);
	}

	public BaseItemEatable(int heal)
	{
		this(heal, 0.6F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister itemIcon)
	{
		this.itemIcon = itemIcon.registerIcon(CraftingPillars.ID + ":" + this.getUnlocalizedName().substring(5));
	}
}
