package dataSupplier;

public class CustomerOrder
{

	private final String customerFirstName;

	private final String customerLastName;

	private final String customerNumber;

	private final long orderNumber;

	private final OrderPosition[] positions;

	public CustomerOrder(String customerFirstName, String customerLastName,
			String customerNumber, long orderNumber, OrderPosition[] positions)
	{
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerNumber = customerNumber;
		this.orderNumber = orderNumber;
		this.positions = positions;
	}

	public String getCustomerFirstName()
	{
		return customerFirstName;
	}

	public String getCustomerLastName()
	{
		return customerLastName;
	}

	public String getCustomerNumber()
	{
		return customerNumber;
	}

	public long getOrderNumber()
	{
		return orderNumber;
	}

	public OrderPosition[] getPositions()
	{
		return positions;
	}
}