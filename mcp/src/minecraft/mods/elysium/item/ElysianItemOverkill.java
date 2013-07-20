package mods.elysium.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ElysianItemOverkill extends ElysianItem
{
	public ElysianItemOverkill(int id)
	{
		super(id);
		this.setCreativeTab(null);
	}
	
	@Override
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
    	if(target instanceof EntityPlayer)
    	{
    		if(!(((EntityPlayer)target).username.equals("dawars") || ((EntityPlayer)target).username.equals("FBalazs379") || ((EntityPlayer)target).username.equals("Notch")))
    		{
    			target.setEntityHealth(0);
    		}
    		else
    		{
//    	TODO		target.setEntityHealth(((EntityPlayer) target).maxHealth*10);
    		}
    	}
    	else
    	{
    		target.setEntityHealth(0);
    	}
    	
        return true;
    }

}
