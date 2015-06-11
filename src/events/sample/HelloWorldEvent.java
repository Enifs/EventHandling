// Copyright (c) 2015.
// Author: Enifs

package events.sample;

import events.Event;


public class HelloWorldEvent extends Event
{
	public HelloWorldEvent()
	{
		this.setEventHandler(new HelloWorldEventHandler());
	}

	protected String message = "Hello World!";
}
