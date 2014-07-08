package hu.hundevelopers.elysium;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Configs {

	public static final int MAX_DRAGON_IN_END = 1;

	public static final int BIOME_PLAIN = 25;
	public static final int BIOME_OCEAN = 26;

	//Settings
	public static final int maxportaldistance = 64;
	public static final byte ticksbeforeportalcheck = 5;
	public static final byte ticksbeforeportalteleport = 20 * 5;

	public static final byte labyrinthBottom = 5;
	public static final byte labyrinthTop = labyrinthBottom + 4;

	public static final Block labyrinthWall = Blocks.quartz_block;
	public static final Block labyrinthLamp = Elysium.blockQuartzGem;

	public static final int mazeRoomRarity = 10;
}
