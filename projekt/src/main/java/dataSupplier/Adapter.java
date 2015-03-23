package dataSupplier;

import java.io.IOException;

public abstract class Adapter
{
	private Configuration config;

	public Adapter(Configuration config)
	{
		this.setConfig(config);
	}

	public abstract void getData() throws Exception;
	
	public abstract void send(String s)  throws IOException;

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
}