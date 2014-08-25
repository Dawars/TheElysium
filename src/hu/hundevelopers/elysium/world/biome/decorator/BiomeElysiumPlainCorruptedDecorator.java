package hu.hundevelopers.elysium.world.biome.decorator;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeElysiumPlainCorruptedDecorator extends BiomeDecorator
{
	 /** Field that holds one of the plantYellow WorldGenFlowers */
   public WorldGenerator plantAsphodelGen;
   
	public BiomeElysiumPlainCorruptedDecorator(BiomeGenBase biomeGenBase)
	{
		super();
       this.plantAsphodelGen = new WorldGenFlowers(Elysium.blockFlower);
       ((WorldGenFlowers) this.plantAsphodelGen).func_150550_a(Elysium.blockFlower, 0);
	}

	/**
	 * The method that does the work of actually decorating chunks
	 */
	@Override
    protected void genDecorations(BiomeGenBase p_150513_1_)
	{
//		System.out.println("Elysium Plain decorator");
		
		
	}
}
