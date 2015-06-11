// Copyright (c) 2015.
// Author: Enifs

package events;


public class EmptyEventHandler implements EventHandler
{

	@Override
	public void handleEvent(Event event)
	{
		System.out.println(event.getClass() + " has no handler");
	}
}
