// Copyright (c) 2015.
// Author: Enifs

package events.regularevents;

import java.util.*;

import events.Event;


/**
 * This class manages regular events.
 */
public class RegularEventDispatcher
{
	public void registerEvent(RegularEvent event)
	{
		if (!this.regularEventSet.contains(event))
		{
			this.regularEvents.add(event);
			this.regularEventSet.add(event);
			event.fire();
			this.treeSet.add(planNextExecution(event));

			// This 1 milisecond sleep prevents situation where an event is not put in
			// tree set for some reason.

//			try
//			{
//				Thread.sleep(1);
//			}
//			catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
		}
	}


	public void unregisterEvent(RegularEvent event)
	{
		if (this.regularEventSet.contains(event))
		{
			this.regularEventSet.remove(event);
			this.regularEvents.remove(event);
		}
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
		for (RegularEvent event : this.regularEvents)
		{
			if (event.getClass().equals(regularEventClass))
			{
				this.unregisterEvent(event);
			}
		}
	}


	private ExecutionTimeWrapper planNextExecution(RegularEvent event)
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

				if (this.regularEvents.contains(eventWrapper.event))
				{
					treeSet.add(planNextExecution(eventWrapper.event));
				}
			}

			if (this.treeSet.size() < this.regularEvents.size())
			{
				Set<Event> treeSetEvents = new HashSet<>();

				for (ExecutionTimeWrapper wrapper : this.treeSet)
				{
					treeSetEvents.add(wrapper.event);
				}

				for (RegularEvent event : this.regularEvents)
				{
					if (!treeSetEvents.contains(event))
					{
						this.treeSet.add(this.planNextExecution(event));
					}
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

		for (Event event : this.regularEvents)
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
		this.regularEvents.clear();
		this.regularEventSet.clear();
	}


	// ---------------------------------------------------------------------
	// Section: Private classes
	// ---------------------------------------------------------------------


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


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private ArrayList<RegularEvent> regularEvents = new ArrayList<RegularEvent>();

	private Set<RegularEvent> regularEventSet = new HashSet<RegularEvent>();

	private TreeSet<ExecutionTimeWrapper> treeSet = new TreeSet<ExecutionTimeWrapper>();
}
