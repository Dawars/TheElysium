package me.dawars.CraftingPillars.api;

import java.util.HashMap;
import java.util.Map;

import me.dawars.CraftingPillars.CraftingPillars;

public class CraftingPillarAPI {
	private static Map<Integer, String> diskTextures = new HashMap<Integer, String>();

	public static void addDiskTexture(int id, String url) {
		diskTextures.put(id, url);
	}

	public static String getDiskTexture(int id) {
		return (String) (diskTextures.get(id) != null ? diskTextures.get(id)
				: CraftingPillars.id + ":textures/models/disk_unknown.png");
	}
}