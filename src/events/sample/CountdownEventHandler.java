package events.sample;

import events.EventControlPanel;
import events.EventHandler;


/**
 * Created by Didzis on 07.06.2015..
 */
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
