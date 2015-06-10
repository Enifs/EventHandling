
package events;

public class MasterEventHandler implements Runnable
{
	@Override
	public void run()
	{
		while(true)
		{
			if (!EventQueue.events.isEmpty())
			{
				Event event = EventQueue.events.poll();
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
