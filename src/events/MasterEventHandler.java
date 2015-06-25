// Copyright (c) 2015.
// Author: Enifs

package events;


import events.regularevents.RegularEventDispatcher;


/**
 * This class constantly checks the event queue for new events
 * Users should not use this class on its own. ECP - Event Control panel takes care of managing it.
 */
public class MasterEventHandler implements Runnable
{
	@Override
	public void run()
	{
		while(!EventControlPanel.stop || EventControlPanel.hasEvents())
		{
			if (EventControlPanel.hasEvents())
			{
				Event event = EventControlPanel.nextEvent();

				if (event != null)
				{
					if (EventControlPanel.consoleLogging)
					{
						System.out.println(event.getClass() + " is being handled. " + "< " + event.toString() + " >");
					}

					event.getEventHandler().handleEvent(event);
				}
			}

			dispatcher.work();

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


	/**
	 * A method for discarding all previously registered regular events.
	 */
	public void clearAllRegularEvents()
	{
		this.dispatcher.clearAllRegisteredEvents();
	}

	protected RegularEventDispatcher dispatcher = new RegularEventDispatcher();
}
