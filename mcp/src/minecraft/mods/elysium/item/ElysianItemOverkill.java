package mods.elysium.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class ElysianItemOverkill extends ElysianItem{

	public ElysianItemOverkill(int id) {
		super(id);
	}
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving target, EntityLiving attacker)
    {
    	target.setEntityHealth(0);
        return true;
    }

}
