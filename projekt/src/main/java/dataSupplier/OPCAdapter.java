package dataSupplier;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.MonitoringMode;
import org.opcfoundation.ua.core.ReferenceDescription;
import org.opcfoundation.ua.transport.security.SecurityMode;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.UaClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class OPCAdapter extends Adapter
{
	private UaClient client;
	private static final String EXCHANGE_NAME = "logs";
	
	public OPCAdapter(Configuration config) throws Exception
	{
		super(config);
		// Create client object 
		client = new UaClient(this.getConfig().getURL());
		
		client.setSecurityMode(SecurityMode.NONE);
		
		initialize(client);
		client.connect();

		client.getAddressSpace().setMaxReferencesPerNode(1000);
		NodeId nid = Identifiers.RootFolder; 
		
		List<ReferenceDescription> references = client.getAddressSpace().browse(nid);
		
		/*// Example of Namespace Browsing 
		NodeId target; 
		ReferenceDescription r = references.get(0);
		
		target = client.getAddressSpace().getNamespaceTable().toNodeId(r.getNodeId()); 
		references = client.getAddressSpace().browse(target);
		r = references.get(4);
		target = client.getAddressSpace().getNamespaceTable().toNodeId(r.getNodeId());*/
	}

	@Override
	public void getData() throws Exception {
		NodeId target2;
		MonitoredDataItem item;
		Subscription subscription = new Subscription();
		for(int i = 0; i < this.getConfig().getItems().size(); i++)
		{
			target2 = new NodeId(2, this.getConfig().getItems().get(i));
			final String name = this.getConfig().getItems().get(i);
			item = new MonitoredDataItem(target2, Attributes.Value, MonitoringMode.Reporting);
			subscription.addItem(item);
			item.setDataChangeListener(new MonitoredDataItemListener()
			{	
				@Override
				public void onDataChange(MonitoredDataItem arg0, DataValue arg1,
						DataValue arg2)
				{
					if (arg1 != null)
					{
						
						OPCType type = null;
						if (arg1.getValue().getValue() instanceof Boolean)
						{
							type = new OPCType<Boolean>(arg1, name);
						}
						/*
						Types type = null;
						if (arg1.getValue().getValue() instanceof Double)
						{
							type = new Types<Double>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Integer)
						{
							type = new Types<Integer>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Short)
						{
							type = new Types<Short>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Byte)
						{
							type = new Types<Byte>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Long)
						{
							type = new Types<Long>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Float)
						{
							type = new Types<Float>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof Character)
						{
							type = new Types<Character>(arg1);
						}
						else if (arg1.getValue().getValue() instanceof String)
						{
							type = new Types<String>(arg1);
						}
						*/
						JAXBContext jc;
						try {
							jc = JAXBContext.newInstance(OPCType.class );
							Marshaller m = jc.createMarshaller();
							m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							StringWriter writer = new StringWriter();
							m.marshal(type, writer);
							System.out.println(writer.toString());
							send(writer.toString());
						} catch (JAXBException e) {
							e.printStackTrace();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			});
		}		
		
		client.addSubscription(subscription);
	}
	
	public void send(String s) throws IOException
	{
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        channel.basicPublish(EXCHANGE_NAME, "", null, s.getBytes());
        //System.out.println(" [x] Sent '" + s + "'");

        channel.close();
        connection.close();
	}
	
	/**
	 * Initialize the client
	 * @param client
	 * @throws SecureIdentityException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	protected static void initialize(UaClient client) throws SecureIdentityException, IOException, UnknownHostException
	{
		// *** Application Description is sent to the server
		ApplicationDescription appDescription = new ApplicationDescription();
		appDescription.setApplicationName(new LocalizedText("DHBW Client",Locale.GERMAN));
		
		// 'localhost' (all lower case) in the URI is converted to the actual
		// host name of the computer in which the application is run
		appDescription.setApplicationUri("urn:localhost:UA:DHBWClient");
		appDescription.setProductUri("urn:prosysopc.com:UA:DHBWClient");
		appDescription.setApplicationType(ApplicationType.Client);

		final ApplicationIdentity identity = new ApplicationIdentity();
		identity.setApplicationDescription(appDescription);
		client.setApplicationIdentity(identity);
	}
}