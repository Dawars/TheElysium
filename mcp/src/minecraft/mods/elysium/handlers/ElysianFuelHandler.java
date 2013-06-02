package mods.elysium.handlers;

import mods.elysium.Elysium;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ElysianFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.itemID == Elysium.itemSulphur.itemID)
			return 1000;
		else
			return 0;
	}

}
