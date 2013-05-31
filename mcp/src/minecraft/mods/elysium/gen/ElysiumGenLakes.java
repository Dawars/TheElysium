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
	int x,y,z;
	
	public ElysiumGenLakes(int liquId, int sideId)
	{
		this.liquidId = liquId;
		this.sideId = sideId;
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
    {
		this.world = world;
		
        this.x = x -= 8;
        this.z = z -= 8;
        this.y = y = world.getTopSolidOrLiquidBlock(x, z);

        if((y <= 4) || (world.getBlockMaterial(x, y, z).isLiquid()))
        {
            return false;
        }
        else
        {
            this.y = y -= 4;
            int l = random.nextInt(4) + 4;
            
            int cx;
            int cy;
            int cz;
            
            int i;
            
            for (i = 0; i < l; i++)
            {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (cx = 1; cx < 15; cx++)
                {
                    for (cz = 1; cz < 15; cz++)
                    {
                        for (cy = 1; cy < 7; cy++)
                        {
                            double d6 = ((double)cx - d3) / (d0 / 2.0D);
                            double d7 = ((double)cy - d4) / (d1 / 2.0D);
                            double d8 = ((double)cz - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                lake[(cx * 16 + cz) * 8 + cy] = liquidId;
                            }
                        }
                    }
                }
            }
            
            boolean flag;

            for (cx = 0; cx < 16; cx++)
            {
                for (cz = 0; cz < 16; cz++)
                {
                    for (cy = 0; cy < 8; cy++)
                    {
                        flag = (lake[(cx * 16 + cz) * 8 + cy] != liquidId) && (cx < 15 && (lake[((cx + 1) * 16 + cz) * 8 + cy] == liquidId) || cx > 0 && (lake[((cx - 1) * 16 + cz) * 8 + cy] == liquidId) || cz < 15 && (lake[(cx * 16 + cz + 1) * 8 + cy] == liquidId) || cz > 0 && (lake[(cx * 16 + (cz - 1)) * 8 + cy] == liquidId) || cy < 7 && (lake[(cx * 16 + cz) * 8 + cy + 1] == liquidId) || cy > 0 && (lake[(cx * 16 + cz) * 8 + (cy - 1)] == liquidId));

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

            for (cx = 0; cx < 16; cx++)
            {
                for (cz = 0; cz < 16; cz++)
                {
                    for (cy = 0; cy < 8; cy++)
                    {
                        if (lake[(cx * 16 + cz) * 8 + cy] == liquidId)
                        {
                        	if(cy >= 4)
                        	{
                        		lake[(cx * 16 + cz) * 8 + cy] = 0;
                        	}
                        }
                    }
                }
            }
            
            for(cy = 0; cy < 8; cy++)
        	{
		        for(cx = 8; cx < 16; cx++)
		        {
		        	for(cz = 8; cz < 16; cz++)
		        	{
                		if(canPlaceSideAt(cx, cy, cz) && (random.nextInt(oddsForPlaceSideAt(cx, cy, cz)) == 0))
                		{
                			lake[(cx*16 + cz)*8 + cy] = sideId;
                		}
                	}
            	}
		        
		        for(cx = 7; cx >= 0; cx--)
		        {
		        	for(cz = 8; cz < 16; cz++)
		        	{
                		if(canPlaceSideAt(cx, cy, cz) && (random.nextInt(oddsForPlaceSideAt(cx, cy, cz)) == 0))
                		{
                			lake[(cx*16 + cz)*8 + cy] = sideId;
                		}
                	}
            	}
		        
		        for(cx = 7; cx >= 0; cx--)
		        {
		        	for(cz = 7; cz >= 0; cz--)
		        	{
                		if(canPlaceSideAt(cx, cy, cz) && (random.nextInt(oddsForPlaceSideAt(cx, cy, cz)) == 0))
                		{
                			lake[(cx*16 + cz)*8 + cy] = sideId;
                		}
                	}
            	}
		        
		        for(cx = 8; cx < 16; cx++)
		        {
		        	for(cz = 7; cz >= 0; cz--)
		        	{
		        		if(canPlaceSideAt(cx, cy, cz) && (random.nextInt(oddsForPlaceSideAt(cx, cy, cz)) == 0))
                		{
                			lake[(cx*16 + cz)*8 + cy] = sideId;
                		}
                	}
            	}
            }
            
            for (cx = 0; cx < 16; cx++)
            {
                for (cz = 0; cz < 16; cz++)
                {
                    for (cy = 0; cy < 8; cy++)
                    {
                    	if(lake[(cx * 16 + cz) * 8 + cy] != 0)
                    	{
                    		world.setBlock(x+cx, y+cy, z+cz, lake[(cx * 16 + cz) * 8 + cy]);
                    	}
                    }
                }
            }
            
            System.out.println(x+" "+y+" "+z);
            return true;
        }
    }
	
	boolean canPlaceSideAt(int cx, int cy, int cz)
	{
		return (lake[(cx*16 + cz)*8 + cy] != sideId) && (lake[(cx*16 + cz)*8 + cy] != liquidId) && (!world.isAirBlock(x+cx, y+cy, z+cz)) && (world.canBlockSeeTheSky(x+cx, y+cy, z+cz) || world.canBlockSeeTheSky(x+cx, y+cy+1, z+cz));
	}
	
	boolean placeSideAt(int cx, int cy, int cz)
	{
		if(canPlaceSideAt(cx, cy, cz))
		{
			
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	int oddsForPlaceSideAt(int cx, int cy, int cz)
	{
		int ret = 1;
		
		if((cx<15) && ((lake[((cx+1)*16 + cz)*8 + cy] != liquidId) && (lake[((cx+1)*16 + cz)*8 + cy] != sideId)))
		{
			ret++;
		}
		if((cx>0) && ((lake[((cx-1)*16 + cz)*8 + cy] != liquidId) && (lake[((cx-1)*16 + cz)*8 + cy] != sideId)))
		{
			ret++;
		}
		if((cz<15) && ((lake[(cx*16 + cz+1)*8 + cy] != liquidId) && (lake[(cx*16 + cz+1)*8 + cy] != sideId)))
		{
			ret++;
		}
		if((cz>0) && ((lake[(cx*16 + cz-1)*8 + cy] != liquidId) || (lake[(cx*16 + cz-1)*8 + cy] != sideId)))
		{
			ret++;
		}
		if((cy<7) && (lake[(cx*16 + cz)*8 + cy+1] != liquidId))
		{
			ret++;
		}
		
		return ret;
	}
}
