package mods.elysium.gen;

import java.util.ArrayList;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenLakes extends WorldGenerator
{
	int liquidId,sideId;
	int lake[] = new int[2048];
	World world;
	Random random;
	BiomeGenBase biome;
	int x,y,z;
	
	public ElysiumGenLakes(int liquid, int sideid)
	{
		this.liquidId = Elysium.waterStill.blockID;
		this.sideId = sideid;
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		this.world = world;
		this.random = random;
		
        this.x = x -= 8;
        this.z = z -= 8;
        this.y = y = world.getTopSolidOrLiquidBlock(x, z);
        this.biome = world.getBiomeGenForCoords(x, z);

        if((y <= 4) || (world.getBlockMaterial(x, y, z).isLiquid()))
        {
            return false;
        }
        else
        {
            y -= 4;
            int l = random.nextInt(4) + 4;
            
            int cx;
            int cy;
            int cz;
            
            int i;
            for(i=0; i<2048; i++) lake[i] = -1;
            
            for (i = 0; i < l; ++i)
            {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (cx = 1; cx < 15; ++cx)
                {
                    for (cz = 1; cz < 15; ++cz)
                    {
                        for (cy = 1; cy < 7; ++cy)
                        {
                            double d6 = ((double)cx - d3) / (d0 / 2.0D);
                            double d7 = ((double)cy - d4) / (d1 / 2.0D);
                            double d8 = ((double)cz - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                lake[(cx * 16 + cz) * 8 + cy] = this.liquidId;
                            }
                        }
                    }
                }
            }
            
            boolean flag;

            for (cx = 0; cx < 16; ++cx)
            {
                for (cz = 0; cz < 16; ++cz)
                {
                    for (cy = 0; cy < 8; ++cy)
                    {
                        flag = !(lake[(cx * 16 + cz) * 8 + cy] == this.liquidId) && (cx < 15 && (lake[((cx + 1) * 16 + cz) * 8 + cy] == this.liquidId) || cx > 0 && (lake[((cx - 1) * 16 + cz) * 8 + cy] == this.liquidId) || cz < 15 && (lake[(cx * 16 + cz + 1) * 8 + cy] == this.liquidId) || cz > 0 && (lake[(cx * 16 + (cz - 1)) * 8 + cy] == this.liquidId) || cy < 7 && (lake[(cx * 16 + cz) * 8 + cy + 1] == this.liquidId) || cy > 0 && (lake[(cx * 16 + cz) * 8 + (cy - 1)] == this.liquidId));

                        if (flag)
                        {
                            Material material = world.getBlockMaterial(x + cx, y + cy, z + cz);

                            if (cy >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (cy < 4 && !material.isSolid() && world.getBlockId(x + cx, y + cy, z + cz) != this.liquidId)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            for (cx = 0; cx < 16; ++cx)
            {
                for (cz = 0; cz < 16; ++cz)
                {
                    for (cy = 0; cy < 8; ++cy)
                    {
                        if (lake[(cx * 16 + cz) * 8 + cy] == this.liquidId)
                        {
                        	if(cy >= 4)
                        	{
                        		lake[(cx * 16 + cz) * 8 + cy] = 0;
                        	}
                        }
                    }
                }
            }
            
            for (cy = 0; cy < 8; ++cy)
            {
		        for (cx = 0; cx < 16; ++cx)
		        {
		            for (cz = 0; cz < 16; ++cz)
		            {
                        if (lake[(cx * 16 + cz) * 8 + cy] == this.liquidId)
                        {
                        	if(canPlaceSideAt(cx+1, cy, cz))
                            {
                            	System.out.println("yay :)");
                            	lake[((cx+1)*16 + cz)*8 + cy] = this.sideId;
                            }
                            if(canPlaceSideAt(cx-1, cy, cz))
                            {
                            	System.out.println("yay :)");
                            	lake[((cx-1)*16 + cz)*8 + cy] = this.sideId;
                            }
                            if(canPlaceSideAt(cx, cy, cz+1))
                            {
                            	System.out.println("yay :)");
                            	lake[(cx*16 + cz+1)*8 + cy] = this.sideId;
                            }
                            if(canPlaceSideAt(cx, cy, cz-1))
                            {
                            	System.out.println("yay :)");
                            	lake[(cx*16 + cz-1)*8 + cy] = this.sideId;
                            }
                            if(canPlaceSideAt(cx, cy-1, cz))
                            {
                            	System.out.println("yay :)");
                            	lake[(cx*16 + cz)*8 + cy-1] = this.sideId;
                            }
                        }
                    }
                }
            }
            
            for (cx = 0; cx < 16; ++cx)
            {
                for (cz = 0; cz < 16; ++cz)
                {
                    for (cy = 0; cy < 8; ++cy)
                    {
                    	if(lake[(cx * 16 + cz) * 8 + cy] != -1)
                    	{
                    		world.setBlock(x+cx, y+cy, z+cz, lake[(cx * 16 + cz) * 8 + cy]);
                    	}
                    }
                }
            }
            
            //System.out.println(x+" "+y+" "+z);

            return true;
        }
    }
	
	boolean canPlaceSideAt(int cx, int cy, int cz)
	{
		if((lake[(cx*16 + cz)*8 + cy] == -1) && (!world.isAirBlock(x+cx, y+cy, z+cz)))
		{
			if(((cx<15) && (lake[((cx+1)*16 + cz)*8 + cy] == this.liquidId)) || ((cx>0) && (lake[((cx-1)*16 + cz)*8 + cy] == this.liquidId)) || ((cz<15) && (lake[(cx*16 + cz+1)*8 + cy] == this.liquidId)) || ((cz>0) && (lake[(cx*16 + cz-1)*8 + cy] == this.liquidId)) || ((cy<7) && (lake[(cx*16 + cz)*8 + cy+1] == this.liquidId)))
			{
				if(((cx<15) && (lake[((cx+1)*16 + cz)*8 + cy] == this.sideId)) || ((cx>0) && (lake[((cx-1)*16 + cz)*8 + cy] == this.sideId)) || ((cz<15) && (lake[(cx*16 + cz+1)*8 + cy] == this.sideId)) || ((cz>0) && (lake[(cx*16 + cz-1)*8 + cy] == this.sideId)) || ((cy<7) && (lake[(cx*16 + cz)*8 + cy+1] == this.sideId)))
				{
					//System.out.println("yay1 :)");
					return true;
				}
				else if(world.canBlockSeeTheSky(x+cx, y+cy, z+cz) && (random.nextInt(10) == 0))
				{
					//System.out.println("yay2 :)");
					return true;
				}
			}
		}
		return false;
	}
}
