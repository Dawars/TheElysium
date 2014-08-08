package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.api.ElysiumApi;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;

public class EntityPinkUnicorn extends EntityHorse
{

	public EntityPinkUnicorn(World world)
	{
		super(world);
	}
	
	@Override
    public EnumCreatureAttribute getCreatureAttribute()
	{
		return ElysiumApi.MOB;
	}
}