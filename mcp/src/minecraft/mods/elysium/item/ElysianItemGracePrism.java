package mods.elysium.item;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElysianItemGracePrism extends ElysianItem
{
	public ElysianItemGracePrism(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if(world.getBlockId(x, y, z) == Block.dragonEgg.blockID)
		{
			world.setBlock(x, y, z, Elysium.blockPortalCore.blockID);
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
