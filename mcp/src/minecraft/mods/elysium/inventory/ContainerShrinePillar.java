package mods.elysium.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class ContainerShrinePillar extends Container
{
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return false;
	}
}
