package events.sample;

import events.Event;


/**
 * Created by Didzis on 07.06.2015..
 */
public class HelloWorldEvent extends Event
{
	public HelloWorldEvent()
	{
		this.setEventHandler(new HelloWorldEventHandler());
	}

	protected String message = "Hello World!";
}
