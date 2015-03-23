package dataConsumer;

public abstract class Consumer {
	protected final String EXCHANGE_NAME;
	//private Configuration config;
	
	public Consumer(String exchangeName) {
		this.EXCHANGE_NAME = exchangeName;
	}

	
	//public abstract void receive();
	
}
