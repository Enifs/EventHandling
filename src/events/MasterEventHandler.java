// Copyright (c) 2015.
// Author: Enifs

package events;


/**
 * This class constantly checks the event queue for new events
 * Users should not use this class on its own. ECP - Event Control panel takes care of managing it.
 */
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
