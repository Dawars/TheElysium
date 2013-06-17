package mods.elysium.client.gui.menu.submenu;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveFormat;
import mods.elysium.client.gui.menu.*;

public class SubMenuCreateWorld extends SubMenu
{
	private GuiTextField textboxWorldName;
	private GuiTextField textboxSeed;
	
	private String folderName;
	private boolean generateStructures = true;
	private boolean commandsAllowed = false;
	private boolean bonusItems = false;
	private String seed;
	private int worldTypeId = 0;
	public String generatorOptionsToUse = "";
	
	private String gameMode = "survival";
	private String gameModeDescriptionLine1;
	private String gameModeDescriptionLine2;
	
	private String localizedNewWorldText;
	private static final String[] ILLEGAL_WORLD_NAMES = new String[] {"CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};
	
	@Override
	public void init()
	{
		StringTranslate translator = StringTranslate.getInstance();
		
		Keyboard.enableRepeatEvents(true);
		
		this.seed = "";
		this.localizedNewWorldText = translator.translateKey("selectWorld.newWorld");
		
		this.buttons.add(new ElysianButton(0, 80, this.displayHeight-24, 160, 16, translator.translateKey("selectWorld.create"), "center"));
		this.buttons.add(new ElysianButton(1, 80 + 164, this.displayHeight-24, 160, 16, translator.translateKey("gui.cancel"), "center"));
		
		this.buttons.add(new ElysianButton(2, 80, 156, 160, 16, translator.translateKey("selectWorld.gameMode"), "center"));
		this.buttons.add(new ElysianButton(3, 80 + 164, 156, 160, 16, translator.translateKey("selectWorld.bonusItems"), "center"));
		this.buttons.add(new ElysianButton(4, 80, 176, 160, 16, translator.translateKey("selectWorld.mapFeatures"), "center"));
		this.buttons.add(new ElysianButton(5, 80 + 164, 176, 160, 16, translator.translateKey("selectWorld.mapType"), "center"));
		this.buttons.add(new ElysianButton(6, 80, 196, 160, 16, translator.translateKey("selectWorld.allowCommands"), "center"));
		this.buttons.add(new ElysianButton(7, 80 + 164, 196, 160, 16, translator.translateKey("selectWorld.customizeType"), "center"));
		
		this.textboxWorldName = new GuiTextField(this.mc.fontRenderer, 80, 124, 160, 16);
		this.textboxWorldName.setFocused(true);
		this.textboxWorldName.setText(this.localizedNewWorldText);
		this.textboxSeed = new GuiTextField(this.mc.fontRenderer, 80 + 164, 124, 160, 16);
		this.textboxSeed.setText(this.seed);
		
		this.makeUseableName();
		this.updateButtonText();
	}
	
	@Override
	public void close()
	{
		super.close();
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public void update()
	{
		super.update();
		this.textboxWorldName.updateCursorCounter();
		this.textboxSeed.updateCursorCounter();
	}
	
	@Override
	public void draw(int mx, int my, float partialTick)
	{
		glBindTexture(GL_TEXTURE_2D, 0);
		glColor4f(0F, 0F, 0F, 0.5F);
		Menu.drawRect(76, 94, 332, this.displayHeight-98);
		
		StringTranslate translator = StringTranslate.getInstance();
		glBindTexture(GL_TEXTURE_2D, mc.renderEngine.getTexture("/font/default.png"));
		this.mc.fontRenderer.drawString(translator.translateKey("selectWorld.create"), 80 + 332/2 - this.mc.fontRenderer.getStringWidth(translator.translateKey("selectWorld.create"))/2, 98, 16777215);
		
		this.mc.fontRenderer.drawString(translator.translateKey("selectWorld.enterName"), 80 + 80 - this.mc.fontRenderer.getStringWidth(translator.translateKey("selectWorld.enterName"))/2, 114, 10526880);
		this.mc.fontRenderer.drawString(translator.translateKey("selectWorld.resultFolder") + " " + this.folderName, 80 + 80 - this.mc.fontRenderer.getStringWidth(translator.translateKey("selectWorld.resultFolder") + " " + this.folderName)/2, 142, 10526880);
		
		this.mc.fontRenderer.drawString(translator.translateKey("selectWorld.enterSeed"), 80 + 164 + 80 - this.mc.fontRenderer.getStringWidth(translator.translateKey("selectWorld.enterSeed"))/2, 114, 10526880);
		this.mc.fontRenderer.drawString(translator.translateKey("selectWorld.seedInfo"), 80 + 164 + 80 - this.mc.fontRenderer.getStringWidth(translator.translateKey("selectWorld.seedInfo"))/2, 142, 10526880);
		
		this.textboxWorldName.drawTextBox();
		this.textboxSeed.drawTextBox();
		glEnable(GL_BLEND);
		
		super.draw(mx, my, partialTick);
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			this.close();
			this.mc.displayGuiScreen((GuiScreen)null);
			
			long i = (new Random()).nextLong();
			String s = this.textboxSeed.getText();
			
			if(!MathHelper.stringNullOrLengthZero(s))
			{
				try
				{
					long j = Long.parseLong(s);
					if(j != 0L)
						i = j;
				}
				catch(NumberFormatException e)
				{
					i = (long)s.hashCode();
				}
			}
			
			WorldType.worldTypes[this.worldTypeId].onGUICreateWorldPress();
			EnumGameType enumgametype = EnumGameType.getByName(this.gameMode);
			WorldSettings worldsettings = new WorldSettings(i, enumgametype, this.generateStructures, this.gameMode.equals("hardcore"), WorldType.worldTypes[this.worldTypeId]);
			
			if(this.bonusItems /*&& !this.gameMode.equals("hardcore")*/)
			{
				worldsettings.enableBonusChest();
			}
			
			if(this.commandsAllowed /*&& !this.gameMode.equals("hardcore")*/)
			{
				worldsettings.enableCommands();
			}
			
			this.mc.launchIntegratedServer(this.folderName, this.textboxWorldName.getText().trim(), worldsettings);
		}
		else if(button.id == 1)
		{
			this.parent.submenus.add(new SubMenuSelectWorld());
			this.close();
		}
		else if(button.id == 2)
		{
			if(this.gameMode.equals("survival"))
			{
				this.gameMode = "hardcore";
				this.commandsAllowed = false;
				this.bonusItems = false;
				/*this.buttons.get(3).enabled = false;
				this.buttons.get(6).enabled = false;*/
			}
			else if(this.gameMode.equals("hardcore"))
			{
				this.gameMode = "creative";
				this.commandsAllowed = true;
				/*this.buttons.get(3).enabled = true;
				this.buttons.get(6).enabled = true;*/
			}
			else if(this.gameMode.equals("creative"))
			{
				this.gameMode = "survival";
				this.commandsAllowed = false;
			}
			
			this.updateButtonText();
		}
		else if(button.id == 3)
		{
			this.bonusItems = !this.bonusItems;
			this.updateButtonText();
		}
		else if(button.id == 4)
		{
			this.generateStructures = !this.generateStructures;
			this.updateButtonText();
		}
		else if(button.id == 5)
		{
			this.worldTypeId++;
			if(this.worldTypeId >= WorldType.worldTypes.length)
				this.worldTypeId = 0;
			
			while((WorldType.worldTypes[this.worldTypeId] == null) || (!WorldType.worldTypes[this.worldTypeId].getCanBeCreated()))
			{
				this.worldTypeId++;
				if(this.worldTypeId >= WorldType.worldTypes.length)
					this.worldTypeId = 0;
			}
			
			this.generatorOptionsToUse = "";
			this.updateButtonText();
		}
		else if(button.id == 6)
		{
			this.commandsAllowed = !this.commandsAllowed;
			this.updateButtonText();
		}
		else if(button.id == 7)
		{
			//custom
			this.updateButtonText();
		}
	}
	
	@Override
	public void onMousePressed(int mx, int my, int mb)
	{
		super.onMousePressed(mx, my, mb);
		this.textboxWorldName.mouseClicked(mx, my, mb);
		this.textboxSeed.mouseClicked(mx, my, mb);
	}
	
	@Override
	public void onKeyPressed(char c, int i)
	{
		super.onKeyPressed(c, i);
		if(this.textboxWorldName.isFocused())
		{
			this.textboxWorldName.textboxKeyTyped(c, i);
			this.localizedNewWorldText = this.textboxWorldName.getText();
			this.buttons.get(0).enabled = this.textboxWorldName.getText().length() > 0;
			this.makeUseableName();
		}
		else if(this.textboxSeed.isFocused())
		{
			this.textboxSeed.textboxKeyTyped(c, i);
			this.seed = this.textboxSeed.getText();
		}
		
		if(c == 13)
		{
			this.actionPerformed(this.buttons.get(0));
		}
	}
	
	private void makeUseableName()
	{
		this.folderName = this.textboxWorldName.getText().trim();
		char[] achar = ChatAllowedCharacters.allowedCharactersArray;
		
		for(int i = 0; i < achar.length; i++)
		{
			char c = achar[i];
			this.folderName = this.folderName.replace(c, '_');
		}
		
		if(MathHelper.stringNullOrLengthZero(this.folderName))
		{
			this.folderName = "World";
		}
		
		this.folderName.replaceAll("[\\./\"]", "_");
		
		for(int i = 0; i < ILLEGAL_WORLD_NAMES.length; i++)
		{
			if(ILLEGAL_WORLD_NAMES[i].equals(this.folderName))
				this.folderName = "_"+this.folderName+"_";
		}
		
		while(this.mc.getSaveLoader().getWorldInfo(this.folderName) != null)
		{
			this.folderName += "-";
		}
	}
	
	private void updateButtonText()
	{
		StringTranslate translator = StringTranslate.getInstance();
		this.buttons.get(2).displayString = translator.translateKey("selectWorld.gameMode") + " " + translator.translateKey("selectWorld.gameMode." + this.gameMode);
		this.gameModeDescriptionLine1 = translator.translateKey("selectWorld.gameMode." + this.gameMode + ".line1");
		this.gameModeDescriptionLine2 = translator.translateKey("selectWorld.gameMode." + this.gameMode + ".line2");
		
		this.buttons.get(3).displayString = translator.translateKey("selectWorld.bonusItems") + " ";
		if(this.bonusItems /*&& !this.gameMode.equals("hardcore")*/)
		{
			this.buttons.get(3).displayString += translator.translateKey("options.on");
		}
		else
		{
			this.buttons.get(3).displayString += translator.translateKey("options.off");
		}
		
		this.buttons.get(4).displayString = translator.translateKey("selectWorld.mapFeatures") + " ";
		if(this.generateStructures)
		{
			this.buttons.get(4).displayString += translator.translateKey("options.on");
		}
		else
		{
			this.buttons.get(4).displayString += translator.translateKey("options.off");
		}
		
		this.buttons.get(5).displayString = translator.translateKey("selectWorld.mapType") + " " + translator.translateKey(WorldType.worldTypes[this.worldTypeId].getTranslateName());
		
		this.buttons.get(6).displayString = translator.translateKey("selectWorld.allowCommands") + " ";
		if(this.commandsAllowed /*&& !this.gameMode.equals("hardcore")*/)
		{
			this.buttons.get(6).displayString += translator.translateKey("options.on");
		}
		else
		{
			this.buttons.get(6).displayString += translator.translateKey("options.off");
		}
	}
}
