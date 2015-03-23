package dataSupplier;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlType(name = "", propOrder = {
	      "value",
	      "timestamp",
	      "quality"
	})

public class Types<T>
{
	@XmlElement(required=true)
	private T value;
	
	@XmlElement(required=true)
	private long timestamp;
	
	@XmlElement(required=true)
	private int quality;
	
	public Types()
	{
		
	}
	
	public Types(DataValue item)
	{
		this.value = (T) item.getValue().getValue();
		this.timestamp =item.getSourceTimestamp().getMilliSeconds();
		this.quality = Integer.parseInt(item.getStatusCode().getValue().toString());
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}
}