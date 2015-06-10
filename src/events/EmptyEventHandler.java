package events;

/**
 * Created by Didzis on 07.06.2015..
 */
public class EmptyEventHandler implements EventHandler
{

	@Override
	public void handleEvent(Event event)
	{
		System.out.println(event.getClass() + " has no handler");
	}
}
