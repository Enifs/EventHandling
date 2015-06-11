// Copyright (c) 2015.
// Author: Enifs

package events;


/**
 * This is the Event Handler interface. Classes implementing this interface can be bound to one or
 * more event classes. When event is fired, this is the class that does the hard work.
 *
 * @param <T> User can implement this interface using  a type parameter to specify the Event this
 *           handler should know how to handle.
 */
public interface EventHandler<T extends Event>
{
	/**
	 * This method should contain all the stuff it has to do when event is being handled.
	 *
	 * @param event Type of this parameter is inherited from class declaration. The event is the
	 *                 same event this handler was bound to in the first place.
	 */
	void handleEvent(T event);
}
