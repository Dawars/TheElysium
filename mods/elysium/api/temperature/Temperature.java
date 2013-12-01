package mods.elysium.api.temperature;

public class Temperature
{
	public int id, meta, temp;
	
	public Temperature(int id, int temp)
	{
		this(id, -1, temp);
	}
	
	public Temperature(int id, int meta, int temp)
	{
		this.id = id;
		this.meta = meta;
		this.temp = temp;
	}
}
