package hu.hundevelopers.elysium.event;

import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiEmulator;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class ElysiumEventHandler {
	@SubscribeEvent
	public void guiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && Configs.customGui) {
			Minecraft.getMinecraft().displayGuiScreen(new ElysiumGuiEmulator(new ElysiumGuiMainMenu(null)));
			event.setCanceled(true);
		}
	}
}
