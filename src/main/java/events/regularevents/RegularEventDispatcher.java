// Copyright (c) 2015.
// Author: Enifs

package events.regularevents;

import java.util.ArrayList;
import java.util.TreeSet;

import events.Event;
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


	/**
	 * This method allows un unregister regular event by it class.
	 *
	 * This method is not optimized, and it slow. If you have many regular events, try to use different
	 * method.
	 *
	 * @param regularEventClass
	 */
	public void unregisterEventClass(Class regularEventClass)
	{
		for (RegularEvent event : this.regularGameEvents)
		{
			if (event.getClass().equals(regularEventClass))
			{
				this.unregisterEvent(event);
			}
		}
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


	/**
	 * This method returns Event that correspond given regular event class.
	 * @param regularEventClass
	 * @return
	 */
	public Event getRegularEventByClass(Class regularEventClass)
	{
		Event returnEvent = null;

		for (Event event : this.regularGameEvents)
		{
			if (event.getClass().equals(regularEventClass))
			{
				returnEvent = event;
			}
		}

		return returnEvent;
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
