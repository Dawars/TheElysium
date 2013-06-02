package mods.elysium.dimension.gen.feature;

import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenSand extends WorldGenerator
{
    int sandId,radius;

    public ElysiumGenSand(int sandId, int radius)
    {
        this.sandId = sandId;
        this.radius = radius;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
    	y = world.getTopSolidOrLiquidBlock(x, z);
    	
        if (world.getBlockMaterial(x, y, z) != Material.water)
        {
            return false;
        }
        else
        {
            int randradius = random.nextInt(this.radius - 2) + 2;
            byte b0 = 2;
            
            boolean placedSand = false;
            
            for (int cx = x - randradius; cx <= x + randradius; ++cx)
            {
                for (int cz = z - randradius; cz <= z + randradius; ++cz)
                {
                    int dx = cx - x;
                    int dz = cz - z;

                    if (dx * dx + dz * dz <= randradius * randradius)
                    {
                        for (int cy = y - b0; cy <= y + b0; ++cy)
                        {
                            int id = world.getBlockId(cx, cy, cz);

                            if (id == Elysium.soilBlock.blockID || id == Elysium.grassBlock.blockID)
                            {
                                world.setBlock(cx, cy, cz, this.sandId, 0, 2);
                                placedSand = true;
                            }
                            
                            if((id == Elysium.grassBlock.blockID) && (this.sandId == Elysium.LeucosandBlock.blockID) && (world.getBlockId(cx, cy+1, cz) == 0) && (world.getBlockId(cx+1, cy+1, cz) == 0) && (world.getBlockId(cx-1, cy+1, cz) == 0) && (world.getBlockId(cx, cy+1, cz+1) == 0) && (world.getBlockId(cx, cy+1, cz-1) == 0) && (random.nextInt(8) == 0))
                            {
                            	if(random.nextInt(2) == 0)
                            	{
                            		world.setBlock(cx, cy+1, cz, Elysium.conchFloatingBlock.blockID);
                            	}
                            	else
                            	{
                            		world.setBlock(cx, cy+1, cz, Elysium.shellFloatingBlock.blockID);
                            	}
                            }
                        }
                    }
                }
            }
            if(placedSand)
            {
            	System.out.println("Generated liquid side at: "+x+" "+y+" "+z);
            }
            return true;
        }
    }
}