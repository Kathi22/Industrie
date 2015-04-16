package dataSupplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlType(name = "", propOrder = {
	      "name",
		  "value",
		  "timestamp"
	})

@XmlRootElement(name="OPCType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OPCType<T> {
	
	@XmlElement(required=true)
	private T value;
	
	@XmlElement(required=true)
	private String name;
	
	@XmlElement(required=true)
	private long timestamp;
	
	public OPCType() {
		
	}
	
	public OPCType(DataValue item, String name){
		this.value = (T) item.getValue().getValue();
		this.name = name;
		this.timestamp =item.getSourceTimestamp().getMilliSeconds();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	

}
