package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import mods.elysium.dimension.portal.ElysianTileEntityPortal;
import mods.elysium.dimension.portal.ElysianTileEntityPortalRenderer;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void RegisterRenders() {
//		Suggestions.SlimeBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
//
//		RenderingRegistry.registerBlockHandler(new RenderSlimeBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysianTileEntityPortalRenderer());
	}
	
	public static void registerBlock(Block block){
		GameRegistry.registerBlock(block, DefaultProps.modId + block.getUnlocalizedName2());
	}
}
