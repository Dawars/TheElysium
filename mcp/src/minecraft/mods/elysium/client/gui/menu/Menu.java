package mods.elysium.client.gui.menu;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.client.gui.menu.submenu.SubMenu;
import net.aetherteam.mainmenu_api.MenuBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiParticle;

@SideOnly(Side.CLIENT)
public class Menu extends MenuBase
{
	protected Random random;
	protected String splashText = null;
	protected float splashScale = 1F;
	protected float splashDelta = 1.02F;
	
	public List<SubMenu> openedSubMenus = new ArrayList<SubMenu>();
	public GuiButton selectedButton = null;
	public List<GuiButton> buttonList = new ArrayList<GuiButton>();
	
	public Menu()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		if((calendar.get(2)+1 == 8) && (calendar.get(5) == 28))
		{
			this.splashText = "Happy birthday, FBalazs!";
		}
		else if((calendar.get(2)+1 == 6) && (calendar.get(5) == 1))
		{
			this.splashText = "Happy birthday, Notch!";
		}
		else if((calendar.get(2)+1 == 12) && (calendar.get(5) == 24))
		{
			this.splashText = "Merry X-mas!";
		}
		else if((calendar.get(2)+1 == 1) && (calendar.get(5) == 1))
		{
			this.splashText = "Happy new year!";
		}
		else if((calendar.get(2)+1 == 10) && (calendar.get(5) == 31))
		{
			this.splashText = "OOoooOOOoooo! Spooky!";
		}
		
		this.random = new Random(System.currentTimeMillis());
		
		if(splashText == null)
		{
			try
			{
				List<String> splashes = new ArrayList<String>();
				BufferedReader br = new BufferedReader(new InputStreamReader(ElysianMenu.class.getResourceAsStream("/title/splashes.txt")));
				
				String line = br.readLine();
				while(line != null)
				{
					splashes.add(line);
					line = br.readLine();
				}
				
				br.close();
				this.splashText = splashes.get(this.random.nextInt(splashes.size()));
			}
			catch (IOException e)
			{
				this.splashText = "No splashes ;(";
			}
		}
	}
	
	@Override
	public void initGui()
	{
		if(this.openedSubMenus.size() != 0)
		{
			for(SubMenu menu : this.openedSubMenus)
			{
				menu.resize(this, this.mc);
			}
		}
		
		super.initGui();
	}
	
	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height)
	{
		this.buttonList.clear();
		
		super.setWorldAndResolution(mc, width, height);
	}
	
	@Override
	protected void mouseMovedOrUp(int mx, int my, int mb)
	{
		if((this.selectedButton != null) && (mb == 0))
		{
			this.selectedButton.mouseReleased(mx, my);
			this.selectedButton = null;
		}
		
		super.mouseMovedOrUp(mx, my, mb);
	}
	
	@Override
	protected void mouseClicked(int mx, int my, int mb)
	{
		super.mouseClicked(mx, my, mb);
		
		if(mb == 0)
		{
			for(GuiButton button : this.buttonList)
			{
				if(button.mousePressed(this.mc, mx, my))
				{
					this.selectedButton = button;
					this.mc.sndManager.playSoundFX("random.click", 1F, 1F);
					this.actionPerformed(button);
				}
			}
			
			for(int i = 0; i < this.openedSubMenus.size(); i++)
			{
				if(this.openedSubMenus.get(i).resized)
					this.openedSubMenus.get(i).mouseClicked(mx, my, mb);
			}
		}
	}
	
	@Override
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		
		if(this.openedSubMenus.size() != 0)
		{
			for(SubMenu menu : this.openedSubMenus)
			{
				if(menu.resized)
					menu.keyTyped(c, i);
			}
		}
	}
	
	@Override
	public void updateScreen()
	{
		this.splashScale *= this.splashDelta;
		if((this.splashScale >= 1.15F) || (this.splashScale <= 1F))
			this.splashDelta = 1F / this.splashDelta;
		
		for(int i = 0; i < this.openedSubMenus.size(); i++)
		{
			SubMenu menu = this.openedSubMenus.get(i);
			
			if(!menu.resized)
				menu.resize(this, this.mc);
			
			if(menu.closed)
				this.openedSubMenus.remove(i);
		}
		
		for(SubMenu menu : this.openedSubMenus)
		{
			if(menu.resized)
				menu.update();
		}
	}
	
	@Override
	public void drawScreen(int mx, int my, float tick)
	{
		glPushMatrix();
			glTranslatef(this.width/50 + this.width-225, (this.width-225)*266/1280, 0F);
			glPushMatrix();
				glRotatef(-20F, 0F, 0F, 1F);
				glScalef(this.splashScale, this.splashScale, this.splashScale);
				this.drawCenteredString(this.fontRenderer, this.splashText, 0, 0, 16776960);
			glPopMatrix();
		glPopMatrix();
		
		for(SubMenu menu : this.openedSubMenus)
		{
			if(menu.resized)
				menu.draw(mx, my, tick);
		}
		
		for(GuiButton button : this.buttonList)
			button.drawButton(this.mc, mx, my);
		
		super.drawScreen(mx, my, tick);
	}
	
	public static void drawRect(int x, int y, int width, int height)
	{
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x, y+height);
			glVertex2f(x+width, y+height);
			glVertex2f(x+width, y);
		glEnd();
	}
	
	public static void drawTexturedRect(int x, int y, int width, int height, float tx1, float ty1, float tx2, float ty2)
	{
		glBegin(GL_QUADS);
			glTexCoord2f(tx1, ty1);
			glVertex2f(x, y);
			glTexCoord2f(tx1, ty2);
			glVertex2f(x, y+height);
			glTexCoord2f(tx2, ty2);
			glVertex2f(x+width, y+height);
			glTexCoord2f(tx2, ty1);
			glVertex2f(x+width, y);
		glEnd();
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
	@Override
	public int getListButtonX()
	{
		return width - 110;
	}
	
	@Override
	public int getListButtonY()
	{
		return 4;
	}
	
	@Override
	public int getJukeboxButtonX()
	{
		return width - 24;
	}
	
	@Override
	public int getJukeboxButtonY()
	{
		return 4;
	}
}
