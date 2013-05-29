package mods.elysium.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class OverkillItem extends ElysiumItem{

	public OverkillItem(int id) {
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
