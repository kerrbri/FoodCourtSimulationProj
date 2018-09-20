package foodCourtPack;

/**
 * @author Roger Ferguson
 * Edited by Brianne Kerr
 * April 18, 2017
 *
 */
public class Sim
{
	private int time; //The counter of the simulation
	
	private PersonProducer producer; //Creates Persons and adds to appropriate eateries
	
	private MyLinkedList<Eatery> eateryList; //List of all eateries
	private MyLinkedList<Person> cashierLine; //List of people waiting in line for cashier

	private int numCashiers; //Number of cashiers
	private int newPersonInterval; //Interval at which a new person is made
	private int peopleServedCounter; //Counts people who completed cashier
	private int totalPeopleTimeCounter; //Counts how long all completed people waited in total
	
	public static final int numStats = 9; //Number of stats to return
	private int[] statValue; //Array of the values of the stats
	
	
	/**
	 * Initializes values
	 * 
	 * @param (newPersonTime) Interval for each new person
	 * @param (eateryTime) Average time spent being served at the eatery
	 * @param (cashierTime) Average time spent being served by the cashier
	 * @param (leaveTime) Average time waiting before storming out without your food
	 * @param (numEateries) Number of eateries to begin with
	 * @param (numCashiers) Number of cashiers to begin with
	 */
	public Sim(int newPersonTime, int eateryTime, int cashierTime, int leaveTime,
			    int numEateries, int numCashiers)
	{
		this.time = 1;
		this.eateryList = new MyLinkedList<Eatery>();
		this.producer = new PersonProducer(eateryList, eateryTime, cashierTime, leaveTime);
		this.cashierLine = new MyLinkedList<Person>();
		this.newPersonInterval = newPersonTime;
		for(int i = 0; i < numEateries; i++)
		{
			this.addEatery();
		}
		this.numCashiers = numCashiers;
		this.setStatValue();
		this.peopleServedCounter = 0;
		this.totalPeopleTimeCounter = 0;
	}
	
	/**
	 * The clock ticks, adding one to time. People move ahead in the eatery and 
	 * cashier times and new people are created as needed.
	 */
	public void tick()
	{
		time++;
		this.moveThroughCashier();
		this.moveThroughEatery();
		
		if(time % newPersonInterval == 0)
		{
			producer.addPerson(time);
		}
	}
	
	/**
	 * For all eateries, removes people who have reached leaveTime and 
	 * removes the first person if they have reached eateryTime
	 */
	private void moveThroughEatery()
	{
		for(int i = 1; i <= eateryList.getSize(); i++)
		{
			if(eateryList.getFromPosition(i).getInLine() > 0)
			{
				Person temp = eateryList.getFromPosition(i).removeFinished(time);
				eateryList.getFromPosition(i).removeIrregular(time);
				if(temp != null)
				{
					temp.setArrivalTimeCashier(time);
					cashierLine.addToBack(temp);
				}
			}
		}
	}
	
	/**
	 * Removes people who have finished with the cashier and moves the next
	 * person to the available cashier
	 */
	private void moveThroughCashier()
	{
		for(int i = 1; i <= cashierLine.getSize() && i <= numCashiers; i++)
		{
			if(cashierLine.getFromPosition(i).doneWithCashier(time))
			{
				if(cashierLine.getSize() > numCashiers)
				{
					cashierLine.replaceFromInLine(i, numCashiers + 1);
				}
				else
				{
					cashierLine.removeFromPosition(i);
				}
				peopleServedCounter++;
			}
		}
	}
	
	/**
	 * 
	 * @return An array of descriptors of the statistic available
	 */
	public static String[] getStatLabel()
	{
		String[] statLabel = new String[numStats];
		
		statLabel[0] = "Time";
		statLabel[1] = "Total People Arrived";
		statLabel[2] = "Total People In Cashier Line";
		statLabel[3] = "Total People Paid";
		statLabel[4] = "Total People Who Left";
		statLabel[5] = "Average Max in Eatery Line";
		statLabel[6] = "Specials Needs Customers";
		statLabel[7] = "Limited Time Customers";
		statLabel[8] = "Average Total Time";
		return statLabel;
	}	
	
	/**
	 * Initializes statValue[] to zero
	 */
	private void setStatValue()
	{	
		statValue = new int[numStats];
		for(int i = 0; i < this.numStats; i++)
		{
			statValue[i] = 0;
		}
	}
	
	/**
	 * Fills statValue[] with updated statistics related to the simulation
	 * @return an array of statistical information
	 */
	public int[] getStatValue()
	{
		statValue[0] = this.time;
		statValue[1] = this.producer.getPersonCounter();
		statValue[2] = this.cashierLine.getSize();
		statValue[3] = this.peopleServedCounter;
		statValue[4] = this.getPeopleWhoLeft();
		statValue[5] = this.getMaxLineAverage();
		statValue[6] = this.producer.getSpecialNeedsCounter();
		statValue[7] = this.producer.getLimitedTimeCounter();
		statValue[8] = this.getAverageTimeSpentForFood();
		return statValue;
	}
	
	/**
	 * Creates a new eatery and adds it to the list
	 */
	public void addEatery()
	{
			Eatery temp = new Eatery();
			eateryList.addToBack(temp);
	}
	
	/**
	 * Removes the last eatery of the list and sends all of the people in that line 
	 * into the void
	 */
	public void subEatery()
	{
		eateryList.removeFromBack();
	}
	
	/**
	 * Makes a new cashier available
	 */
	public void addCashier()
	{
		numCashiers++;
		cashierLine.getFromPosition(numCashiers).setArrivalTimeCashier(time);
	}
	
	/**
	 * Removes one cashier and the person they were serving
	 */
	public void subCashier()
	{
		 cashierLine.removeFromPosition(1);
		 numCashiers--;
	}
	
	/**
	 * 
	 * @return  an array of the total amount of people in each eatery line
	 */
	public int[] getNumberInEachLine()
	{
		int[] temp = new int[eateryList.getSize()];
		for(int i = 0; i < eateryList.getSize(); i++)
		{
			temp[i] = eateryList.getFromPosition(i + 1).getInLine();
		}
		
		return temp;
	}
	
	/**
	 * 
	 * @return The average of the Maximum amount of people each line received 
	 */
	private int getMaxLineAverage()
	{
		int temp = 0;
		for(int i = 1; i <= this.eateryList.getSize(); i++)
		{
			temp += this.eateryList.getFromPosition(i).getMaxQlength();
		}
		return temp / this.eateryList.getSize();
	}
	
	/**
	 * 
	 * @return the average total time for those that completed the cashier line
	 */
	private int getAverageTimeSpentForFood()
	{
		 if(peopleServedCounter > 0)
		 {
			 return this.totalPeopleTimeCounter / this.peopleServedCounter;
		 }
		 
		 return 0;
	}
	
	
	/**
	 * 
	 * @return The total amount of people who left the line prematurely
	 */
	private int getPeopleWhoLeft()
	{
		int temp = 0;
		for(int i = 1; i <= this.eateryList.getSize(); i++)
		{
			temp += this.eateryList.getFromPosition(i).getPeopleWhoLeftCounter();
		}
		return temp;
	}

}