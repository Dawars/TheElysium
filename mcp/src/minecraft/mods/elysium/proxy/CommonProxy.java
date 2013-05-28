package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
	@SidedProxy(clientSide = "mods.elysium.proxy.ClientProxy", serverSide = "mods.elysium.proxy.CommonProxy")
	public static CommonProxy proxy;

	/* SIMULATION */
	public boolean isSimulating(World world) {
		return !world.isRemote;
	}

	public boolean isRenderWorld(World world) {
		return world.isRemote;
	}
	
	public void RegisterRenders(){
		
	}

	public static void registerBlock(Block block){
		GameRegistry.registerBlock(block);
	}
}
