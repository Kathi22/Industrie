import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class Main
{

	public static void main(String[] args)
	{
		// Config-Objekt für Prosys Simulation erstellen
		/*Vector<String> testvector = new Vector<String>(3);
		testvector.add("Sinusoid1");
		testvector.add("Counter1");
		testvector.add("Triangle1");

		Configuration c1 = new Configuration(
				"opc.tcp://localhost:53530/OPCUA/SimulationServer", testvector);*/
		
		// Config-Objekt für Straße erstellen
		Vector<String> strassenvector = new Vector<String>(19);
		strassenvector.add("SPSData.S7-1200.Inputs.Phototransistor conveyer belt swap");
		strassenvector.add("SPSData.S7-1200.Inputs.Phototransistor drilling machine");
		strassenvector.add("SPSData.S7-1200.Inputs.Phototransistor loading station");
		strassenvector.add("SPSData.S7-1200.Inputs.Phototransistor milling machine");
		strassenvector.add("SPSData.S7-1200.Inputs.Phototransistor slider 1");
		strassenvector.add("SPSData.S7-1200.Inputs.Push-button slider 1 front");
		strassenvector.add("SPSData.S7-1200.Inputs.Push-button slider 1 rear");
		strassenvector.add("SPSData.S7-1200.Inputs.Push-button slider 2 front");
		strassenvector.add("SPSData.S7-1200.Inputs.Push-button slider 2 rear");
		strassenvector.add("SPSData.S7-1200.Outputs.motor conveyor belt drilling machine");
		strassenvector.add("SPSData.S7-1200.Outputs.motor conveyor belt feed");
		strassenvector.add("SPSData.S7-1200.Outputs.motor conveyor belt swap");
		strassenvector.add("SPSData.S7-1200.Outputs.motor drilling machine");
		strassenvector.add("SPSData.S7-1200.Outputs.motor milling machine");
		strassenvector.add("SPSData.S7-1200.Outputs.motor slider 1 backward");
		strassenvector.add("SPSData.S7-1200.Outputs.motor slider 1 forward");
		strassenvector.add("SPSData.S7-1200.Outputs.motor slider 2 backward");
		strassenvector.add("SPSData.S7-1200.Outputs.motor slider 2 forward");
		strassenvector.add("SPSData.S7-1200.Timer.Timer Fraesen");
		
		Configuration c2 = new Configuration(
				"opc.tcp://192.168.0.102:49320", strassenvector);
		
		try
		{
			//Prosys Simulation
			//OPCAdapter opca1 = new OPCAdapter(c1);
			//OPCConsumer opcc1 = new OPCConsumer("logs");
			//opca1.getData();
			//opcc1.receive();
			
			//OPC-Adapter für Strasse
			OPCAdapter opca2 = new OPCAdapter(c2);
		    opca2.getData();
		    
		    //OPC-Consumer für Pi
			//OPCConsumer opcc2 = new OPCConsumer("NFC_DATA_QUEUE");
			
			//OPC-Consumer für Strasse
			OPCConsumer opcc3 = new OPCConsumer("MACHINE_DATA_QUEUE");
			
			//ERP-Consumer für Kunden- und Maschinendaten
			//ERPConsumer erp1 = new ERPConsumer("CUSTOMER_ORDER_QUEUE");
			//ERPConsumer erp2 = new ERPConsumer("MACHINE_ORDER_QUEUE");

			//Daten empfangen
		    //opcc2.receive();
		    opcc3.receive();
			//erp1.receive();
			//erp2.receive();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}