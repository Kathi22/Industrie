package dataSupplier;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;

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
	private DateTime timestamp;
	
	@XmlElement(required=true)
	private UnsignedInteger quality;
	
	public Types()
	{
		
	}
	
	public Types(DataValue item)
	{
		this.value = (T) item.getValue().getValue();
		this.timestamp = item.getSourceTimestamp();
		this.quality = item.getStatusCode().getValue();
	}
}