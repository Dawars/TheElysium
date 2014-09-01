package me.dawars.CraftingPillars.api.sentry;

import java.util.HashMap;
import java.util.Map;

import me.dawars.CraftingPillars.api.FreezerRecipes.LiquidRecipe;
import net.minecraft.item.Item;

import cpw.mods.fml.common.FMLLog;

public class SentryBehaviors {
	
	/** Registry for all sentry behaviors. */
	public static Map<String, IBehaviorSentryItem> sentryBehaviorRegistry = new HashMap<String, IBehaviorSentryItem>();

	/**
	 * Use this method to register a new behavior for an item/block.
	 * @param item - the item of the projectile or weapon
	 * @param behavior - The behavior for the projectile or weapon - must extend SentryDefaultProjectile
	 */	
	public static void add(Item item, Object behavior)
	{
		if(behavior instanceof IBehaviorSentryItem)
		{
			sentryBehaviorRegistry.put(item.getUnlocalizedName(), (IBehaviorSentryItem)behavior);
		} else {
			FMLLog.warning("[CraftingPillar]: Couldn't register " + behavior.toString() + "! It has to implement IBehaviorSentryItem!");
		}
	}

	/**
	 * Gets the behavior for the item
	 * @param item - item of the weapon/projectile
	 * @return - behavior for the weapon/projectile
	 */
	public static IBehaviorSentryItem get(Item item)
	{
		return (IBehaviorSentryItem) sentryBehaviorRegistry.get(item.getUnlocalizedName());
	}
}
