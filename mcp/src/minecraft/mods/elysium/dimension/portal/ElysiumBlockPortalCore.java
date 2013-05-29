package mods.elysium.dimension.portal;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.block.ElysiumBlockContainer;
import mods.elysium.dimension.TutorialTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ElysiumBlockPortalCore extends ElysiumBlockContainer
{
	public ElysiumBlockPortalCore(int id, Material mat)
	{
		super(id, mat);
		this.setBlockBounds(0, 0, 0, 1, 0.9375F, 1);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Elysium.GracePrismItem.itemID;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        super.breakBlock(world, x, y, z, par5, par6);
        world.setBlock(x, y, z, Block.dragonEgg.blockID);
    }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		System.out.println(world.getBlockMetadata(x, y, z));
		if((world.getBlockMetadata(x, y, z) == 1) && (entity.riddenByEntity == null) && (entity.ridingEntity == null) && (entity instanceof EntityPlayerMP))
		{
			System.out.println("teleporting");
			EntityPlayerMP player = (EntityPlayerMP) entity;
			
			if(player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			}
			else if(player.dimension == Elysium.DimensionID)
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new ElysiumTeleporter(player.mcServer.worldServerForDimension(0)));
			}
			else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Elysium.DimensionID, new ElysiumTeleporter(player.mcServer.worldServerForDimension(Elysium.DimensionID)));
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new ElysiumTileEntityPortal();
	}
}
