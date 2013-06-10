package mods.elysium.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ElysianItemOverkill extends ElysianItem
{
	public ElysianItemOverkill(int id)
	{
		super(id);
	}
	
	@Override
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving target, EntityLiving attacker)
    {
    	if(target instanceof EntityPlayer)
    	{
    		if(!((EntityPlayer) target).capabilities.isCreativeMode)
    			target.setEntityHealth(0);
    	}
    	else
    	{
    		target.setEntityHealth(0);
    	}
    	
        return true;
    }

}
