package mods.elysium.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElysiumItem extends Item
{
	public ElysiumItem(int id)
	{
		super(id);
		this.setCreativeTab(Elysium.tabElysium);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.itemIcon = itemIcon.registerIcon(DefaultProps.modId + ":" + getUnlocalizedName().substring(5));
    }
	
	/*@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		//player.sendChatToPlayer("Id: "+world.getBlockId(x, y, z));
		player.sendChatToPlayer("Metadata: "+world.getBlockMetadata(x, y, z));
        return false;
    }*/
}
