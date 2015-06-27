// Copyright (c) 2015.
// Author: Enifs

package events.regularevents;

import java.util.ArrayList;
import java.util.TreeSet;

import events.EventControlPanel;


/**
 * This class manages regular events.
 */
public class RegularEventDispatcher
{
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


	public void work()
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
		}
	}


	public void clearAllRegisteredEvents()
	{
		this.treeSet.clear();
		this.regularGameEvents.clear();
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
