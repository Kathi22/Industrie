package dataSupplier;

public class OrderPosition
{

	private final int pieces;

	private String position;

	private final MachineOrder[] machineOrders;

	public OrderPosition(int pieces, Workpiece position,
			MachineOrder[] machineOrders)
	{
		this.pieces = pieces;
		this.position = position.getName();
		this.machineOrders = machineOrders;
	}

	public int getPieces()
	{
		return pieces;
	}

	public String getPosition()
	{
		return position;
	}

	public MachineOrder[] getMachineOrders()
	{
		return machineOrders;
	}
}