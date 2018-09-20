package foodCourtPack;

import java.util.concurrent.*;

public class testLinkedList 
{
	static int count;
	public static void main(String [] args)
	{
		MyLinkedList<String> list = new MyLinkedList<String>();
		String first = "third";
		list.addToBack("first");
		list.addToBack("second");
		list.addToBack("lsdkfjsdl");
		list.addToBack(first);
		
		//Sim sim = new Sim();
		
		//final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    //executorService.scheduleAtFixedRate(sim, 0, 1, TimeUnit.SECONDS);
		
	   for(int i = 1; i <= list.getSize(); i++)
		{
			System.out.println(list.getFromPosition(i));
		}
		
		list.removeFromPosition(3);
		
		for(int i = 1; i <= list.getSize(); i++)
		{
			System.out.println(list.getFromPosition(i));
		}
		System.out.println(list.getPositionOfElement("first"));
		
		Person person = new LimitedTimePerson(6);
		person.setEateryTime(6);
		System.out.println("Does this work? " + person.getEateryTime());
		
		
	}
	
	public void keepTime()
	{
		System.out.println(count++);
	}
}
	
