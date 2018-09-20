package foodCourtPack;

public interface ClockListener 
{
	static int time = 0;
	public void event(int tick);  // the method that is called by the clock
}