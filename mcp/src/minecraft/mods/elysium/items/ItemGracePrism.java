package mods.elysium.items;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGracePrism extends ElysiumItem
{
	public ItemGracePrism(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if(world.getBlockId(x, y, z) == Block.dragonEgg.blockID)
		{
			world.setBlock(x, y, z, Elysium.portalCore.blockID);
			stack.damageItem(1, player);
			//return true;
		}
        return false;
    }
	
	@Override
	public int getMaxDamage()
	{
		return 1;
	}
}
