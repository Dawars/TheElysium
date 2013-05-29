package mods.elysium.gen;

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
    private int blockIndex;

    public ElysiumGenLakes()
    {
        this.blockIndex = Elysium.waterStill.blockID;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
        x -= 8;
        z -= 8;
        
        //Search for ground
        y = world.getTopSolidOrLiquidBlock(x, z);
        
        if (y <= 4)
        {
            return false;
        }
        else
        {
            y -= 4;
            boolean lake[][][] = new boolean[16][8][16];
            int depth = random.nextInt(4) + 4;
            int i1;

            for (i1 = 0; i1 < depth; ++i1)
            {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int px = 1; px < 15; ++px)
                {
                    for (int pz = 1; pz < 15; ++pz)
                    {
                        for (int py = 1; py < 7; ++py)
                        {
                            double d6 = ((double)px - d3) / (d0 / 2.0D);
                            double d7 = ((double)pz - d4) / (d1 / 2.0D);
                            double d8 = ((double)py - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                lake[px][py][pz] = true;
                            }
                        }
                    }
                }
            }
            
            int px;
            int py;
            int pz;
            boolean flag;
            
            for (px = 0; px < 16; ++px)
            {
                for (pz = 0; pz < 16; ++pz)
                {
                    for (py = 0; py < 8; ++py)
                    {
                    	flag = !lake[px][py][pz] && (px < 15 && lake[px+1][py][pz] || px > 0 && lake[px-1][py][pz] || pz < 15 && lake[px][py][pz+1] || pz > 0 && lake[px][py][pz-1] || py < 7 && lake[px][py+1][pz] || py > 0 && lake[px][py-1][pz]);

                        if (flag)
                        {
                            Material material = world.getBlockMaterial(x + px, y + py, z + pz);

                            if (py >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (py < 4 && !material.isSolid() && world.getBlockId(x + px, y + py, z + pz) != this.blockIndex)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            for (px = 0; px < 16; ++px)
            {
                for (pz = 0; pz < 16; ++pz)
                {
                    for (py = 0; py < 8; ++py)
                    {
                        if (lake[px][py][pz])
                        {
                            world.setBlock(x + px, y + py, z + pz, py >= 4 ? 0 : this.blockIndex, 0, 2);
                        }
                    }
                }
            }

            for (px = 0; px < 16; ++px)
            {
                for (pz = 0; pz < 16; ++pz)
                {
                    for (py = 4; py < 8; ++py)
                    {
                    	BiomeGenBase biomegenbase = world.getBiomeGenForCoords(x + px, z + pz);
                        if (lake[px][py][pz] && world.getBlockId(x + px, y + py - 1, z + pz) == biomegenbase.fillerBlock && world.getSavedLightValue(EnumSkyBlock.Sky, x + px, y + py, z + pz) > 0)
                        {
                            world.setBlock(x + px, y + py - 1, z + pz, /*biomegenbase.topBlock*/Block.wood.blockID, 0, 2);
                        }
                    }
                }
            }
            
            /*for (px = 0; px < 16; ++px)
            {
                for (pz = 0; pz < 16; ++pz)
                {
                    for (py = 0; py < 8; ++py)
                    {
                    	flag = !lake[px][py][pz] && (px < 15 && lake[px+1][py][pz] || px > 0 && lake[px-1][py][pz] || pz < 15 && lake[px][py][pz+1] || pz > 0 && lake[px][py][pz-1] || py < 7 && lake[px][py+1][pz] || py > 0 && lake[px][py-1][pz]);

                        if (flag && (py < 4 || random.nextInt(2) != 0) && world.getBlockMaterial(x + px, y + py, z + pz).isSolid())
                        {
                            world.setBlock(x + px, y + py, z + pz, Elysium.RiltBlock.blockID, 0, 2);
                        }
                    }
                }
            }*/

            /*if (Block.blocksList[this.blockIndex].blockMaterial == Material.lava)
            {
                for (px = 0; px < 16; ++px)
                {
                    for (pz = 0; pz < 16; ++pz)
                    {
                        for (py = 0; py < 8; ++py)
                        {
                        	flag = !lake[px][py][pz] && (px < 15 && lake[px+1][py][pz] || px > 0 && lake[px-1][py][pz] || pz < 15 && lake[px][py][pz+1] || pz > 0 && lake[px][py][pz-1] || py < 7 && lake[px][py+1][pz] || py > 0 && lake[px][py-1][pz]);

                            if (flag && (py < 4 || random.nextInt(2) != 0) && world.getBlockMaterial(x + px, y + py, z + pz).isSolid())
                            {
                                world.setBlock(x + px, y + py, z + pz, Block.stone.blockID, 0, 2);
                            }
                        }
                    }
                }
            }*/

            /*if (Block.blocksList[this.blockIndex].blockMaterial == Material.water)
            {
                for (px = 0; px < 16; ++px)
                {
                    for (pz = 0; pz < 16; ++pz)
                    {
                        byte b0 = 4;

                        if (world.isBlockFreezable(x + px, y + b0, z + pz))
                        {
                            world.setBlock(x + px, y + b0, z + pz, Block.ice.blockID, 0, 2);
                        }
                    }
                }
            }*/

            return true;
        }
    }
}
