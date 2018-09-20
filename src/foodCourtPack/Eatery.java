package foodCourtPack;

/**
 * @author   Roger Ferguson
 * Edited by Brianne Kerr
 * April 18, 2017
 */
public class Eatery
{
	private MyLinkedList<Person> Q = new MyLinkedList<Person>();
	private int maxQlength = 0;
	private int completed = 0;
	private int peopleLeftCounter = 0;
	
	/**
     * This method adds a person to its line. If it is the only person in line
     * it starts the person's eatery time.
     * 
     * @param  (person) The Person to join the line
     */
	public void add (Person person)
	{
		Q.addToBack(person);
		if(Q.getSize() == 1)
		{
			person.setArrivalTimeEatery(person.getArrivalTime());
		}
		if (Q.getSize() > maxQlength)
		{
			maxQlength = Q.getSize();
		}
	}

	/**
	 * 
	 * @return the amount of people (int) who are in line
	 */
	public int getInLine() 
	{
		return Q.getSize();
	}
	
	/**
	 * 
	 * @return the amount of people (int) who left prematurely
	 */
	public int getPeopleWhoLeftCounter()
	{
		return peopleLeftCounter;
	}
	
	/**
	 * 
	 * @return the most amount of people the line had
	 */
	public int getMaxQlength() {
		return maxQlength;
	}

	/**
	 * 
	 * @return the amount of people who got food and went to the cashier
	 */
	public int getCompleted() {
		return completed;
	}
	
	/**
	 * This method removes any people who are in the eatery line but have reached
	 * their time limit and need to leave.
	 * 
	 * @param (currentTime) the time on the simulation clock
	 * @return true or false of if any person was removed
	 */
	public boolean removeIrregular(int currentTime)
	{
		boolean didRemove = false;
		for(int i = 2; i < Q.getSize(); i++)
		{
			if(Q.getFromPosition(i).needsToLeave(currentTime))
			{
				Q.removeFromPosition(i);
				peopleLeftCounter++;
				didRemove = true;
			}
		}
		return didRemove;
	}
	
	/**
	 * This method removes the person currently getting food if they 
	 * have completed their wait time
	 * 
	 * @param (currentTime) The time on the simulation clock
	 * @return The Person removed (or null if not removed)
	 */
	public Person removeFinished(int currentTime)
	{
		if(Q.getFromPosition(1).doneWithEatery(currentTime))
		{
			if(Q.getSize() > 1)
			{
				Q.getFromPosition(2).setArrivalTimeEatery(currentTime);
			}
			completed++;
			return Q.removeFromPosition(1);
		}
		return null;
	}
}