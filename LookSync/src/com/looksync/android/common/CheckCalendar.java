package com.looksync.android.common;

import android.database.Cursor;
import android.net.Uri;

//verifie si l'utilisateur a un calendrier installe sur le telephone ou non
//si pas de calendrier, pas la peine de faire une synchronisation...

public class CheckCalendar {
	//renvoie le nombre de dossiers calendrier de l'utilisateur
	private int getCalendarNumber()
	{
		/*
		 * Cursor cursor = getContentResolver().query(Uri.parse("content://calendar/calendars"),
		 */
		/*
                new String[] { "_id", "displayName" }, "selected=1", null, null);
		if (cursor != null && cursor.moveToFirst()) {
		     String[] calNames = new String[cursor.getCount()];
		     int[] calIds = new int[cursor.getCount()];
		     for (int i = 0; i < calNames.length; i++) {
		          // RETRIEVE THE CALENDAR NAMES AND IDS
		          // AT THIS STAGE YOU CAN PRINT OUT THE DISPLAY NAMES TO GET AN IDEA OF WHAT CALENDARS THE USER HAS
		          calIds[i] = cursor.getInt(0);
		          calNames[i] = cursor.getString(1);
		          cursor.moveToNext();
		      }
		      cursor.close();
		      if (calIds.length > 0) {
		           // WE'RE SAFE HERE TO DO ANY FURTHER WORK
		      }
		}
		*/
		
		return 0;
	}
	
	//récupère la liste de calendriers/vérifie les urls à utiliser
	//cf. MainCalendar.java
	/*retrieve a list of available calendars*/
    /*private MyCalendar m_calendars[];
    private String m_selectedCalendarId = "0";
    private void getCalendars() {
    	String[] l_projection = new String[]{"_id", "displayName"};
    	Uri l_calendars;
    	/////if (Build.VERSION.SDK_INT >= 8) {
    		l_calendars = Uri.parse("content://com.android.calendar/calendars");
    	} else {
    		l_calendars = Uri.parse("content://calendar/calendars");
    	}
    	Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, null, null, null);	//all calendars
    	//Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, "selected=1", null, null);   //active calendars
    	if (l_managedCursor.moveToFirst()) {
    		m_calendars = new MyCalendar[l_managedCursor.getCount()];
    		String l_calName;
    		String l_calId;
    		int l_cnt = 0;
    		int l_nameCol = l_managedCursor.getColumnIndex(l_projection[1]);
    		int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
    		do {
    			l_calName = l_managedCursor.getString(l_nameCol);
    			l_calId = l_managedCursor.getString(l_idCol);
    			m_calendars[l_cnt] = new MyCalendar(l_calName, l_calId);
    			++l_cnt;
    		} while (l_managedCursor.moveToNext());
    	}
    }*/
}
