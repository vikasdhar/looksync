package common;

import android.database.Cursor;

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
}
