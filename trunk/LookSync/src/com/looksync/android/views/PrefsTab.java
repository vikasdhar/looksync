package com.looksync.android.views;

import com.independentsoft.exchange.FindFolderResponse;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
import com.independentsoft.exchange.StandardFolder;
import com.looksync.android.R;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

public class PrefsTab extends PreferenceActivity{
	
	private static final String TAG = "PrefsTab";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   addPreferencesFromResource(R.xml.prefs);
	   
	   try
       {
		   Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428"); //TODO suppr
		   
		   FindFolderResponse findFolderResponse = service.findFolder(StandardFolder.CALENDAR);
		   
		   int nbFolders = findFolderResponse.getFolders().size();
		   //CharSequence[] entries = { "One", "Two", "Three" };
		   CharSequence[] entries = new String[nbFolders];
		   //CharSequence[] entryValues = { "1", "2", "3" };
		   CharSequence[] entryValues = new String[nbFolders];
		   for (int i = 0; i < nbFolders; i++)
	       {
			   entries[i] = findFolderResponse.getFolders().get(i).getDisplayName();
			   entryValues[i] = findFolderResponse.getFolders().get(i).getDisplayName();
	       }
		   
		   //calendrier créé de base
		   entries[nbFolders] = "Calendrier";
		   entryValues[nbFolders] = "Calendrier";
		   
		   ListPreference lp = (ListPreference)findPreference("liste_calendrier");
		   lp.setEntries(entries);
		   lp.setEntryValues(entryValues);
	   }
       catch (ServiceException e)
       {
	       	Log.d(TAG, e.getMessage());
	       	Log.d(TAG, e.getXmlMessage());

	       	e.printStackTrace();
       }
	}
}