package mods.elysium.client.gui.menu;

public class MiscWorld
{
	private static int[] blocks = new int[65536];//64*16*64
	private static int[] metas = new int[65536];//64*16*64
	
	static void setBlockId(int x, int y, int z, int id)
	{
		blocks[(x*16 + y)*64 + z] = id;
	}
	
	static int getBlockId(int x, int y, int z)
	{
		return blocks[(x*16 + y)*64 + z];
	}
	
	static void setBlockMetadata(int x, int y, int z, int meta)
	{
		metas[(x*16 + y)*64 + z] = meta;
	}
	
	static int getBlockMetadata(int x, int y, int z)
	{
		return metas[(x*16 + y)*64 + z];
	}
}
