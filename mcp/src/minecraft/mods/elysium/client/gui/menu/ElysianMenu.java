package mods.elysium.client.gui.menu;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.client.gui.menu.submenu.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ElysianMenu extends Menu
{
	public ElysianMenu()
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
		StringTranslate translator = StringTranslate.getInstance();
		
		int y = 94;
		this.buttonList.add(new ElysianButton(0, 0, y, 72, 16, translator.translateKey("menu.singleplayer")));
		this.buttonList.add(new ElysianButton(1, 0, y+20, 72, 16, translator.translateKey("menu.multiplayer")));
		
		this.buttonList.add(new ElysianButton(2, 0, y+50, 72, 16, "Mods"));
		this.buttonList.add(new ElysianButton(3, 0, y+70, 72, 16, translator.translateKey("menu.options")));
		
		this.buttonList.add(new ElysianButton(4, 0, y+100, 72, 16, translator.translateKey("menu.quit")));
		
		this.buttonList.add(new GuiButtonLanguage(5, this.width - 48, 4));
		
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.openedSubMenus.add(new SubMenuSelectWorld());
			//this.mc.displayGuiScreen(new GuiSelectWorld(this));
		}
		else if(button.id == 1)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		else if(button.id == 2)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.mc.displayGuiScreen(new GuiModList(this));
		}
		else if(button.id == 3)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}
		else if(button.id == 4)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.mc.shutdown();
		}
		
		else if(button.id == 5)
		{
			if(this.openedSubMenus.size() != 0)
			{
				for(SubMenu menu : this.openedSubMenus)
				{
					menu.close();
				}
			}
			this.openedSubMenus.clear();
			
			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings));
		}
	}
	
	@Override
	public void drawScreen(int mx, int my, float tick)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glColor4f(1F, 1F, 1F, 1F);
		
		glBindTexture(GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/elysium/textures/gui/menu/bg.png"));
		Menu.drawTexturedRect(0, 0, this.width, this.height, 0F, 0F, 1F, 1F);
		
		glBindTexture(GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/elysium/textures/gui/menu/title.png"));
		Menu.drawTexturedRect(this.width/50, 0, this.width-200, (this.width-200)*266/1280, 0F, 0F, 1F, 1F);
		
		super.drawScreen(mx, my, tick);
		
		glDisable(GL_BLEND);
	}
	
	@Override
	public String getName()
	{
		return "Elysian Menu";
	}
	
	@Override
	public String getVersion()
	{
		return Elysium.version;
	}
	
	@Override
	public String getMusicFileName()
	{
		return "Uranus Paradise Short";
	}
	
	@Override
	public String getIconPath()
	{
		return "/mods/elysium/textures/gui/menu/icon.png";
	}
	
	@Override
	public String getJukeboxBackgroundPath()
	{
		return "/mods/elysium/textures/blocks/elysium_dirt.png";
	}
}