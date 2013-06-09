package mods.elysium;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.aetherteam.mainmenu_api.MenuBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreenOnlineServers;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MenuBaseElysium extends MenuBase
{
public void initGui()
{
         super.initGui(); // REMEMBER TO USE SUPER
        
         // ADD BUTTONS HERE
         //
//          Example: 
        	  buttonList.add(new GuiButton(0, 50, 50, 80, 20, "Make A Menu Base"));
}

protected void actionPerformed(GuiButton par1GuiButton)
{
         // EXECUTE CODE BASED ON BUTTON ID
         if (par1GuiButton.id == 0)
         {
                 this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
         }
}

public void drawScreen(int par1, int par2, float par3)
{
         // ADD YOUR RENDERING CODE HERE
        
         super.drawScreen(par1, par2, par3); // REMEMBER TO USE SUPER
}

public int getListButtonX()
{
return width - 110;
}

public int getListButtonY()
{
return 4;
}

public int getJukeboxButtonX()
{
return width - 24;
}

public int getJukeboxButtonY()
{
return 4;
}

public String getName()
{
return "Elysium Menu";
}

public String getVersion()
{
return "1.0.0";
}

public String getMusicFileName()
{
return "Strad";
}

public String getIconPath()
{
return "/net/aetherteam/mainmenu_api/icons/minecraft.png";
}
}