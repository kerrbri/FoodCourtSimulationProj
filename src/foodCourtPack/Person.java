package foodCourtPack;

/**
 * @author   Roger Ferguson
 * Edited by Brianne Kerr
 * April 18, 2017
 */
public class Person {
	protected int eateryTime;  // time it takes to order food at eatery
	protected int cashierTime; // time it takes to pay cashier
	protected int leaveTime; // time before they leave without food
	protected int arrivalTime; // time they arrive at the food court
	protected int arrivalTimeEatery; // time they begin ordering food
	protected int arrivalTimeCashier; // time they begin paying cashier
	
	
	/**
	 * Initializes value 
	 * @param (arrivalTime) time the person arrived at the food court
	 */
	public Person(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * Checks if the person has reached their leaveTime
	 * @param (currentTime) the time of the simulation
	 * @return whether or not the Person has reached their leaveTime
	 */
	public boolean needsToLeave(int currentTime)
	{
		if(currentTime - arrivalTime >= leaveTime)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the Person has reached their eateryTime
	 * @param (currentTime) the simulation's time
	 * @return whether or not the Person has reached their eateryTime
	 */
	public boolean doneWithEatery(int currentTime)
	{
		if(currentTime - arrivalTimeEatery == eateryTime)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the Person has reached their cashierTime
	 * @param (currentTime) the simulation's time
	 * @return whether or not the Person reached their cashierTime
	 */
	public boolean doneWithCashier(int currentTime)
	{
		if(currentTime - arrivalTimeCashier == cashierTime)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return how long the person's food takes
	 */
	public int getEateryTime() 
	{
		return eateryTime;
	}
	
	/**
	 * 
	 * @return how long the person requires at the cash register
	 */
	public int getCashierTime()
	{
		return cashierTime;
	}

	/**
	 * 
	 * @return how long the person will wait in line before leaving
	 */
	public int getLeaveTime()
	{
		return leaveTime;
	}
	
	/**
	 * 
	 * @return the simulation's time the person was created
	 */
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/**
	 * 
	 * @return the simulation's time the person was being served at the eatery
	 */
	public void setEateryTime(int time) 
	{
		this.eateryTime = time;
	}
	
	/**
	 * Sets the Person's cashierTime
	 * 
	 * @param (time) new cashierTime
	 */
	public void setCashierTime(int time)
	{
		this.cashierTime = time;
	}
	
	/**
	 * Sets the Person's leaveTime
	 * 
	 * @param (time) new leave time
	 */
	public void setLeaveTime(int time)
	{
		this.leaveTime = time;
	}
	
	/**
	 * Sets the Person's arrivalTimeCashier
	 * 
	 * @param (time) new arrivalTimeCashier
	 */
	public void setArrivalTimeCashier(int time)
	{
		this.arrivalTimeCashier = time;
	}
	
	/**
	 * Sets the Person's arrivalTimeEatery
	 * 
	 * @param (time) new arrivalTimeEatery
	 */
	public void setArrivalTimeEatery(int time)
	{
		this.arrivalTimeEatery = time;
	}
	
}
