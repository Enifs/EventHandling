package events;


import events.regularevents.RegularEvent;
import events.regularevents.RegularEventDispatcher;


/**
 * Created by Didzis on 06.06.2015..
 */
public class EventControlPanel
{
	public static void startEventHandling()
	{
		EventControlPanel.masterEventHandler = new MasterEventHandler();
		EventControlPanel.eventThread = new Thread(masterEventHandler);
		EventControlPanel.eventThread.start();

		EventControlPanel.dispatcher = new RegularEventDispatcher();
		EventControlPanel.regularEventThread = new Thread(EventControlPanel.dispatcher);
		EventControlPanel.regularEventThread.start();

		EventQueue.events.clear();
	}

	public static void stopEventHandling()
	{
		// todo: stop is deprecated maybe a better solutions exist.
		EventControlPanel.eventThread.stop();
		EventControlPanel.masterEventHandler = null;

		EventControlPanel.regularEventThread.stop();
		EventControlPanel.dispatcher = null;

		EventQueue.events.clear();
	}

	public static void registerRegularEvent(RegularEvent event)
	{
		EventControlPanel.dispatcher.registerEvent(event);
	}

	public static void unRegisterRegularEvent(RegularEvent event)
	{
		EventControlPanel.dispatcher.unregisterEvent(event);
	}

	private static RegularEventDispatcher dispatcher;
	private static Thread regularEventThread;
	private static Thread eventThread;
	private static MasterEventHandler masterEventHandler;
}
