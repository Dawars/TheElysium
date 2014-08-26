package hu.hundevelopers.elysium.world.biome.decorator;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenPlants;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenCactus;

public class ElysiumBiomeDecoratorDesert extends BiomeDecorator
{

	public ElysiumBiomeDecoratorDesert()
	{
        this.cactusGen = new ElysiumGenPlants(Elysium.blockCactus);

	}
}
