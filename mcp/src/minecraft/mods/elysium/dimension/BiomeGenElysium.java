package mods.elysium.dimension;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.Elysium;
import mods.elysium.gen.WorldGenBalazsLake;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenElysium extends BiomeGenBase {
	private WorldGenerator UnDeadworldGeneratorBigTree;
	public final Material blockMaterial;

	public BiomeGenElysium(int par1) {
		super(par1);
		this.blockMaterial = Material.water;
		this.minHeight = 0.1F;
		this.maxHeight = 2.0F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = ((byte) Elysium.grassBlock.blockID);
		this.fillerBlock = ((byte) Elysium.soilBlock.blockID);
//		this.waterColorMultiplier = 0x78FFF6;

		
		this.setBiomeName("Elysium Plain");
		
		
        this.theBiomeDecorator = new BiomeElysiumPlainDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
	}
	
	@Override
    public void decorate(World world, Random rand, int i, int k){
		super.decorate(world, rand, i, k);
		
//		if (rand.nextInt(50) == 0)
//        {
//            int x = i + rand.nextInt(16) + 8;
//            int y = k + rand.nextInt(16) + 8;
////            WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
////            worldgendesertwells.generate(world, rand, x, world.getHeightValue(x, y) + 1, y);
//            
//            
//
////          WorldGenBalazsLake worldgendesertwells = new WorldGenBalazsLake();
////          worldgendesertwells.generate(world, rand, x, world.getHeightValue(x, y), y);
//        }
	}
}