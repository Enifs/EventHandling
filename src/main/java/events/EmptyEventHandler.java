// Copyright (c) 2015.
// Author: Enifs

package events;


/**
 * This class is the temporary placeholder for real event handler.
 * All it does, it prints in console name of the Event, that does not yet have its own event
 * handler attached.
 */
public class EmptyEventHandler implements EventHandler
{
	@Override
	public void handleEvent(Event event)
	{
		System.out.println(event.getClass() + " has no handler");
	}
}
