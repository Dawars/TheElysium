package me.dawars.CraftingPillars.proxy;

import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.renderer.RenderAnvilPillar;
import me.dawars.CraftingPillars.renderer.RenderBrewingPillar;
import me.dawars.CraftingPillars.renderer.RenderChristmasLeaves;
import me.dawars.CraftingPillars.renderer.RenderCraftingPillar;
import me.dawars.CraftingPillars.renderer.RenderDiskPillar;
import me.dawars.CraftingPillars.renderer.RenderExtendPillar;
import me.dawars.CraftingPillars.renderer.RenderFreezerPillar;
import me.dawars.CraftingPillars.renderer.RenderFurnacePillar;
import me.dawars.CraftingPillars.renderer.RenderLight;
import me.dawars.CraftingPillars.renderer.RenderPotPillar;
import me.dawars.CraftingPillars.renderer.RenderPresent;
import me.dawars.CraftingPillars.renderer.RenderPumpPillar;
import me.dawars.CraftingPillars.renderer.RenderSentryPillar;
import me.dawars.CraftingPillars.renderer.RenderShowOffPillar;
import me.dawars.CraftingPillars.renderer.RenderTankPillar;
import me.dawars.CraftingPillars.renderer.RenderTrashPillar;
import me.dawars.CraftingPillars.renderer.RingItemRenderer;
import me.dawars.CraftingPillars.tiles.TileEntityAnvilPillar;
import me.dawars.CraftingPillars.tiles.TileEntityBrewingPillar;
import me.dawars.CraftingPillars.tiles.TileEntityChristmasPresent;
import me.dawars.CraftingPillars.tiles.TileEntityCraftingPillar;
import me.dawars.CraftingPillars.tiles.TileEntityDiskPlayerPillar;
import me.dawars.CraftingPillars.tiles.TileEntityExtendPillar;
import me.dawars.CraftingPillars.tiles.TileEntityFreezerPillar;
import me.dawars.CraftingPillars.tiles.TileEntityFurnacePillar;
import me.dawars.CraftingPillars.tiles.TileEntityLight;
import me.dawars.CraftingPillars.tiles.TileEntityPotPillar;
import me.dawars.CraftingPillars.tiles.TileEntityPumpPillar;
import me.dawars.CraftingPillars.tiles.TileEntitySentryPillar;
import me.dawars.CraftingPillars.tiles.TileEntityShowOffPillar;
import me.dawars.CraftingPillars.tiles.TileEntityTankPillar;
import me.dawars.CraftingPillars.tiles.TileEntityTrashPillar;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClient()
	{
		return FMLClientHandler.instance().getClient();
	}

	public Object getPlayer()
	{
		return FMLClientHandler.instance().getClient().thePlayer;
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void initIconRegistry()
	{

		CraftingPillars.extendPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.showOffPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.craftingPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.furnacePillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.anvilPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.tankPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.brewingillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.diskPlayerRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.freezerPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.potPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.sentryPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.trashPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.pumpPillarRenderID = RenderingRegistry.getNextAvailableRenderId();

		CraftingPillars.christmasLeavesRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.PresentRenderID = RenderingRegistry.getNextAvailableRenderId();
		CraftingPillars.lightRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(new RenderExtendPillar());
		RenderingRegistry.registerBlockHandler(new RenderShowOffPillar());
		RenderingRegistry.registerBlockHandler(new RenderCraftingPillar());
		RenderingRegistry.registerBlockHandler(new RenderFurnacePillar());
		RenderingRegistry.registerBlockHandler(new RenderAnvilPillar());
		RenderingRegistry.registerBlockHandler(new RenderTankPillar());
		RenderingRegistry.registerBlockHandler(new RenderBrewingPillar());
		RenderingRegistry.registerBlockHandler(new RenderDiskPillar());
		RenderingRegistry.registerBlockHandler(new RenderFreezerPillar());
		RenderingRegistry.registerBlockHandler(new RenderPotPillar());
		RenderingRegistry.registerBlockHandler(new RenderSentryPillar());
		RenderingRegistry.registerBlockHandler(new RenderTrashPillar());
		RenderingRegistry.registerBlockHandler(new RenderPumpPillar());

		if(CraftingPillars.winter)
			RenderingRegistry.registerBlockHandler(new RenderChristmasLeaves());
		RenderingRegistry.registerBlockHandler(new RenderPresent());
		RenderingRegistry.registerBlockHandler(new RenderLight());
		
		MinecraftForgeClient.registerItemRenderer(CraftingPillars.itemRing, new RingItemRenderer());

	}

	@Override
	public void initTileRenderer()
	{
//		VersionChecker.check();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtendPillar.class, new RenderExtendPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShowOffPillar.class, new RenderShowOffPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingPillar.class, new RenderCraftingPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFurnacePillar.class, new RenderFurnacePillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnvilPillar.class, new RenderAnvilPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTankPillar.class, new RenderTankPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingPillar.class, new RenderBrewingPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDiskPlayerPillar.class, new RenderDiskPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFreezerPillar.class, new RenderFreezerPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPotPillar.class, new RenderPotPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySentryPillar.class, new RenderSentryPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashPillar.class, new RenderTrashPillar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPumpPillar.class, new RenderPumpPillar());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasPresent.class, new RenderPresent());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLight.class, new RenderLight());


	}

	@Override
	public void sendToServer(Packet packet)
	{
		FMLClientHandler.instance().getClient().getNetHandler().addToSendQueue(packet);
	}

	@Override
	public String playerName()
	{
		return FMLClientHandler.instance().getClient().thePlayer.getDisplayName();
	}
}
