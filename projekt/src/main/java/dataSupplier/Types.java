package dataSupplier;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Variant;


public class Types
{
	private Variant value;
	private DateTime timestamp;
	private StatusCode quality;
	
	public Types(DataValue item)
	{
		this.value = item.getValue();
		this.timestamp = item.getServerTimestamp();
		this.quality = item.getStatusCode();
	}

	public Variant getValue() {
		return value;
	}

	public void setValue(Variant value) {
		this.value = value;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public StatusCode getQuality() {
		return quality;
	}

	public void setQuality(StatusCode quality) {
		this.quality = quality;
	}	
}