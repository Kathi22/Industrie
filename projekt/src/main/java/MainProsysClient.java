import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class MainProsysClient
{

	public static void main(String[] args)
	{
		// Config-Objekt für Prosys Simulation erstellen
		Vector<String> testvector = new Vector<String>(3);
		testvector.add("Sinusoid1");
		testvector.add("Counter1");
		testvector.add("Triangle1");

		Configuration c1 = new Configuration(
				"opc.tcp://localhost:53530/OPCUA/SimulationServer", testvector);

		try
		{
			//Prosys Simulation
			OPCAdapter opca1 = new OPCAdapter(c1);
			OPCConsumer opcc1 = new OPCConsumer("logs");
			//Daten vom OPCClient erhalten
			opca1.getData();
			
			
			//Daten empfangen
			opcc1.receive();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}