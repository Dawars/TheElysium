package mods.elysium.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ElysianBlockContainer extends ElysianBlock implements ITileEntityProvider
{
    protected ElysianBlockContainer(int id, Material material)
    {
        super(id, material);
        this.isBlockContainer = true;
    }
    
    @Override
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }
    
    @Override
    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }
    
    @Override
    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World world, int x, int y, int z, int blockID, int eventID)
    {
        super.onBlockEventReceived(world, x, y, z, blockID, eventID);
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(blockID, eventID) : false;
    }
    
    @Override
    public boolean canBeReplacedByLake()
	{
		return false;
	}
}
