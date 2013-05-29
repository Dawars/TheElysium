package mods.elysium.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDebug extends ElysiumItem
{
	public ItemDebug(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		player.sendChatToPlayer("Id: "+world.getBlockId(x, y, z));
		player.sendChatToPlayer("Metadata: "+world.getBlockMetadata(x, y, z));
		player.sendChatToPlayer("TileEntity: "+world.getBlockTileEntity(x, y, z));
        return false;
    }
}
