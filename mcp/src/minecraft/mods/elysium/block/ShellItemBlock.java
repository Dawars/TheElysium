package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ShellItemBlock extends ItemBlock {
	
	@SideOnly(Side.CLIENT)
	private Icon iconConch;
	
	@SideOnly(Side.CLIENT)
	private Icon iconShell;
	
	public ShellItemBlock(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName( DefaultProps.modId + "-shell");
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	@SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int meta)
    {
		Icon icon = null;
    	if(meta == 0)
    		return this.iconConch;
    	else
    		return this.iconShell;
    				
    }
    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister IconRegister)
    {
    	this.iconConch = IconRegister.registerIcon(DefaultProps.modId + ":conch");
    	this.iconShell = IconRegister.registerIcon(DefaultProps.modId + ":shell");
    }
}