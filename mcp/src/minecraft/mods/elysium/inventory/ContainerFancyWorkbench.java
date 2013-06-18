package mods.elysium.inventory;

import mods.elysium.entity.tileentity.TileEntityFancyWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;

public class ContainerFancyWorkbench extends Container
{
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	private TileEntityFancyWorkbench tile;
	
	public ContainerFancyWorkbench(TileEntityFancyWorkbench tile)
	{
		this.tile = tile;
		
		for(int i = 0; i < 9; i++)
			this.addSlotToContainer(new Slot(tile, i, i*16, 0));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tile.isUseableByPlayer(player);
	}
	
	
}