package foodCourtPack;

import java.util.Random;

/**
 * @author Roger Ferguson
 * Edited by Brianne Kerr
 * April 18, 2017
 */
public class PersonProducer 
{
	
	private MyLinkedList<Eatery> eateryList; //List of Eateries to add to
	private int averageEateryTime; //Average time for Persons spent on food being prepared
	private int averageCashierTime; //Average time for Persons at the cashier
	private int averageLeaveTime; //Average time for Persons before leaving prematurely
	private int personCounter; //Total number of people created
	private int limitedTimeCounter; //Total number of limitedTimePersons
	private int specialNeedsCounter; //Total number of specialNeedsPersons
	
	private Random r = new Random(); //Random number generator
	
	
	/**
	 * Initializes values 
	 * 
	 * @param (list) the eatery list 
	 * @param (eateryTime) the average of Persons' eatery times
	 * @param (cashierTime) the average of Persons' cashier times
	 * @param (leaveTime) the average of Persons' leave time
	 */
	public PersonProducer(MyLinkedList<Eatery> list, int eateryTime, int cashierTime, int leaveTime) 
	{
		eateryList = list;
		averageEateryTime = eateryTime;
		averageCashierTime = cashierTime;
		averageLeaveTime = leaveTime;
		personCounter = 0;
		limitedTimeCounter = 0;
		specialNeedsCounter = 0;
	}
	
	/**
	 * Creates a new Person, calculates its initial values, and adds it to the 
	 * next eatery
	 * @param (tick) current simulation time
	 */
	public void addPerson(int tick)
	{
			
		   int rNumber = r.nextInt(10) + 1;
		   Person person;
		   if(rNumber <= 7)
		   {
			   person = new Person(tick);
		   }
		   else if(rNumber < 9)
		   {
			   person = new LimitedTimePerson(tick);
			   limitedTimeCounter++;
		   }
		   else
		   {
			   person = new SpecialNeedsPerson(tick);
			   specialNeedsCounter++;
		   }
			
			
			person.setEateryTime((int)Math.max(0,averageEateryTime*0.5*r.nextGaussian() + averageEateryTime +.5));
			person.setCashierTime((int)Math.max(0,averageCashierTime*0.5*r.nextGaussian() + averageCashierTime +.5));
			person.setLeaveTime((int)Math.max(0,averageLeaveTime*0.5*r.nextGaussian() + averageLeaveTime +.5));
			person.setArrivalTimeEatery(tick);
			
			eateryList.getFromPosition((personCounter % eateryList.getSize()) + 1).add(person);
			personCounter++;
			
		}
	
	/**
	 * 
	 * @return total number of people created
	 */
	public int getPersonCounter()
	{
		return personCounter;
	}
	
	/**
	 * 
	 * @return total number of limited time people created
	 */
	public int getLimitedTimeCounter()
	{
		return limitedTimeCounter;
	}
	
	/**
	 * 
	 * @return total number of special needs people created
	 */
	public int getSpecialNeedsCounter()
	{
		return specialNeedsCounter;
	}

}