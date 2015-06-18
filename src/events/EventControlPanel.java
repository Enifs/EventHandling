// Copyright (c) 2015.
// Author: Enifs

package events;

import events.regularevents.RegularEvent;
import events.regularevents.RegularEventDispatcher;


/**
 * This is main control class. The system is initialized for event handling by calling the default
 * constructor of this class. There is no need for a user to maintain an instance of this class.
 */
public class EventControlPanel
{
	/**
	 * Default constructor. Call this method to initialize event handling system.
	 * IF not initialized, you will face some null pointer exceptions.
	 */
	public EventControlPanel()
	{
		this.startEventHandling();
	}


	/**
	 * Manual switch for starting up the event handling system. Use this if you have stopped event
	 * handling manually at some point in your code.
	 */
	public void startEventHandling()
	{
		EventControlPanel.events = new EventQueue();

		EventControlPanel.masterEventHandler = new MasterEventHandler();
		EventControlPanel.eventThread = new Thread(masterEventHandler);
		EventControlPanel.eventThread.start();

		EventControlPanel.events.clear();
	}


	/**
	 * Off switch for this system.
	 */
	public synchronized static void stopEventHandling()
	{
		EventControlPanel.masterEventHandler.dispatcher.clearAllRegisteredEvents();
		EventControlPanel.stop = true;
	}


	/**
	 * This method registers a regular event in this system.
	 * @param event The event to register in the system.
	 */
	public static void registerRegularEvent(RegularEvent event)
	{
		EventControlPanel.masterEventHandler.dispatcher.registerEvent(event);
	}


	/**
	 * This method removes a regular event from this system.
	 * @param event The event to remove.
	 */
	public static void unRegisterRegularEvent(RegularEvent event)
	{
		EventControlPanel.masterEventHandler.dispatcher.unregisterEvent(event);
	}


	/**
	 * This method queues ap an event to be executed as soon as possible.
	 * @param event Event to queue up for execution.
	 */
	public static void fireEvent(Event event)
	{
		if (!EventControlPanel.stop)
		{
			EventControlPanel.events.offer(event);
		}
	}


	/**
	 * This method says if there are any events currently queued up for execution.
	 *
	 * @return True, if there are events in the event queue;
	 */
	public static boolean hasEvents()
	{
		return !EventControlPanel.events.isEmpty();
	}


	/**
	 * This method fetches next Event from the event queue. It might return null.
	 * @return Event retrieved from the event queue.
	 */
	public static Event nextEvent()
	{
		return EventControlPanel.events.poll();
	}


	private static Thread eventThread;
	private static MasterEventHandler masterEventHandler;
	private static EventQueue events;

	public static boolean stop = false;
}
