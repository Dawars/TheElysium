package mods.elysium.api.obj;

import org.lwjgl.util.vector.Vector3f;

public class OBJFace
{
	public Vector3f vertex;
	public Vector3f texture;
	public Vector3f normal;
	
	public OBJFace(Vector3f vertex, Vector3f texture, Vector3f normal)
	{
		this.vertex = vertex;
		this.texture = texture;
		this.normal = normal;
	}
}