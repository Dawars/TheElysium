package mods.elysium.items;

import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.logging.ILogAgent;
import net.minecraft.network.NetworkListenThread;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.transformers.ForgeAccessTransformer;

public class ItemWhistle extends ElysiumItem{

	public ItemWhistle(int id) {
		super(id);
        this.maxStackSize = 1;
        this.setMaxDamage(4);
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {
    	if(world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd){

                EntityDragon entitydragon = new EntityDragon(world);
                entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, new Random().nextFloat() * 360.0F, 0.0F);
               	world.spawnEntityInWorld(entitydragon);
    	} else {
    		if(!world.isRemote)
    			entity.sendChatToPlayer("It look like it can call a Dragon in The End...");
    	}
    	itemStack.damageItem(1, entity);

        return itemStack;
    }

}
