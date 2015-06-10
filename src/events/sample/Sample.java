package events.sample;

import events.EventControlPanel;


/**
 * Created by Didzis on 07.06.2015..
 */
public class Sample
{
	public static void main(String[] args)
	{
		new Sample().run();
	}


	private void run()
	{
		new EventControlPanel();

		EventControlPanel.registerRegularEvent(new CountdownEvent(500));

		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		new HelloWorldEvent().fire();
	}
}
