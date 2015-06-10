package events.sample;

import events.regularevents.RegularEvent;


/**
 * Created by Didzis on 07.06.2015..
 */
public class CountdownEvent extends RegularEvent
{
	public CountdownEvent(long frequency)
	{
		super(frequency);
		this.setEventHandler(new CountdownEventHandler());
	}

	protected int x = 10;
}
