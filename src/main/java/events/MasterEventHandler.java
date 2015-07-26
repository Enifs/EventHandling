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
		while(this.running || EventControlPanel.hasEvents())
		{
			if (EventControlPanel.hasEvents())
			{
				Event event = EventControlPanel.nextEvent();

				if (event != null)
				{
					EventControlPanel.print(event.getClass() +
						" is being handled in " +
						Thread.currentThread().getName() +
						". " +
						"< " + event.toString() + " >");

					synchronized (event.getEventHandler())
					{
						event.getEventHandler().handleEvent(event);
					}
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

		EventControlPanel.print(Thread.currentThread().getName() + " stops running.");
	}


	/**
	 * A method for discarding all previously registered regular events.
	 */
	public void clearAllRegularEvents()
	{
		this.dispatcher.clearAllRegisteredEvents();
	}

	protected RegularEventDispatcher dispatcher = new RegularEventDispatcher();
	public boolean running = true;
}
