package me.dawars.CraftingPillars.api;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLLog;

import me.dawars.CraftingPillars.CraftingPillars;

public class CraftingPillarAPI
{
	private static Map diskTextures = new HashMap();

	public static void addDiskTexture(int id, String url)
	{
		diskTextures.put(id, url);
	}

	public static String getDiskTexture(int id)
	{
		return (String) (diskTextures.get(id) != null ? diskTextures.get(id) : CraftingPillars.id + ":textures/models/disk_unknown.png");
	}
}