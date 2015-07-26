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


	@Override
	public String toString()
	{
		return "This is a 'Hello World' event!";
	}


	protected String message = "Hello World!";
}
