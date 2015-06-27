// Copyright (c) 2015.
// Author: Enifs

package events;

import java.util.LinkedList;


/**
 * This is the queue where all the events are queued up for execution.
 */
public class EventQueue extends LinkedList<Event>
{
	@Override
	public boolean offer(Event event)
	{
		boolean rv = Event.isEnabled(event.getClass());

		if (rv)
		{
			EventControlPanel.print(event.getClass().getSimpleName() + " fired in " +
				EventControlPanel.eventThread.getName());
			super.offer(event);
		}

		return rv;
	}
}
