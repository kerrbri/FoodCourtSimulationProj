package foodCourtPack;

/**
 * @author Brianne Kerr
 * April 18, 2017
 *
 */

public class SpecialNeedsPerson extends Person
{
	
	public SpecialNeedsPerson(int arrivalTime)
	{
		super(arrivalTime);
	}
	
	public void setEateryTime(int time)
	{
		this.eateryTime = time * 4;
	}
	
	public void setLeaveTime(int time)
	{
		this.leaveTime = time * 3;
	}
	
	public void setCashierTime(int time)
	{
		this.cashierTime = time * 2;
	}
}
