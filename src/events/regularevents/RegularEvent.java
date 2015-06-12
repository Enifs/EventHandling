// Copyright (c) 2015.
// Author: Enifs

package events.regularevents;

import events.Event;


/**
 * This is a type of event that must be initialized with frequency.
 * It gets periodical tasks done, like changing what clock shows every second.
 *
 * Class extending this one should have following line in its constructor -
 * this.setEventHandler(new EventHandler());
 */
public abstract class RegularEvent extends Event
{
	/**
	 * The default constructor of a regular event.
	 * @param frequency
	 */
	public RegularEvent(long frequency)
	{
		super();
		this.frequency = frequency;
	}

	long frequency;
}
