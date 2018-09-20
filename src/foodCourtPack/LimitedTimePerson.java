package foodCourtPack;

/**
 * @author Brianne Kerr
 * April 18, 2017
 */

public class LimitedTimePerson extends Person
{
	public LimitedTimePerson(int arrivalTime)
	{
		super(arrivalTime);
	}
	
	public void setEateryTime(int time)
	{
		this.eateryTime = (int) (time * .5);
	}
	
	public void setLeaveTime(int time)
	{
		this.leaveTime = (int) (time * .5);
	}
}
