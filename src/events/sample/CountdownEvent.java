package events.sample;

import events.regularevents.RegularEvent;


public class CountdownEvent extends RegularEvent
{
	public CountdownEvent(long frequency)
	{
		super(frequency);
		this.setEventHandler(new CountdownEventHandler());
	}

	protected int x = 10;
}
