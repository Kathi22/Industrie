package dataSupplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opcfoundation.ua.builtintypes.DataValue;

@XmlType(name = "", propOrder = {
	      "value",
	})

@XmlRootElement(name="OPCType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OPCType<T> {
	
	@XmlElement(required=true)
	private T value;
	
	public OPCType() {
		
	}
	
	public OPCType(DataValue item){
		this.value = (T) item.getValue().getValue();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	

}
