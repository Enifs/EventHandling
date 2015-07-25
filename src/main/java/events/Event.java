// Copyright (c) 2015.
// Author: Enifs

package events;

import java.util.HashSet;


/**
 * This is the base class for every event in this system.
 * Class extending this one should have following line in its constructor -
 * this.setEventHandler(new EventHandler());
 */
public abstract class Event
{
	/**
	 * This method must be called to make an event happen.
	 */
	public void fire()
	{
		EventControlPanel.fireEvent(this);
	}


	/**
	 * This method gets the event handler bound to this event class.
	 * @return An event handler object bound to this class.
	 */
	protected EventHandler getEventHandler()
	{
		return eventHandler;
	}


	/**
	 * This method must be called in events constructor for any event handling to happen.
	 * @param eventHandler
	 */
	public void setEventHandler(EventHandler eventHandler)
	{
		this.eventHandler = eventHandler;
	}


	/**
	 * This method is used to block events of the given class so they would not execute.
	 *
	 * @param eventClass Event class to disable.
	 */
	public static void disable(Class eventClass)
	{
		EventControlPanel.print("Disabling " + eventClass.getName());
		Event.disabledEvents.add(eventClass);
	}


	/**
	 * This method enables previously disabled event.
	 *
	 * @param eventClass Event class to get enabled.
	 */
	public static void enable(Class eventClass)
	{
		EventControlPanel.print("Enabling " + eventClass.getName());
		Event.disabledEvents.remove(eventClass);
	}


	/**
	 * This method checks if given event class is enabled or no.
	 *
	 * @param eventClass Class to have its status checked.
	 * @return True, if given event class is allowed to fire events.
	 */
	public static boolean isEnabled(Class eventClass)
	{
		return !Event.disabledEvents.contains(eventClass);
	}


	public static void enableAll()
	{
		Event.disabledEvents.clear();
	}


	public static void addPauseException(Class eventClass)
	{
		Event.pauseExceptions.add(eventClass);
	}


	public static void removePauseException(Class eventClass)
	{
		Event.pauseExceptions.remove(eventClass);
	}


	public static void clearPauseExceptions()
	{
		Event.pauseExceptions.clear();
	}


	public static boolean isInPauseExceptions(Class eventClass)
	{
		return Event.pauseExceptions.contains(eventClass);
	}


	private EventHandler eventHandler = new EmptyEventHandler();

	private static HashSet<Class> disabledEvents = new HashSet<Class>();

	private static HashSet<Class> pauseExceptions = new HashSet<Class>();
}
