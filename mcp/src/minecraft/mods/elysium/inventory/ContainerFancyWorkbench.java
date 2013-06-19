package mods.elysium.inventory;

import mods.elysium.entity.tileentity.TileEntityFancyWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;

public class ContainerFancyWorkbench extends Container
{
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}