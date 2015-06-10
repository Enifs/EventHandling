

package events.regularevents;

import events.Event;


public class RegularEvent extends Event
{
	public RegularEvent(long frequency)
	{
		super();
		this.frequency = frequency;
	}

	long frequency;
}
