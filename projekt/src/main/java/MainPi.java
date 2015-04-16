import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class MainPi
{

	public static void main(String[] args)
	{
		
		try
		{
			//OPC-Consumer für Pi
			OPCConsumer opcc2 = new OPCConsumer("NFC_DATA_QUEUE");
			
			//Daten empfangen
		    opcc2.receive();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}