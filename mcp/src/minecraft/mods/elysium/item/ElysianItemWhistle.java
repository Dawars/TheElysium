package mods.elysium.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import mods.elysium.ShuffleBag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
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

public class ElysianItemWhistle extends ElysianItem{
	
	//Add API support
//	public static ArrayList<String> tips = new ArrayList<String>();
   public static ShuffleBag shuffleBag = new ShuffleBag();

	public ElysianItemWhistle(int id) {
		super(id);
        this.maxStackSize = 9;
        this.setMaxDamage(9*20);
        
        shuffleBag.Add("Hmm... looks like a musical instrument");
        shuffleBag.Add("Its sound could be heared from a long distance...");
        shuffleBag.Add("Sorry, I can use it now!");

	}
	
	
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {
		entity.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));

        return itemStack;
    }
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean flag) {
    	if(world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUse() == item)
    		item.damageItem(2, (EntityPlayer)entity);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entity, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "mods.elysium.sound.FluteTrack", 3.0F, itemRand.nextFloat() * 0.1F + 0.9F);
    	
    	if(world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd){

                EntityDragon entitydragon = new EntityDragon(world);
                entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, new Random().nextFloat() * 360.0F, 0.0F);
               	world.spawnEntityInWorld(entitydragon);
               	
                
    	} else {
    		if(!world.isRemote){
    			entity.sendChatToPlayer(shuffleBag.Next());
    		}
    	}
        return false;
    }
    
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 9*20;
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
}
