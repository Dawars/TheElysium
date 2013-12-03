package mods.elysium.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumChunkManager extends WorldChunkManager implements IChunkProvider
{
	private GenLayer genBiomes;
	/** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
	private GenLayer biomeIndexLayer;
	/** The BiomeCache object for this world. */
	private BiomeCache biomeCache;
	/** A list of biomes that the player can spawn in. */
	private List biomesToSpawnIn;
	
	protected ElysiumChunkManager()
	{
		this.allowedBiomes.clear();
		this.allowedBiomes.add(Elysium.biomePlain);
		this.allowedBiomes.add(Elysium.biomePlain);
		this.allowedBiomes.add(Elysium.biomeOcean);
		this.biomeCache = new BiomeCache(this);
		this.biomesToSpawnIn = new ArrayList();
		this.biomesToSpawnIn.addAll(allowedBiomes);
	}

	public ElysiumChunkManager(long par1, WorldType par3WorldType)
	{
		this();
		GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(par1, par3WorldType);
		agenlayer = getModdedBiomeGenerators(par3WorldType, par1, agenlayer);
		this.genBiomes = agenlayer[0];
		this.biomeIndexLayer = agenlayer[1];
	}

	public ElysiumChunkManager(World par1World)
	{
		this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
	}

	/**
	 * Gets the list of valid biomes for the player to spawn in.
	 */
	public List getBiomesToSpawnIn()
	{
		return this.biomesToSpawnIn;
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position on the world.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return this.biomeCache.getBiomeGenAt(par1, par2);
	}

	/**
	 * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
	 */
	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();

		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
		{
			par1ArrayOfFloat = new float[par4 * par5];
		}

		int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

		for (int i1 = 0; i1 < par4 * par5; ++i1)
		{
			float f = (float)this.allowedBiomes.get(aint[i1] % allowedBiomes.size()).getIntRainfall() / 65536.0F;

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			par1ArrayOfFloat[i1] = f;
		}

		return par1ArrayOfFloat;
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Return an adjusted version of a given temperature based on the y height
	 */
	public float getTemperatureAtHeight(float par1, int par2)
	{
		return par1;
	}

	/**
	 * Returns a list of temperatures to use for the specified blocks.  Args: listToReuse, x, y, width, length
	 */
	public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();

		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
		{
			par1ArrayOfFloat = new float[par4 * par5];
		}

		int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

		for (int i1 = 0; i1 < par4 * par5; ++i1)
		{
			float f = (float)this.allowedBiomes.get(aint[i1] % allowedBiomes.size()).getIntTemperature() / 65536.0F;

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			par1ArrayOfFloat[i1] = f;
		}

		return par1ArrayOfFloat;
	}

	/**
	 * Returns an array of biomes for the location input.
	 */
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
		{
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

		for (int i1 = 0; i1 < par4 * par5; ++i1)
		{
			par1ArrayOfBiomeGenBase[i1] = this.allowedBiomes.get(aint[i1] % allowedBiomes.size());
		}

		return par1ArrayOfBiomeGenBase;
	}

	/**
	 * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
	 * WorldChunkManager Args: oldBiomeList, x, z, width, depth
	 */
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	{
		return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	}

	/**
	 * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
	 * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
	 */
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
	{
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
		{
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
		{
			BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
			System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
			return par1ArrayOfBiomeGenBase;
		}
		else
		{
			int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

			for (int i1 = 0; i1 < par4 * par5; ++i1)
			{
				par1ArrayOfBiomeGenBase[i1] = this.allowedBiomes.get(aint[i1] % allowedBiomes.size());
			}

			return par1ArrayOfBiomeGenBase;
		}
	}

	/**
	 * checks given Chunk's Biomes against List of allowed ones
	 */
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
	{
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

		for (int j2 = 0; j2 < l1 * i2; ++j2)
		{
			BiomeGenBase biomegenbase = this.allowedBiomes.get(aint[j2] % allowedBiomes.size());

			if (!par4List.contains(biomegenbase))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
	 * Strongly favors positive y positions.
	 */
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
	{
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
		ChunkPosition chunkposition = null;
		int j2 = 0;

		for (int k2 = 0; k2 < l1 * i2; ++k2)
		{
			int l2 = l + k2 % l1 << 2;
			int i3 = i1 + k2 / l1 << 2;
			BiomeGenBase biomegenbase = this.allowedBiomes.get(aint[k2] % allowedBiomes.size());

			if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(j2 + 1) == 0))
			{
				chunkposition = new ChunkPosition(l2, 0, i3);
				++j2;
			}
		}

		return chunkposition;
	}

	/**
	 * Calls the WorldChunkManager's biomeCache.cleanupCache()
	 */
	public void cleanupCache()
	{
		this.biomeCache.cleanupCache();
	}

	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
	{
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newBiomeGens;
	}
	
	 /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
       return null;
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
        
    }

	@Override
	public Chunk provideChunk(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk loadChunk(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
}