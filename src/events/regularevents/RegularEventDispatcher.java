
package events.regularevents;


import java.util.ArrayList;
import java.util.TreeSet;


public class RegularEventDispatcher implements Runnable
{
	@Override
	public void run()
	{
		while(true)
		{
			if (!this.treeSet.isEmpty())
			{
				ExecutionTimeWrapper eventWrapper = treeSet.first();

				if (eventWrapper.nextExecution <= System.currentTimeMillis())
				{
					treeSet.pollFirst();
					eventWrapper.event.fire();

					if (this.regularGameEvents.contains(eventWrapper.event))
					{
						treeSet.add(planNextExecution(eventWrapper.event));
					}
				}
				else
				{
					long sleepTime = eventWrapper.nextExecution - System.currentTimeMillis();

					if (sleepTime > 0)
					{
						try
						{
							Thread.sleep(sleepTime);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void registerEvent(RegularEvent event)
	{
		this.regularGameEvents.add(event);
		event.fire();
		this.treeSet.add(planNextExecution(event));
	}

	public void unregisterEvent(RegularEvent event)
	{
		this.regularGameEvents.remove(event);
	}

	public ExecutionTimeWrapper planNextExecution(RegularEvent event)
	{
		return new ExecutionTimeWrapper(event, System.currentTimeMillis() + event.frequency);
	}

	ArrayList<RegularEvent> regularGameEvents = new ArrayList<RegularEvent>();
	TreeSet<ExecutionTimeWrapper> treeSet = new TreeSet<ExecutionTimeWrapper>();

	private class ExecutionTimeWrapper implements Comparable<ExecutionTimeWrapper>
	{
		public ExecutionTimeWrapper(RegularEvent event, long nextExecution)
		{
			this.event = event;
			this.nextExecution = nextExecution;
		}


		public RegularEvent event;
		public long nextExecution;


		@Override
		public int compareTo(ExecutionTimeWrapper o)
		{
			return Long.compare(this.nextExecution, o.nextExecution);
		}
	}
}
