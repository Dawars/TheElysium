package mods.elysium.client.gui.menu.submenu;

import static org.lwjgl.opengl.GL11.*;

public class SubMenuContainer extends SubMenu
{
	protected int x1, y1, x2, y2;
	
	public SubMenuContainer(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void draw(int mx, int my, float partialTick)
	{
		glViewport(this.x1, this.y1, this.x2-this.x1, this.y2-this.y1);
		
		
		
		super.draw(mx, my, partialTick);
		glViewport(0, 0, this.width, this.height);
	}
}
