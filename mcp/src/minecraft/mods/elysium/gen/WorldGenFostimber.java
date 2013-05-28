package mods.elysium.gen;

import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.block.ElysiumFlower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class WorldGenFostimber extends WorldGenerator
{
    /** The minimum height of a generated tree. */
//    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
//    private final boolean vinesGrow;

    /** The metadata value of the wood to use in tree generation. */
//    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
//    private final int metaLeaves;

    public WorldGenFostimber(boolean par1)
    {
        super(par1);
    }

//    public WorldGenFostimber(boolean par1, int par2, int par3, int par4)
//    {
//        super(par1);
//        this.minTreeHeight = par2;
////        this.metaWood = par3;
////        this.metaLeaves = par4;
////        this.vinesGrow = par5;
//    }
    public WorldGenFostimber(boolean par1, int par2)
    {
        super(par1);
    }

    public boolean generate(World world, Random rand, int i, int j, int k)
    { 
    	
    	
    	
    	
    	int cap = rand.nextInt(2) + 2;
    	int trunk = rand.nextInt(3) + 3;
    	int minTreeHeight = 8;

        int treeHeight = trunk + minTreeHeight;
        
        if (!((ElysiumFlower)Elysium.FostimberSaplingBlock).canThisPlantGrowOnThisBlockID(world.getBlockId(i, j-1, k)))
    		return false;

        for(int i2 = j + 1; i2 < j + treeHeight; i2++){
    		if(world.getBlockId(i, i2, k) != 0)
    			return false;
    			
        }
        
        
        int h1 = treeHeight - rand.nextInt(2);//leaves end
    	int h2 = treeHeight - rand.nextInt(2);//leaves
    	int h3 = treeHeight - rand.nextInt(2);//leaves
    	int h4 = treeHeight - rand.nextInt(2);//leaves
    	
        for(int h = 0; h < treeHeight + cap; h++){
        	
        	if(h < treeHeight){
        		this.setBlock(world, i, j + h, k, Elysium.FostimberLogBlock.blockID);
        	}
        	
        	if(h >= trunk && h < treeHeight + 1){
	        	addLeaves(world, i + 1, j + h, k, Elysium.FostimberLeavesBlock);
	        	addLeaves(world, i - 1, j + h, k, Elysium.FostimberLeavesBlock);
	        	addLeaves(world, i, j + h, k + 1, Elysium.FostimberLeavesBlock);
	        	addLeaves(world, i, j + h, k - 1, Elysium.FostimberLeavesBlock);
        	}
        	
        	
        	
        	if(h > trunk && h < h1)
	        	addLeaves(world, i + 1, j + h, k + 1, Elysium.FostimberLeavesBlock);
        	if(h > trunk && h < h2)
        		addLeaves(world, i - 1, j + h, k - 1, Elysium.FostimberLeavesBlock);
        	if(h > trunk && h < h3)
        		addLeaves(world, i - 1, j + h, k + 1, Elysium.FostimberLeavesBlock);
        	if(h > trunk && h < h4)
        		addLeaves(world, i + 1, j + h, k - 1, Elysium.FostimberLeavesBlock);
        	
        	if(h >= treeHeight){
        		addLeaves(world, i, j + h, k, Elysium.FostimberLeavesBlock);
        	}
        }
        
        return true;
        
//        if (block == null || block.canBeReplacedByLeaves(par1World, j2, j1, l2))
//        {
//            this.setBlockAndMetadata(par1World, j2, j1, l2, Block.leaves.blockID, this.metaLeaves);
//        }
//                if (isSoil && par4 < 256 - l - 1)
//                {
//                    soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
    }

	private void addLeaves(World world, int i, int j, int k, Block leaf) {
		int id = world.getBlockId(i, j, k);
        Block block = Block.blocksList[id];
		if(block == null || block.canBeReplacedByLeaves(world, i, j, k)){
			this.setBlock(world, i, j, k, leaf.blockID);
		}
	}

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
//    private void growVines(World par1World, int par2, int par3, int par4, int par5)
//    {
//        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
//        int i1 = 4;
//
//        while (true)
//        {
//            --par3;
//
//            if (par1World.getBlockId(par2, par3, par4) != 0 || i1 <= 0)
//            {
//                return;
//            }
//
//            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
//            --i1;
//        }
//    }
}
