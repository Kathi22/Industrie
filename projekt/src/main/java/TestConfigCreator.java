import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;
import de.dhbw.mannheim.erpsim.model.CustomerOrder;

public class TestConfigCreator
{

	public static void main(String[] args)
	{
		// Config-Objekte erstellen (mit Testdaten)
		Vector<String> testvector = new Vector<String>(3);
		testvector.add("Sinusoid1");
		testvector.add("Counter1");
		testvector.add("Triangle1");

		Configuration c1 = new Configuration(
				"opc.tcp://localhost:53530/OPCUA/SimulationServer", testvector);

		// COnfig-Objekte Marshallalalaen. (Obejekt to XML) //XML iwo speichern
		// :D

		try
		{
			JAXBContext jc = JAXBContext.newInstance(Configuration.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// OutputStream os = new
			// FileOutputStream("C:\\Users\\D059185\\Documents\\workbench\\machine.xml"
			// );
			m.marshal(c1, System.out);// os );
			//OPCAdapter opc = new OPCAdapter(c1);
			//OPCConsumer opc1 = new OPCConsumer("logs");
			ERPConsumer erp1 = new ERPConsumer("CUSTOMER_ORDER_QUEUE");
			ERPConsumer erp2 = new ERPConsumer("MACHINE_ORDER_QUEUE");
			//opc.getData();
			//opc1.receive();
			erp1.receive();
			erp2.receive();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
