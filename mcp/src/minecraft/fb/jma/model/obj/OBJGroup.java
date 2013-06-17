package fb.jma.model.obj;

import java.util.List;
import java.util.ArrayList;

public class OBJGroup
{
	public String name;
	public List<OBJFace> faces;
	
	public OBJGroup(String name)
	{
		this.name = name;
		this.faces = new ArrayList<OBJFace>();
	}
}
