// Copyright (c) 2015.
// Author: Enifs

package events;

import java.util.HashSet;


public abstract class Event
{
	public void fire()
	{
		EventControlPanel.fireEvent(this);
	}

	protected EventHandler getEventHandler()
	{
		return eventHandler;
	}


	public void setEventHandler(EventHandler eventHandler)
	{
		this.eventHandler = eventHandler;
	}

	public static void disable(Class c)
	{
		Event.disabledEvents.add(c);
	}

	public static void enable(Class c)
	{
		Event.disabledEvents.remove(c);
	}

	public static boolean isEnabled(Class c)
	{
		return !Event.disabledEvents.contains(c);
	}

	private EventHandler eventHandler = new EmptyEventHandler();

	private static HashSet<Class> disabledEvents = new HashSet<Class>();
}
