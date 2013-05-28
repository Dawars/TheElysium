package mods.elysium.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ElysianItem extends Item{
	private String iconName;

	public ElysianItem(int id) {
		super(id);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item setUnlocalizedName(String name) {
		iconName = name;
		return super.setUnlocalizedName(name);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.itemIcon = itemIcon.registerIcon(DefaultProps.modId + ":" + iconName);
    }
}
