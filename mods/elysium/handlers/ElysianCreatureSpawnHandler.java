package mods.elysium.handlers;

import mods.elysium.Elysium;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class ElysianCreatureSpawnHandler
{
	@ForgeSubscribe
    public void CheckSpawn(LivingSpawnEvent event)
    {
       	if(event.world.provider.dimensionId == Elysium.DimensionID){
       		if(event.entity instanceof EntitySquid || event.entity instanceof EntityBat){
				event.setResult(Result.DENY);
       		}
       	}
    }
}