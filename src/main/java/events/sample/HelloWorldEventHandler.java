// Copyright (c) 2015.
// Author: Enifs

package events.sample;

import events.EventHandler;


public class HelloWorldEventHandler implements EventHandler<HelloWorldEvent>
{
	@Override
	public void handleEvent(HelloWorldEvent event)
	{
		System.out.println(event.message);
	}
}
