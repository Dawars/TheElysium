package mods.elysium.client.gui.menu.submenu;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

import mods.elysium.client.gui.menu.Menu;

public class SubMenuContainer extends SubMenu
{
	protected int x, y, width, height;
	protected float sliderpos = 0F;
	public List<SubMenuContainerElement> elements = new ArrayList<SubMenuContainerElement>();
	protected int listHeight = 0;
	
	public SubMenuContainer(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void resize(Menu parent, Minecraft mc)
	{
		this.elements.clear();
		super.resize(parent, mc);
		this.listHeight = 0;
		for(SubMenuContainerElement element : this.elements)
			this.listHeight += element.getHeight();
	}
	
	@Override
	public void draw(int mx, int my, float partialTick)
	{
		glBindTexture(GL_TEXTURE_2D, 0);
		glColor4f(0F, 0F, 0F, 0.5F);
		Menu.drawRect(this.x, this.y, this.width, this.height);
		
		super.draw(mx, my, partialTick);
	}
	
	@Override
	public void onMousePressed(int mx, int my, int mb)
	{
		super.onMousePressed(mx, my, mb);
	}
}
