package fb.jma.model.obj.animation;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class OBJAnimation
{
	public List<OBJAnimationFrame> frames;
	
	public OBJAnimation()
	{
		this.frames = new ArrayList<OBJAnimationFrame>();
	}
}