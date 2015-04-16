import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class MainERPMachine
{

	public static void main(String[] args)
	{
		try
		{
			//ERP-Consumer für Maschinendaten
			ERPConsumer erp2 = new ERPConsumer("MACHINE_ORDER_QUEUE");

			//Daten empfangen
			erp2.receive();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}