package events;


public class MasterEventHandler implements Runnable
{
	@Override
	public void run()
	{
		while(true)
		{
			if (EventControlPanel.hasEvents())
			{
				Event event = EventControlPanel.nextEvent();
				event.getEventHandler().handleEvent(event);
			}
			try
			{
				Thread.sleep(5);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
