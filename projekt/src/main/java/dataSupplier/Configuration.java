package dataSupplier;

import java.util.Vector;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
	      "url",
	      "items"
	})

@XmlRootElement(name="Configuration")
public class Configuration {

		@XmlElement(required=true)
		private String url; 
		
		@XmlElement(required=true)
		private Vector<String> items; 
		
		public Configuration(){
			
		}
		
		public Configuration(String u, Vector<String> i) {
			this.url = u;
			this.items = i;
		}
		
		public String getURL() {
			return this.url;	
		}
		
		public Vector<String> getItems() {
			return this.items;	
		}
}