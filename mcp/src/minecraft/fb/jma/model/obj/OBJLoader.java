package fb.jma.model.obj;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fb.jma.model.obj.animation.OBJAnimation;
import fb.jma.model.obj.animation.OBJAnimationFrame;

public class OBJLoader
{
	public static OBJModel loadOBJModel(String filepath)
	{
		try
		{
			OBJModel ret = new OBJModel();
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			
			String line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("v "))
				{
					line = line.substring(1).trim();
					ret.vertices.add(new Vector3f(Float.valueOf(line.split(" ")[0]),
							Float.valueOf(line.split(" ")[1]),
							Float.valueOf(line.split(" ")[2])));
				}
				else if(line.startsWith("vn "))
				{
					line = line.substring(2).trim();
					ret.normals.add(new Vector3f(Float.valueOf(line.split(" ")[0]),
							Float.valueOf(line.split(" ")[1]),
							Float.valueOf(line.split(" ")[2])));
				}
				else if(line.startsWith("vt "))
				{
					line = line.substring(2).trim();
					ret.textureCoordinates.add(new Vector2f(Float.valueOf(line.split(" ")[0]),
							Float.valueOf(line.split(" ")[1])));
				}
				else if(line.startsWith("f "))
				{
					line = line.substring(1).trim();
					if(ret.mode == 0)
						ret.mode = line.split(" ").length;
					if(ret.groups.size() == 0)
						ret.groups.add(new OBJGroup("default"));
					ret.groups.get(ret.groups.size()-1).faces.add(new OBJFace(line));
				}
				else if(line.startsWith("g "))
				{
					line = line.substring(1).trim();
					ret.groups.add(new OBJGroup(line));
				}
				
				line = br.readLine();
			}
			
			br.close();
			return ret;
		}
		catch(IOException e)
		{
			System.err.println("Error while loading "+filepath);
			return null;
		}
	}
	
	public static OBJAnimation loadOBJAnimation(String filepath)
	{
		try
		{
			OBJAnimation ret = new OBJAnimation();
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			
			Vector3f position = new Vector3f(0F, 0F, 0F);
			Vector3f size = new Vector3f(1F, 1F, 1F);
			Vector3f offset = new Vector3f(0F, 0F, 0F);
			Vector3f rotation = new Vector3f(0F, 0F, 0F);
			
			String line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("f"))
				{
					ret.frames.add(new OBJAnimationFrame());
				}
				else if(line.startsWith("g "))
				{
					ret.frames.get(ret.frames.size()-1).groups.add(line.substring(2));
					ret.frames.get(ret.frames.size()-1).positions.add(position);
					ret.frames.get(ret.frames.size()-1).sizes.add(size);
					ret.frames.get(ret.frames.size()-1).offsets.add(offset);
					ret.frames.get(ret.frames.size()-1).rotations.add(rotation);
				}
				else if(line.startsWith("tex "))
				{
					ret.frames.get(ret.frames.size()-1).texture = line.substring(4);
				}
				else if(line.startsWith("pos "))
				{
					line = line.substring(4).trim();
					ret.frames.get(ret.frames.size()-1).positions.get(ret.frames.get(ret.frames.size()-1).positions.size()-1).x += Float.valueOf(line.split(" ")[0]);
					ret.frames.get(ret.frames.size()-1).positions.get(ret.frames.get(ret.frames.size()-1).positions.size()-1).y += Float.valueOf(line.split(" ")[1]);
					ret.frames.get(ret.frames.size()-1).positions.get(ret.frames.get(ret.frames.size()-1).positions.size()-1).z += Float.valueOf(line.split(" ")[2]);
					position.x += Float.valueOf(line.split(" ")[0]);
					position.y += Float.valueOf(line.split(" ")[1]);
					position.z += Float.valueOf(line.split(" ")[2]);
				}
				else if(line.startsWith("scl "))
				{
					line = line.substring(4).trim();
					ret.frames.get(ret.frames.size()-1).sizes.get(ret.frames.get(ret.frames.size()-1).sizes.size()-1).x *= Float.valueOf(line.split(" ")[0]);
					ret.frames.get(ret.frames.size()-1).sizes.get(ret.frames.get(ret.frames.size()-1).sizes.size()-1).y *= Float.valueOf(line.split(" ")[1]);
					ret.frames.get(ret.frames.size()-1).sizes.get(ret.frames.get(ret.frames.size()-1).sizes.size()-1).z *= Float.valueOf(line.split(" ")[2]);
					size.x *= Float.valueOf(line.split(" ")[0]);
					size.y *= Float.valueOf(line.split(" ")[1]);
					size.z *= Float.valueOf(line.split(" ")[2]);
				}
				else if(line.startsWith("off "))
				{
					line = line.substring(4).trim();
					ret.frames.get(ret.frames.size()-1).offsets.get(ret.frames.get(ret.frames.size()-1).offsets.size()-1).x += Float.valueOf(line.split(" ")[0]);
					ret.frames.get(ret.frames.size()-1).offsets.get(ret.frames.get(ret.frames.size()-1).offsets.size()-1).y += Float.valueOf(line.split(" ")[1]);
					ret.frames.get(ret.frames.size()-1).offsets.get(ret.frames.get(ret.frames.size()-1).offsets.size()-1).z += Float.valueOf(line.split(" ")[2]);
					offset.x += Float.valueOf(line.split(" ")[0]);
					offset.y += Float.valueOf(line.split(" ")[1]);
					offset.z += Float.valueOf(line.split(" ")[2]);
				}
				else if(line.startsWith("rot "))
				{
					line = line.substring(4).trim();
					ret.frames.get(ret.frames.size()-1).rotations.get(ret.frames.get(ret.frames.size()-1).rotations.size()-1).x += Float.valueOf(line.split(" ")[0]);
					ret.frames.get(ret.frames.size()-1).rotations.get(ret.frames.get(ret.frames.size()-1).rotations.size()-1).y += Float.valueOf(line.split(" ")[1]);
					ret.frames.get(ret.frames.size()-1).rotations.get(ret.frames.get(ret.frames.size()-1).rotations.size()-1).z += Float.valueOf(line.split(" ")[2]);
					rotation.x += Float.valueOf(line.split(" ")[0]);
					rotation.y += Float.valueOf(line.split(" ")[1]);
					rotation.z += Float.valueOf(line.split(" ")[2]);
				}
				
				br.readLine();
			}
			
			br.close();
			return ret;
		}
		catch(IOException e)
		{
			System.err.println("Error while loading "+filepath);
			return null;
		}
	}
}
