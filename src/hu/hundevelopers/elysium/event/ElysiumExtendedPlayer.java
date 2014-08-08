package hu.hundevelopers.elysium.event;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLCommonHandler;
import me.dawars.rememberme.Log;
import me.dawars.rememberme.api.RememberMeAPI.FightStyle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ElysiumExtendedPlayer implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "Elysium";
	private boolean isHero = false;

	public ElysiumExtendedPlayer(EntityPlayer player) { }

	@Override
	public void init(Entity entity, World world)
	{
		Log.log("Elysium Extended player init " + FMLCommonHandler.instance().getEffectiveSide());
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();

		properties.setBoolean("isHero", this.isHero);
		compound.setTag(EXT_PROP_NAME, properties);

	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		if (properties != null) {
			this.isHero = properties.getBoolean("isHero");
			
		} else {
			
		}
		
		Log.log("Extended player load " + FMLCommonHandler.instance().getEffectiveSide());
	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event This method is for convenience only; it will
	 * make your code look nicer
	 */
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ElysiumExtendedPlayer.EXT_PROP_NAME, new ElysiumExtendedPlayer(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player This method is for
	 * convenience only; it will make your code look nicer
	 */
	public static final ElysiumExtendedPlayer get(EntityPlayer player)
	{
		return (ElysiumExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);

	}
	
	public boolean isHero()
	{
		return isHero;
	}
}