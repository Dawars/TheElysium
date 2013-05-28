package mods.elysium.dimension.portal;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.block.ElysiumBlockContainer;
import mods.elysium.dimension.TutorialTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ElysiumBlockPortalCore extends ElysiumBlockContainer
{
	private Icon blockIconActive;
	
	public ElysiumBlockPortalCore(int id, Material mat)
	{
		super(id, mat);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister itemIcon)
	{
		blockIconActive = itemIcon.registerIcon(DefaultProps.modId + ":" + getUnlocalizedName().substring(5)+"Active");
		super.registerIcons(itemIcon);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
    {
		if(meta == 0)
		{
			return blockIcon;
		}
		else
		{
			return blockIconActive;
		}
    }
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		if(world.getBlockMetadata(x, y, z) == 1)
		{
			//dropBlockAsItem(world, x, y, z, Elysium.GracePrismItem.itemID, 0);
			player.dropItem(Elysium.GracePrismItem.itemID, 1);
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz)
    {
		if((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem().itemID == Elysium.GracePrismItem.itemID))
		{
			player.getCurrentEquippedItem().damageItem(1, player);
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		}
		return false;
    }
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		if((world.getBlockMetadata(x, y, z) == 1) && (canStayPortal(world, x, y, z)) && (entity.riddenByEntity == null) && (entity.ridingEntity == null) && (entity instanceof EntityPlayerMP))
		{
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
	
	public static boolean canStayPortal(World world, int x, int y, int z)
	{
		boolean canstay = true;
		
		for(int i=-2; i<=2; i++)
		{
			for(int j=-2; j<=2; j++)
			{
				if(world.getBlockId(x+i, y-2, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-2, z+j) != 0) canstay = false;
				if(world.getBlockId(x+i, y-8, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-8, z+j) != 0) canstay = false;
			}
		}
		for(int i=-1; i<=1; i++)
		{
			for(int j=-1; j<=1; j++)
			{
				if(world.getBlockId(x+i, y-1, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-1, z+j) != 1) canstay = false;
				
				if(world.getBlockId(x+i, y-3, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-3, z+j) != 2) canstay = false;
				if(world.getBlockId(x+i, y-4, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-4, z+j) != 2) canstay = false;
				
				if(world.getBlockId(x+i, y-5, z+j) != Block.blockGold.blockID) canstay = false;
				
				if(world.getBlockId(x+i, y-6, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-6, z+j) != 2) canstay = false;
				if(world.getBlockId(x+i, y-7, z+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(world.getBlockMetadata(x+i, y-7, z+j) != 2) canstay = false;
			}
		}
		
		return canstay;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new ElysiumTileEntityPortal();
	}
}
