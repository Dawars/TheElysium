package mods.elysium.client.gui.menu;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import mods.elysium.Elysium;
import net.aetherteam.mainmenu_api.MenuBase;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MiscElysianMenu extends MenuBase
{
	@Override
	public void initGui()
	{	
		super.initGui();
		MiscRenderer.load();
		for(int i = 0; i < 64; i++)
		{
			for(int k = 0; k < 64; k++)
			{
				MiscWorld.setBlockId(i, 0, k, Elysium.blockGrass.blockID);
			}
		}
		
		MiscWorld.setBlockId(31, 1, 16, Elysium.blockWorkbench.blockID);
		MiscWorld.setBlockId(31, 1, 17, Elysium.blockCobalt.blockID);
		MiscWorld.setBlockId(32, 1, 17, Elysium.blockLogFostimber.blockID);
		MiscWorld.setBlockMetadata(32, 1, 17, 5);
		
		//buttonList.add(new ElysianButton(0, 50, 50, 100, 300, "Options"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}
	}
	
	private float eyeHeight = 1.8F;
	private Vector3f pos = new Vector3f(32F, 0.5F, 32F);
	private Vector3f ppos = new Vector3f(32F, 0.5F, 32F);
	private Vector3f rot = new Vector3f(0F, 0F, 0F);
	private Vector3f prot = new Vector3f(0F, 0F, 0F);
	private final float playerSpeed = 5F;
	private int fps;
	private long ptime;
	private float mx, my;
	static float fov = 60F;
	
	@Override
	public void updateScreen()
	{
		System.out.println(fps);
		this.ppos = this.pos;
		this.prot = this.rot;
		
		if(Mouse.isButtonDown(0))
			Mouse.setGrabbed(true);
		
		if(Mouse.isButtonDown(1))
			Mouse.setGrabbed(false);
		
		if(Mouse.isGrabbed())
		{
			this.mx = Mouse.getDX()*0.16F*2F;
			this.my = Mouse.getDY()*0.16F*2F;
			
			this.rot.y += mx;
			if(this.rot.y >= 360F) this.rot.y -= 360F;
			if(this.rot.y < 0F) this.rot.y += 360F;
			
			this.rot.x -= my;
			if(this.rot.x < -85F) this.rot.x = -85F;
			if(this.rot.x > 85F) this.rot.x = 85F;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W))
			{
				this.pos.x += Math.sin(Math.toRadians(this.rot.y)) * this.playerSpeed/20;
				this.pos.z -= Math.cos(Math.toRadians(this.rot.y)) * this.playerSpeed/20;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A))
			{
				this.pos.x += Math.sin(Math.toRadians(this.rot.y-90F)) * this.playerSpeed/20;
				this.pos.z -= Math.cos(Math.toRadians(this.rot.y-90F)) * this.playerSpeed/20;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				this.pos.x += Math.sin(Math.toRadians(this.rot.y+90F)) * this.playerSpeed/20;
				this.pos.z -= Math.cos(Math.toRadians(this.rot.y+90F)) * this.playerSpeed/20;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				this.pos.x -= Math.sin(Math.toRadians(this.rot.y)) * this.playerSpeed/20;
				this.pos.z += Math.cos(Math.toRadians(this.rot.y)) * this.playerSpeed/20;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
				this.eyeHeight = 1.5F;
			else
				this.eyeHeight = 1.8F;
		}
		
		if(this.pos.x < 0F)
			this.pos.x = 0F;
		
		if(this.pos.x > 63F)
			this.pos.x = 63F;
		
		if(this.pos.z < 0F)
			this.pos.z = 0F;
		
		if(this.pos.z > 63F)
			this.pos.z = 63F;
		
		
		if(MiscWorld.getBlockId(Math.round(this.pos.x), Math.round(this.pos.y), Math.round(this.pos.z)) != 0)
		{
			System.out.println("auch");
			//if(MiscWorld.getBlockId(Math.round(this.pos.x), Math.round(this.pos.y), Math.round(this.ppos.z)) != 0)
				this.pos.x -= (this.pos.x-this.ppos.x)*2;
			
			//if(MiscWorld.getBlockId(Math.round(this.ppos.x), Math.round(this.pos.y), Math.round(this.pos.z)) != 0)
				this.pos.z -= (this.pos.z-this.ppos.z)*2;
		}
	}
	
	@Override
	public void drawScreen(int mx, int my, float partialTick)
	{
		this.fps = (int) (1000 / (System.currentTimeMillis()-this.ptime));
		this.ptime = System.currentTimeMillis();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float)Display.getDisplayMode().getWidth() / (float)Display.getDisplayMode().getHeight(), 0.1F, MiscRenderer.renderDistance*1.75F);
		glMatrixMode(GL_MODELVIEW);
		
		glLoadIdentity();
		
		glRotatef(this.rot.x + (this.rot.x-this.prot.x)*partialTick, 1F, 0F, 0F);
		glRotatef(this.rot.y + (this.rot.y-this.prot.y)*partialTick, 0F, 1F, 0F);
		glRotatef(this.rot.z + (this.rot.z-this.prot.z)*partialTick, 0F, 0F, 1F);
		glColor3f(0.25F, 0.25F, 0.75F);
		glBindTexture(GL_TEXTURE_2D, 0);
		glCallList(MiscRenderer.displayListSky);
		
		glPushMatrix();
			glTranslatef(-this.pos.x - (this.pos.x-this.ppos.x)*partialTick, -this.pos.y-this.eyeHeight - (this.pos.y-this.ppos.y)*partialTick, -this.pos.z - (this.pos.z-this.ppos.z)*partialTick);
			
			MiscRenderer.render(Math.round(this.pos.x + (this.pos.x-this.ppos.x)*partialTick), Math.round(this.pos.z + (this.pos.z-this.ppos.z)*partialTick));
		glPopMatrix();
		
		super.drawScreen(mx, my, partialTick);
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
	
	@Override
	public String getName()
	{
		return "Misc Elysian Menu";
	}
	
	@Override
	public String getVersion()
	{
		return "1.0.0";
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
}