// Copyright (c) 2015.
// Author: Enifs

package events;

import java.security.spec.ECParameterSpec;

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
		EventControlPanel.time = System.currentTimeMillis();
		EventControlPanel.events = new EventQueue();

		EventControlPanel.masterEventHandler = new MasterEventHandler();
		EventControlPanel.eventThread = new Thread(masterEventHandler);
		EventControlPanel.eventThread.setName(EventControlPanel.eventThread.getName() + " MEH");
		EventControlPanel.eventThread.start();

		EventControlPanel.print("Thread " + EventControlPanel.eventThread.getName() + " starts.");

		EventControlPanel.events.clear();
		Event.enableAll();

		EventControlPanel.print("ECP starts in " + Thread.currentThread().getName());
	}


	/**
	 * Off switch for this system.
	 */
	public synchronized static void stopEventHandling()
	{
		EventControlPanel.masterEventHandler.dispatcher.clearAllRegisteredEvents();
		EventControlPanel.masterEventHandler.running = false;

		EventControlPanel.print("Thread " + EventControlPanel.eventThread.getName() + " stop initialized. Events left " + EventControlPanel.events.size());

		while (EventControlPanel.hasEvents())
		{

		}

		EventControlPanel.print("ECP stops in " + Thread.currentThread().getName() + " Events left " + EventControlPanel.events.size() );
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
	 * This class allows to disable regular event by class.
	 *
	 * This method is not optimized, and it slow. If you have many regular events, try to use different
	 * method.
	 *
	 * @param regularEventClass
	 */
	public static void unRegisterRegularEventClass(Class regularEventClass)
	{
		EventControlPanel.masterEventHandler.dispatcher.unregisterEventClass(regularEventClass);
	}


	/**
	 * This method returns Event that correspond given regular event class.
	 * @param regularEventClass
	 * @return
	 */
	public static Event getRegularEventByClass(Class regularEventClass)
	{
		Event returnEvent = null;

		returnEvent = EventControlPanel.masterEventHandler.dispatcher.getRegularEventByClass(regularEventClass);

		return returnEvent;
	}


	/**
	 * This method queues ap an event to be executed as soon as possible.
	 * @param event Event to queue up for execution.
	 */
	public static void fireEvent(Event event)
	{
		boolean shouldPause =
			EventControlPanel.isPaused() &&
				!Event.isInPauseExceptions(event.getClass());

		if (EventControlPanel.masterEventHandler.running && !shouldPause && EventControlPanel.events != null)
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
		boolean rv = false;

		if (EventControlPanel.events != null)
		{
			rv = !EventControlPanel.events.isEmpty();
		}

		return rv;
	}


	/**
	 * This method fetches next Event from the event queue. It might return null.
	 * @return Event retrieved from the event queue.
	 */
	public static Event nextEvent()
	{
		return EventControlPanel.events.poll();
	}


	public static void print(String string)
	{
		if (consoleLogging)
		{
			System.out.println(System.currentTimeMillis() - time + " " + string);
		}
	}


	public static void toggleConsoleLogging()
	{
		if (EventControlPanel.consoleLogging)
		{
			EventControlPanel.consoleLogging = false;
		}
		else
		{
			EventControlPanel.consoleLogging = true;
		}
	}

	public static void pause()
	{
		synchronized (EventControlPanel.events)
		{
			EventControlPanel.pause = true;

			EventControlPanel.pauseBackup = EventControlPanel.events;
			EventControlPanel.events = new EventQueue();
		}
	}


	public static void unPause()
	{
		synchronized (EventControlPanel.pauseBackup)
		{
			EventControlPanel.pause = false;
			EventControlPanel.events = EventControlPanel.pauseBackup;
			EventControlPanel.pauseBackup = null;
		}
	}

	public static boolean isPaused()
	{
		return EventControlPanel.pause;
	}


	private static boolean pause = false;

	public static Thread eventThread;
	public static MasterEventHandler masterEventHandler;
	private static EventQueue events;

	private static EventQueue pauseBackup;

	private static long time;

	protected static boolean consoleLogging = false;
}
