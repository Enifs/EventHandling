# EventHandling
Event Handling!

This project is a fruit of dissatisfaction with Java built-in event management. It is still required and good for keyboard and mouse events, but anything custom written is a pain to get working.

To use this Event Handling system there are 3 things to get started.

1. `new EventControlPanel();` This line initializes event managing system.
2. See HelloWorldEvent.java for how to write a new event. <https://github.com/Enifs/EventHandling/blob/master/src/events/sample/HelloWorldEvent.java>
3. See HelloWorldEventHandler.java for how to write a new EventHandler. <https://github.com/Enifs/EventHandling/blob/master/src/events/sample/HelloWorldEventHandler.java>
4. Finish the party by calling `EventControlPanel.stopEventHandling();`

This is all that is required to start writing your event based application. 

It is also possible to register regular events, that are fired at a given frequency.

Have fun and easy coding!  
Enifs
