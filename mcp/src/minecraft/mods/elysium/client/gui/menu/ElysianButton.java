package mods.elysium.client.gui.menu;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ElysianButton extends GuiButton
{
	private String textAlign = "left";
	
	public ElysianButton(int id, int x, int y, int width, int height, String name)
	{
		super(id, x, y, width, height, name);
	}
	
	public ElysianButton(int id, int x, int y, int width, int height, String name, String textAlign)
	{
		super(id, x, y, width, height, name);
		this.textAlign = textAlign;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mx, int my)
	{
		if(this.drawButton)
		{
			boolean isMouseOver = (mx >= this.xPosition) && (mx < this.xPosition+this.width) && (my >= this.yPosition) && (my < this.yPosition+this.height);
			
			glBindTexture(GL_TEXTURE_2D, 0);
			
			int textColor;
			if(!this.enabled)
			{
				glColor4f(0.5F, 0.5F, 0.5F, 0.5F);
				textColor = 14737632;
			}
			else if(isMouseOver)
			{
				glColor4f(0.5F, 0.5F, 0.5F, 0.5F);
				textColor = 16777120;
			}
			else
			{
				glColor4f(0F, 0F, 0F, 0.5F);
				textColor = -6250336;
			}
			
			Menu.drawRect(this.xPosition, this.yPosition, this.width, this.height);
			glBindTexture(GL_TEXTURE_2D, mc.renderEngine.getTexture("/font/default.png"));
			if(this.textAlign.equals("left"))
			{
				mc.fontRenderer.drawString(this.displayString, this.xPosition + this.height/2 - 4, this.yPosition + this.height/2 - 4, textColor);
			}
			else if(this.textAlign.equals("right"))
			{
				mc.fontRenderer.drawString(this.displayString, this.xPosition+this.width-mc.fontRenderer.getStringWidth(this.displayString) - this.height/2 + 4, this.yPosition + this.height/2 - 4, textColor);
			}
			else
			{
				mc.fontRenderer.drawString(this.displayString, this.xPosition + this.width/2 - mc.fontRenderer.getStringWidth(this.displayString)/2, this.yPosition + this.height/2 - 4, textColor);
			}
		}
	}
}
