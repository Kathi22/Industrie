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
@XmlRootElement(name="Types")
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
}