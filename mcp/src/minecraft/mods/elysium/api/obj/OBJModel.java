package mods.elysium.api.obj;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.opengl.GL31;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class OBJModel
{
	public List<Vector3f> verticies;
	public List<Vector2f> textures;
	public List<Vector3f> normals;
	public List<OBJFace> faces;
	
	public OBJModel()
	{
		this.verticies = new ArrayList<Vector3f>();
		this.textures = new ArrayList<Vector2f>();
		this.normals = new ArrayList<Vector3f>();
		this.faces = new ArrayList<OBJFace>();
	}
	
	public void render()
	{
		glBegin(GL_TRIANGLES);
		for(OBJFace face : this.faces)
		{
			if(face.normal != null)
			{
				Vector3f n1 = this.normals.get((int) face.normal.x - 1);
				glNormal3f(n1.x, n1.y, n1.z);
			}
			if(face.texture != null)
			{
				Vector2f t1 = this.textures.get((int) face.texture.x - 1);
				glTexCoord2f(t1.x, t1.y);
			}
			if(face.vertex != null)
			{
				Vector3f v1 = this.verticies.get((int) face.vertex.x - 1);
				glVertex3f(v1.x, v1.y, v1.z);
			}
			
			if(face.normal != null)
			{
				Vector3f n2 = this.normals.get((int) face.normal.y - 1);
				glNormal3f(n2.x, n2.y, n2.z);
			}
			if(face.texture != null)
			{
				Vector2f t2 = this.textures.get((int) face.texture.y - 1);
				glTexCoord2f(t2.x, t2.y);
			}
			if(face.vertex != null)
			{
				Vector3f v2 = this.verticies.get((int) face.vertex.y - 1);
				glVertex3f(v2.x, v2.y, v2.z);
			}
			
			if(face.normal != null)
			{
				Vector3f n3 = this.normals.get((int) face.normal.z - 1);
				glNormal3f(n3.x, n3.y, n3.z);
			}
			if(face.texture != null)
			{
				Vector2f t3 = this.textures.get((int) face.texture.z - 1);
				glTexCoord2f(t3.x, t3.y);
			}
			if(face.vertex != null)
			{
				Vector3f v3 = this.verticies.get((int) face.vertex.z - 1);
				glVertex3f(v3.x, v3.y, v3.z);
			}
		}
		glEnd();
	}
}