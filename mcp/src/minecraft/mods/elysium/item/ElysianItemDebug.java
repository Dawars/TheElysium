package mods.elysium.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.dimension.portal.ElysianTeleporter;
import mods.elysium.entity.ElysianEntityDrachma;
import mods.elysium.network.PacketRandom;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianItemDebug extends ElysianItem
{
	public ElysianItemDebug(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		world.spawnEntityInWorld(new ElysianEntityDrachma(world, x+hitX, y+hitY, z+hitZ, new Random().nextInt(10)+10));
		
		if(!world.isRemote)
		{
			player.sendChatToPlayer("Id: "+world.getBlockId(x, y, z));
			player.sendChatToPlayer("Metadata: "+world.getBlockMetadata(x, y, z));
			player.sendChatToPlayer("TileEntity: "+world.getBlockTileEntity(x, y, z));
		}
		
        return false;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(world.isRemote)
		{
			int randomNum = new Random().nextInt();
			
			player.sendChatToPlayer("Sending packet to server: " + randomNum);
			PacketDispatcher.sendPacketToServer(new PacketRandom(randomNum).getPacket());
		}
		if(player.isSneaking() && (player instanceof EntityPlayerMP))
		{
			EntityPlayerMP playermp = (EntityPlayerMP)player;
			if(playermp.dimension == Elysium.DimensionID)
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, 0, new ElysianTeleporter(playermp.mcServer.worldServerForDimension(0)));
			}
			else
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, Elysium.DimensionID, new ElysianTeleporter(playermp.mcServer.worldServerForDimension(Elysium.DimensionID)));
			}
		}
		return stack;
    }
}
