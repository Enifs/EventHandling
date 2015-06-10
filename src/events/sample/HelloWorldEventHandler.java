package events.sample;

import events.EventHandler;


/**
 * Created by Didzis on 07.06.2015..
 */
public class HelloWorldEventHandler implements EventHandler<HelloWorldEvent>
{
	@Override
	public void handleEvent(HelloWorldEvent event)
	{
		System.out.println(event.message);
	}
}
