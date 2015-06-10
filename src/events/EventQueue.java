
package events;


import java.util.LinkedList;


public class EventQueue extends LinkedList<Event>
{
	@Override
	public boolean offer(Event event)
	{
		boolean rv = Event.isEnabled(event.getClass());

		if (rv)
		{
			super.offer(event);
		}

		return rv;
	}


	public static EventQueue events = new EventQueue();
}
