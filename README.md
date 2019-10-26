# DIYRules
This "app" is the result of a quick and dirty 3h coding session that had two goals:
* getting familiar with android development using kotlin
* creating a solution to turn on/off Do Not Disturb mode during the night (i.e. when the phone is plugged in)

Future Work _may_ include:
* Refactoring and general cleanup of this mess
* moving the functionality to a foreground service which can be started on device boot
* adding more triggers, such as
	* Geofence enter / exit
	* Alarm
* adding more effects, such as
	* turning WIFI on / off
	* turning Double tap to wake up on / off
* adding functionality so the user can combine triggers and effects as she likes, and general rule management
(points are in no particular order)  