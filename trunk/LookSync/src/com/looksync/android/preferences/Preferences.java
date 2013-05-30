package com.looksync.android.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.independentsoft.exchange.Service;
import com.looksync.android.views.SynchronizeTab;

public class Preferences {
	
	private SharedPreferences prefs;
	
	public Preferences(Context c) {
		prefs = PreferenceManager
        	    .getDefaultSharedPreferences(c);
	}
	
	/*private String serveur;
	
	private String nomUtilisateur;
	
	private String motDePasse;
	
	private String intervalleSynchronisation;
	
	private String periodeASynchroniser;
	
	private String calendrierParDefaut;*/

	public String getServeur() {
		return prefs.getString("serveur", "");
	}

	public String getNomUtilisateur() {
		return prefs.getString("nom_utilisateur", "");
	}

	public String getMotDePasse() {
		return prefs.getString("mdp", "");
	}

	public String getSensSynchronisation() {
		return prefs.getString("liste_sens", "");
	}
	
	public String getIntervalleSynchronisation() {
		return prefs.getString("intervalle", "");
	}

	public String getPeriodeASynchroniser() {
		return prefs.getString("liste_periode_options", "-1");
	}

	public String getCalendrierParDefaut() {
		return prefs.getString("liste_calendrier", "");
	}
	
}
