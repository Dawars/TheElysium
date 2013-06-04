package mods.elysium.api.obj;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.lwjgl.util.vector.Vector3f;

public class OBJLoader
{
	public static OBJModel loadModel(String file) throws FileNotFoundException, IOException
	{
		OBJModel retModel = new OBJModel();
		BufferedReader modelReader = new BufferedReader(new FileReader(file));
		
		String cline = modelReader.readLine();
		while(cline != null)
		{
			if(cline.startsWith("v "))
			{
				float x = Float.valueOf(cline.split(" ")[1]);
				float y = Float.valueOf(cline.split(" ")[2]);
				float z = Float.valueOf(cline.split(" ")[3]);
				retModel.verticies.add(new Vector3f(x, y, z));
			}
			else if(cline.startsWith("vn"))
			{
				float x = Float.valueOf(cline.split(" ")[1]);
				float y = Float.valueOf(cline.split(" ")[2]);
				float z = Float.valueOf(cline.split(" ")[3]);
				retModel.normals.add(new Vector3f(x, y, z));
			}
			else if(cline.startsWith("f "))
			{
				Vector3f vertexIndices = new Vector3f(Float.valueOf(cline.split(" ")[1].split("/")[0]),
						Float.valueOf(cline.split(" ")[2].split("/")[0]),
						Float.valueOf(cline.split(" ")[3].split("/")[0]));
				Vector3f normalIndices = new Vector3f(Float.valueOf(cline.split(" ")[1].split("/")[2]),
						Float.valueOf(cline.split(" ")[2].split("/")[2]),
						Float.valueOf(cline.split(" ")[3].split("/")[2]));
				retModel.faces.add(new OBJFace(vertexIndices, normalIndices));
			}
			cline = modelReader.readLine();
		}
		
		modelReader.close();
		return retModel;
	}
}