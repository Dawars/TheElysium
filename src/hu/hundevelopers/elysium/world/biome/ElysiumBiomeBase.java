package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumBiomeBase extends BiomeGenBase
{   
	protected static final BiomeGenBase.Height height_Plains = new BiomeGenBase.Height(0.125F, 0.1F);
	protected static final BiomeGenBase.Height height_Plains_Corrupted = new BiomeGenBase.Height(1.0F, 0.5F);

	protected static final BiomeGenBase.Height height_Forest_Corrupted = new BiomeGenBase.Height(0.1F, 0.3F);
	protected static final BiomeGenBase.Height height_Forest = new BiomeGenBase.Height(0.2F, 0.2F);

	
//	protected static final BiomeGenBase.Height height_Default = new BiomeGenBase.Height(0.1F, 0.2F);
//    protected static final BiomeGenBase.Height height_ShallowWaters = new BiomeGenBase.Height(-0.5F, 0.0F);
//    protected static final BiomeGenBase.Height height_Oceans = new BiomeGenBase.Height(-1.0F, 0.1F);
//    protected static final BiomeGenBase.Height height_DeepOceans = new BiomeGenBase.Height(-1.8F, 0.1F);
//    protected static final BiomeGenBase.Height height_LowPlains = new BiomeGenBase.Height(0.125F, 0.05F);
//    protected static final BiomeGenBase.Height height_MidPlains = new BiomeGenBase.Height(0.2F, 0.2F);
//    protected static final BiomeGenBase.Height height_LowHills = new BiomeGenBase.Height(0.45F, 0.3F);
//    protected static final BiomeGenBase.Height height_HighPlateaus = new BiomeGenBase.Height(1.5F, 0.025F);
//    protected static final BiomeGenBase.Height height_MidHills = new BiomeGenBase.Height(1.0F, 0.5F);
//    protected static final BiomeGenBase.Height height_Shores = new BiomeGenBase.Height(0.0F, 0.025F);
//    protected static final BiomeGenBase.Height height_RockyWaters = new BiomeGenBase.Height(0.1F, 0.8F);
//    protected static final BiomeGenBase.Height height_LowIslands = new BiomeGenBase.Height(0.2F, 0.3F);
//    protected static final BiomeGenBase.Height height_PartiallySubmerged = new BiomeGenBase.Height(-0.2F, 0.1F);
    
	public ElysiumBiomeBase(int id)
	{
		super(id, true);
        this.flowers.add(new FlowerEntry(Elysium.blockFlower, 0, 20));

        this.topBlock = Elysium.blockGrass;
		this.fillerBlock = Elysium.blockDirt;
		
        this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}

}
