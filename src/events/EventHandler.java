// Copyright (c) 2015.
// Author: Enifs

package events;


public interface EventHandler<T extends Event>
{
	void handleEvent(T event);
}
