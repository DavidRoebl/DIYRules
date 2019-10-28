# AutoMate
Your buddy in automating small but tiresome tasks directly on your Android phone.

Current support is limited to Android 9.0 and only tested on a Nokia 7.2

## Goals, history
This "app" is the result of a quick and dirty 3h coding session that had two goals:
* getting familiar with android development using kotlin
* creating a solution to turn on/off Do Not Disturb mode during the night (i.e. when the phone is plugged in)

Addendum:
* if this app should become ready for publishing, it should explain _in detail_ to the user why every permission is 
needed, what the permission allows us to do on the phone, what part of that we use and why. This is so that every user
is able to make informed decisions, but also to educate the user, so she can understand the workings of her phone better.

## Future Work _may_ include:
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

## Call for participants
Currently I am not looking for developers to support this idea.
If by chance a UI/UX designer happens across this project: I could really use your help ;)
E-Mail contact will be posted here as soon as I have a project website up and running.