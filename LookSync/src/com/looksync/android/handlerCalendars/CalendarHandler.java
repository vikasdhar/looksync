package com.looksync.android.handlerCalendars;

import java.util.List;

import com.independentsoft.exchange.Appointment;

public interface CalendarHandler {
	//méthodes pour manipuler les calendriers
	//readEvent, createEvent, updateEvent, deleteEvent
	//interface implémentée par les différents Handler de calendriers :
	// - package androidCalendar: CalendarAndroidHandler
	// - package outlookCalendar: CalendarOutlookHandler
	
	public List<Appointment> readEvents();
	
	public void createEvents();
	
	public void updateEvents(List<Appointment> events);
	
	public void deleteEvents(List<Appointment> events);
}
