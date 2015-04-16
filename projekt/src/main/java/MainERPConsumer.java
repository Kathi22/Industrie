import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class MainERPConsumer
{

	public static void main(String[] args)
	{
		try
		{
			//ERP-Consumer f�r Kundendaten
			ERPConsumer erp1 = new ERPConsumer("CUSTOMER_ORDER_QUEUE");

			//Daten empfangen
			erp1.receive();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}