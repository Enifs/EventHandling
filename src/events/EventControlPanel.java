// Copyright (c) 2015.
// Author: Enifs

package events;

import events.regularevents.RegularEvent;
import events.regularevents.RegularEventDispatcher;


public class EventControlPanel
{
	public EventControlPanel()
	{
		this.startEventHandling();
	}


	public void startEventHandling()
	{
		EventControlPanel.events = new EventQueue();

		EventControlPanel.masterEventHandler = new MasterEventHandler();
		EventControlPanel.eventThread = new Thread(masterEventHandler);
		EventControlPanel.eventThread.start();

		EventControlPanel.dispatcher = new RegularEventDispatcher();
		EventControlPanel.regularEventThread = new Thread(EventControlPanel.dispatcher);
		EventControlPanel.regularEventThread.start();

		EventControlPanel.events.clear();
	}

	public void stopEventHandling()
	{
		// todo: stop is deprecated maybe a better solutions exist.
		EventControlPanel.eventThread.stop();
		EventControlPanel.masterEventHandler = null;

		EventControlPanel.regularEventThread.stop();
		EventControlPanel.dispatcher = null;

		EventControlPanel.events.clear();
	}

	public static void registerRegularEvent(RegularEvent event)
	{
		EventControlPanel.dispatcher.registerEvent(event);
	}

	public static void unRegisterRegularEvent(RegularEvent event)
	{
		EventControlPanel.dispatcher.unregisterEvent(event);
	}


	public static void fireEvent(Event event)
	{
		EventControlPanel.events.offer(event);
	}

	public static boolean hasEvents()
	{
		return !EventControlPanel.events.isEmpty();
	}

	public static Event nextEvent()
	{
		return EventControlPanel.events.poll();
	}


	private static RegularEventDispatcher dispatcher;
	private static Thread regularEventThread;
	private static Thread eventThread;
	private static MasterEventHandler masterEventHandler;
	private static EventQueue events;
}
