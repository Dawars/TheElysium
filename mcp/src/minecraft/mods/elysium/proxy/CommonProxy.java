package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy
{
	public Object getClient()
	{
		return null;
	}
	
	public World getClientWorld()
	{
		return null;
	}
	
	public void registerRenderers()
	{
		
	}
	
	public void installSounds()
	{
		
	}
}
