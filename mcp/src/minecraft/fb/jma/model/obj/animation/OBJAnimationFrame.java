package fb.jma.model.obj.animation;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class OBJAnimationFrame
{
	public List<String> groups;
	public List<Vector3f> positions;
	public List<Vector3f> rotations;
	public List<Vector3f> offsets;
	public List<Vector3f> sizes;
	
	public String texture;
	
	public OBJAnimationFrame()
	{
		this.groups = new ArrayList<String>();
		this.positions = new ArrayList<Vector3f>();
		this.rotations = new ArrayList<Vector3f>();
		this.offsets = new ArrayList<Vector3f>();
		this.sizes = new ArrayList<Vector3f>();
		
		this.texture = null;
	}
}
