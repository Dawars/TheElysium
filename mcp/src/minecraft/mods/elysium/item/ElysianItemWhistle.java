package mods.elysium.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.ShuffleBag;
import mods.elysium.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
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

public class ElysianItemWhistle extends ElysianItem
{
	
	private static Random rand = new Random();
	
	//Add API support
	public static ArrayList<String> dragonExist = new ArrayList<String>();
	public static ShuffleBag shuffleBag = new ShuffleBag();

	public ElysianItemWhistle(int id)
	{
		super(id);
        this.maxStackSize = 9;
        this.setMaxDamage(9*20);
        
        shuffleBag.Add("Hmm... looks like a musical instrument");
        shuffleBag.Add("Its sound could be heared from a long distance...");
        shuffleBag.Add("Sorry, I can use it now!");
        
        dragonExist.add("Looks like there is a dragon somewhere...");
        dragonExist.add("Oh! There is the Ender Dragon!");
        dragonExist.add("There can't be more dragons in this dimension...");
        dragonExist.add("There should be a dragon somewhere here...");
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {
		Elysium.soundWhistle.play();
    	if(CommonProxy.proxy.isSimulating(world)){
			
	    	if(world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd )
	    	{
	    		if(isDragonAlive(world) < Elysium.MaxDragon){
	    			EntityDragon entitydragon = new EntityDragon(world);
	    			entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, rand.nextFloat() * 360.0F, 0.0F);
	    			world.spawnEntityInWorld(entitydragon);
	    		} else {
					entity.sendChatToPlayer(dragonExist.get(rand.nextInt(dragonExist.size())));
	    		}
	    	}
	    	else
	    	{
	    		if(!world.isRemote)
	    		{
	    			entity.sendChatToPlayer(shuffleBag.Next());
	    		}
	    	}
    	}
    	entity.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    private int isDragonAlive(World world) {
    	List list = world.getLoadedEntityList();
    	int dragonNum = 0;
    	
    	for (int i = 0; i < list.size(); i++) {
    		if(list.get(i) instanceof EntityDragon)
    			dragonNum++;
    	}
		
    	return dragonNum;
	}

	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean flag)
    {
    	if(world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUse() == item)
    		item.damageItem(2, (EntityPlayer)entity);
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
