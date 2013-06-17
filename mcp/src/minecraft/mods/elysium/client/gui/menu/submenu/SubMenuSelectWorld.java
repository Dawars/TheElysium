package mods.elysium.client.gui.menu.submenu;

import static org.lwjgl.opengl.GL11.*;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import mods.elysium.client.gui.menu.ElysianButton;
import mods.elysium.client.gui.menu.Menu;

public class SubMenuSelectWorld extends SubMenu
{
	private List saveList;
	private int selectedWorld;
	private boolean selected = false;
	
	@Override
	public void init()
	{
		this.parent.buttonList.get(0).enabled = false;
		
		StringTranslate translator = StringTranslate.getInstance();
		this.buttons.add(new ElysianButton(0, 80, this.height-44, 128, 16, translator.translateKey("selectWorld.select"), "center"));
		this.buttons.get(0).enabled = false;
		this.buttons.add(new ElysianButton(1, 80+132, this.height-44, 128, 16, translator.translateKey("selectWorld.create"), "center"));
		this.buttons.add(new ElysianButton(2, 80, this.height-24, 62, 16, translator.translateKey("selectWorld.rename"), "center"));
		this.buttons.get(2).enabled = false;
		this.buttons.add(new ElysianButton(3, 80+66, this.height-24, 62, 16, translator.translateKey("selectWorld.delete"), "center"));
		this.buttons.get(3).enabled = false;
		this.buttons.add(new ElysianButton(4, 80+132, this.height-24, 62, 16, translator.translateKey("selectWorld.recreate"), "center"));
		this.buttons.get(4).enabled = false;
		this.buttons.add(new ElysianButton(5, 80+198, this.height-24, 62, 16, translator.translateKey("gui.cancel"), "center"));
		
		try
		{
			this.loadSaves();
		}
		catch(AnvilConverterException e)
		{
			e.printStackTrace();
			this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load words", e.getMessage()));
			return;
		}
	}
	
	@Override
	public void draw(int mx, int my, float partialTick)
	{
		glBindTexture(GL_TEXTURE_2D, 0);
		glColor4f(0F, 0F, 0F, 0.5F);
		Menu.drawRect(76, 94, 268, this.height-98);
		
		super.draw(mx, my, partialTick);
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			this.selectWorld(this.selectedWorld);
		}
		else if(button.id == 1)
		{
			this.parent.openedSubMenus.add(new SubMenuCreateWorld());
			this.close();
		}
		else if(button.id == 2)
		{
			
			this.mc.displayGuiScreen(new GuiRenameWorld(this.parent, this.getSaveFileName(this.selectedWorld)));
		}
		else if(button.id == 3)
		{
			/*String s = this.getSaveName(this.selectedWorld);
			if(s != null)
			{
				this.deleting = true;
				GuiYesNo guiyesno = getDeleteWorldScreen(this.parent, s, this.selectedWorld);
				this.mc.displayGuiScreen(guiyesno);
			}*/
		}
		else if(button.id == 4)
		{
			GuiCreateWorld guicreateworld = new GuiCreateWorld(this.parent);
			ISaveHandler isavehandler = this.mc.getSaveLoader().getSaveLoader(this.getSaveFileName(this.selectedWorld), false);
			WorldInfo worldinfo = isavehandler.loadWorldInfo();
			isavehandler.flush();
			guicreateworld.func_82286_a(worldinfo);
			this.mc.displayGuiScreen(guicreateworld);
		}
		else if(button.id == 5)
		{
			this.parent.buttonList.get(0).enabled = true;
			this.close();
		}
	}
	
	private void loadSaves() throws AnvilConverterException
	{
		ISaveFormat isaveformat = this.mc.getSaveLoader();
		this.saveList = isaveformat.getSaveList();
		Collections.sort(this.saveList);
		this.selectedWorld = -1;
	}
	
	protected String getSaveFileName(int i)
	{
		return ((SaveFormatComparator)this.saveList.get(i)).getFileName();
	}
	
	protected String getSaveName(int i)
	{
		String s = ((SaveFormatComparator)this.saveList.get(i)).getDisplayName();
		
		if((s == null) || MathHelper.stringNullOrLengthZero(s))
		{
			StringTranslate translator = StringTranslate.getInstance();
			s = translator.translateKey("selectWorld.world") + " " + (i + 1);
		}
		
		return s;
	}
	
	public void selectWorld(int i)
	{
		this.mc.displayGuiScreen((GuiScreen)null);
		
		if(!this.selected)
		{
			this.selected = true;
			
			String s = this.getSaveFileName(i);
			if(s == null)
			{
				s = "World"+i;
			}
			
			String s1 = this.getSaveName(i);
			if(s1 == null)
			{
				s1 = "World"+i;
			}
			
			if(this.mc.getSaveLoader().canLoadWorld(s))
			{
				this.mc.launchIntegratedServer(s, s1, (WorldSettings)null);
			}
		}
	}
}
