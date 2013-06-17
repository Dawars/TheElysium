package mods.elysium.client.gui.menu.submenu;

import java.util.ArrayList;
import java.util.List;

import mods.elysium.client.gui.menu.Menu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public abstract class SubMenu
{
	protected Menu parent;
	protected Minecraft mc;
	public int width, height;
	public List<GuiButton> buttons = new ArrayList<GuiButton>();
	public List<SubMenu> submenus = new ArrayList<SubMenu>();
	public boolean closed = false;
	public boolean resized = false;
	
	public void resize(Menu parent, Minecraft mc)
	{
		this.resized = true;
		this.parent = parent;
		this.mc = mc;
		this.width = mc.displayWidth/2;
		this.height = mc.displayHeight/2;
		this.buttons.clear();
		this.submenus.clear();
		this.init();
		for(SubMenu sub : this.submenus)
			sub.resize(this.parent, mc);
	}
	
	public void init(){}
	
	public void close()
	{
		for(SubMenu sub : this.submenus)
			sub.close();
		this.closed = true;
	}
	
	public void update()
	{
		for(SubMenu sub : this.submenus)
			sub.update();
	}
	
	public void draw(int mx, int my, float partialTick)
	{
		for(GuiButton button : this.buttons)
			button.drawButton(this.mc, mx, my);
		
		for(SubMenu sub : this.submenus)
			sub.draw(mx, my, partialTick);
	}
	
	public void actionPerformed(GuiButton button){}
	
	public void mouseClicked(int mx, int my, int mb)
	{
		if(mb == 0)
		{
			for(GuiButton button : this.buttons)
			{
				if(button.mousePressed(this.mc, mx, my))
				{
					this.mc.sndManager.playSoundFX("random.click", 1F, 1F);
					this.actionPerformed(button);
				}
			}
		}
		
		for(SubMenu sub : this.submenus)
			sub.mouseClicked(mx, my, mb);
	}
	
	public void keyTyped(char c, int i)
	{
		for(SubMenu sub : this.submenus)
			sub.keyTyped(c, i);
	}
}
