package dataSupplier;

import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.UaClient;

public abstract class Adapter
{
	private Configuration config;

	public Adapter(Configuration config)
	{
		this.setConfig(config);
	}
	
	public abstract void getData() throws Exception;

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
}