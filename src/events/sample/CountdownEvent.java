// Copyright (c) 2015.
// Author: Enifs

package events.sample;

import events.regularevents.RegularEvent;


public class CountdownEvent extends RegularEvent
{
	public CountdownEvent(long frequency)
	{
		super(frequency);
		this.setEventHandler(new CountdownEventHandler());
	}


	@Override
	public String toString()
	{
		return "Countdown " + x;
	}


	protected int x = 10;
}
