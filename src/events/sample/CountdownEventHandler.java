// Copyright (c) 2015.
// Author: Enifs

package events.sample;

import events.EventControlPanel;
import events.EventHandler;


public class CountdownEventHandler implements EventHandler<CountdownEvent>
{
	@Override
	public void handleEvent(CountdownEvent event)
	{
		System.out.println(event.x);
		event.x--;

		if (event.x == 0)
		{
			EventControlPanel.unRegisterRegularEvent(event);
		}
	}
}
