import java.util.Vector;

import dataConsumer.ERPConsumer;
import dataConsumer.OPCConsumer;
import dataSupplier.Configuration;
import dataSupplier.OPCAdapter;

public class Main
{

	public static void main(String[] args)
	{
		//Starten der main-Methoden
		MainERPConsumer.main(args);
		MainERPMachine.main(args);
		MainPi.main(args);
		MainProsysClient.main(args);
		MainStreet.main(args);
	}
}