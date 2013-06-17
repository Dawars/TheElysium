package fb.jma.model.obj;

public class OBJFace
{
	public final int[] vertexIndices;
	public final int[] normalIndices;
	public final int[] textureCoordinateIndices;
	
	public OBJFace(String line)
	{
		String[] indices = line.split(" ");
		int mode = indices.length;
		
		this.vertexIndices = new int[mode];
		this.normalIndices = new int[mode];
		this.textureCoordinateIndices = new int[mode];
		
		for(int i = 0; i < mode; i++)
		{
			if(!indices[i].split("/")[0].equals(""))
				this.vertexIndices[i] = Integer.parseInt(indices[i].split("/")[0]);
			else
				this.vertexIndices[i] = -1;
			
			if(!indices[i].split("/")[1].equals(""))
				this.textureCoordinateIndices[i] = Integer.parseInt(indices[i].split("/")[1]);
			else
				this.textureCoordinateIndices[i] = -1;
			
			if(!indices[i].split("/")[2].equals(""))
				this.normalIndices[i] = Integer.parseInt(indices[i].split("/")[2]);
			else
				this.normalIndices[i] = -1;
		}
	}
	
	public boolean hasVertices()
	{
		return (this.vertexIndices[0] != -1);
	}
	
	public boolean hasNormals()
	{
		return (this.normalIndices[0] != -1);
	}
	
	public boolean hasTextureCoordinates()
	{
		return (this.textureCoordinateIndices[0] != -1);
	}
}
