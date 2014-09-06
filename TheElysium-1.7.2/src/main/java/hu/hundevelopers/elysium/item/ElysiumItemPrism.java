package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElysiumItemPrism extends ElysiumItem
{
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if(world.getBlock(x, y, z) == Blocks.dragon_egg)
		{
			world.createExplosion(player, x+0.5D, y+0.5D, z+0.5D, 2F, true);
			world.setBlock(x, y, z, Elysium.blockPortalCore);
			if(!player.capabilities.isCreativeMode)
				stack.stackSize--;
			//return true;
		}
        return false;
    }
}
