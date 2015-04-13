package dataSupplier;

import java.beans.Transient;

/**
 * @author Tarek Auel
 * @since 08.04.2015
 */
public class MachineOrder
{

	private final String id;

	private final String machine;

	private final double plannedSeconds;

	private final double speedShaperRPM;

	private final double speedDrillerRPM;

	public MachineOrder(String id, String machine, double plannedSeconds,
			double speedShaperRPM, double speedDrillerRPM)
	{
		this.id = id;
		this.machine = machine;
		this.plannedSeconds = plannedSeconds;
		this.speedShaperRPM = speedShaperRPM;
		this.speedDrillerRPM = speedDrillerRPM;
	}

	public String getId()
	{
		return id;
	}

	public String getMachine()
	{
		return machine;
	}

	public double getPlannedSeconds()
	{
		return plannedSeconds;
	}

	public double getSpeedShaperRPM()
	{
		return speedShaperRPM;
	}

	public double getSpeedDrillerRPM()
	{
		return speedDrillerRPM;
	}
}