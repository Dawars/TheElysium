package mods.elysium.api.obj;

import java.util.List;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class OBJModel
{
	public List<Vector3f> verticies;
	public List<Vector3f> normals;
	public List<OBJFace> faces;
	
	public OBJModel()
	{
		this.verticies = new ArrayList<Vector3f>();
		this.normals = new ArrayList<Vector3f>();
		this.faces = new ArrayList<OBJFace>();
	}
	
	public void render()
	{
		glBegin(GL_TRIANGLES);
		for(OBJFace face : this.faces)
		{
			Vector3f n1 = this.normals.get((int) face.normal.x - 1);
			glNormal3f(n1.x, n1.y, n1.z);
			Vector3f v1 = this.verticies.get((int) face.vertex.x - 1);
			glVertex3f(v1.x, v1.y, v1.z);
			Vector3f n2 = this.normals.get((int) face.normal.y - 1);
			glNormal3f(n2.x, n2.y, n2.z);
			Vector3f v2 = this.verticies.get((int) face.vertex.y - 1);
			glVertex3f(v2.x, v2.y, v2.z);
			Vector3f n3 = this.normals.get((int) face.normal.z - 1);
			glNormal3f(n3.x, n3.y, n3.z);
			Vector3f v3 = this.verticies.get((int) face.vertex.z - 1);
			glVertex3f(v3.x, v3.y, v3.z);
		}
		glEnd();
	}
}