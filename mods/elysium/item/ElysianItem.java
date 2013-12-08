package mods.elysium.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.BlockItemIDs;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElysianItem extends Item
{
	public ElysianItem(int id)
	{
		super(id);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.itemIcon = itemIcon.registerIcon(BlockItemIDs.modId + ":" + getUnlocalizedName().substring(5));
    }
}
