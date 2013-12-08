package mods.elysium.item;

import mods.elysium.Elysium;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class ElysianBucket extends ItemBucket
{
	public ElysianBucket(int id, int blockID) {
		super(id, blockID);
		this.setContainerItem(Item.bucketEmpty);
		this.setCreativeTab(Elysium.tabElysium);
	}

	public void registerIcons(IconRegister r) {
		itemIcon = r.registerIcon(Elysium.id + ":" + this.getUnlocalizedName().substring(5));
	}
}
