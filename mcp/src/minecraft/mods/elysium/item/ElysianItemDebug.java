package mods.elysium.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.dimension.portal.ElysianTeleporter;
import mods.elysium.entity.ElysianEntityDrachma;
import net.minecraft.client.entity.EntityClientPlayerMP;
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

		 Random random = new Random();
         int randomInt1 = random.nextInt();
         int randomInt2 = random.nextInt();
         if(world.isRemote){
        	 player.sendChatToPlayer("Client numbers: " + randomInt1 + ", " + randomInt2);
         }
         
         ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
         DataOutputStream outputStream = new DataOutputStream(bos);
         try {
                 outputStream.writeInt(randomInt1);
                 outputStream.writeInt(randomInt2);
         } catch (Exception ex) {
                 ex.printStackTrace();
         }
         
         Packet250CustomPayload packet = new Packet250CustomPayload();
         packet.channel = DefaultProps.NET_CHANNEL_NAME;
         packet.data = bos.toByteArray();
         packet.length = bos.size();
         
         Side side = FMLCommonHandler.instance().getEffectiveSide();
         if (side == Side.SERVER) {
                 // We are on the server side.
                 EntityPlayerMP player2 = (EntityPlayerMP) player;
         } else if (side == Side.CLIENT) {
                 // We are on the client side.
                 EntityClientPlayerMP player2 = (EntityClientPlayerMP) player;
                 player2.sendQueue.addToSendQueue(packet);
         } else {
                 // We are on the Bukkit server.
         }
		
		if(player.isSneaking() /*Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)*/ && (player instanceof EntityPlayerMP))
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
