package fb.jma.model.obj;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import cpw.mods.fml.client.FMLClientHandler;

import fb.jma.model.obj.animation.OBJAnimation;
import fb.jma.model.obj.animation.OBJAnimationFrame;
import static org.lwjgl.opengl.GL11.*;

public class OBJModel
{
	public int mode;
	
	public final List<Vector3f> vertices;
	public final List<Vector3f> normals;
	public final List<Vector2f> textureCoordinates;
	
	public final List<OBJGroup> groups;
	
	public OBJModel()
	{
		this.vertices = new ArrayList<Vector3f>();
		this.normals = new ArrayList<Vector3f>();
		this.textureCoordinates = new ArrayList<Vector2f>();
		this.groups = new ArrayList<OBJGroup>();
	}
	
	public void renderAll()
	{
		for(OBJGroup group : this.groups)
		{
			this.renderGroup(group.name);
		}
	}
	
	public void renderGroup(String name)
	{
		if(this.mode == 3)
			glBegin(GL_TRIANGLES);
		else if(this.mode == 4)
			glBegin(GL_QUADS);
		
		for(OBJGroup group : this.groups)
		{
			if(group.name.equals(name))
			{
				for(OBJFace face : group.faces)
				{
					for(int i = 0; i < this.mode; i++)
					{
						if(face.hasNormals())
						{
							Vector3f normal = this.normals.get(face.normalIndices[i]-1);
							glNormal3f(normal.x, normal.y, normal.z);
						}
						if(face.hasTextureCoordinates())
						{
							Vector2f texture = this.textureCoordinates.get(face.textureCoordinateIndices[i]-1);
							glTexCoord2f(texture.x, texture.y);
						}
						if(face.hasVertices())
						{
							Vector3f vertex = this.vertices.get(face.vertexIndices[i]-1);
							glVertex3f(vertex.x, vertex.y, vertex.z);
						}
					}
				}
			}
		}
		
		glEnd();
	}
	
	public void renderWithAnimation(OBJAnimation animation, int tick)
	{
		OBJAnimationFrame frame = animation.frames.get(tick % animation.frames.size());
		
		if(frame.texture != "")
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(frame.texture);
		else
			glBindTexture(GL_TEXTURE_2D, 0);
		
		for(OBJGroup group : this.groups)
		{
			if(frame.groups.contains(group.name))
			{
				int i = frame.groups.indexOf(group.name);
				
				glPushMatrix();
					glRotatef(frame.rotations.get(i).x, 1F, 0F, 0F);
					glRotatef(frame.rotations.get(i).y, 0F, 1F, 0F);
					glRotatef(frame.rotations.get(i).z, 0F, 0F, 1F);
					glTranslatef(frame.offsets.get(i).x, frame.offsets.get(i).y, frame.offsets.get(i).z);
					glScalef(frame.sizes.get(i).x, frame.sizes.get(i).y, frame.sizes.get(i).z);
					
					glPushMatrix();
						glTranslatef(frame.positions.get(i).x, frame.positions.get(i).y, frame.positions.get(i).z);
						this.renderGroup(group.name);
					glPopMatrix();
				glPopMatrix();
			}
		}
	}
}
