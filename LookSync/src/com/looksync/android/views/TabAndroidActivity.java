package com.looksync.android.views;
 
import com.looksync.android.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
 
public class TabAndroidActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec; 
        Intent intent; 
        
        intent = new Intent().setClass(this, HomeTab.class);
 
        intent = new Intent().setClass(this, HomeTab.class);
        spec = tabHost
                .newTabSpec("Accueil")
                .setIndicator("Accueil",
                        res.getDrawable(android.R.drawable.ic_menu_view))
                .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, SynchronizeTab.class);
        spec = tabHost
                .newTabSpec("Synchroniser")
                .setIndicator("Synchroniser",
                        res.getDrawable(android.R.drawable.ic_menu_rotate))
                .setContent(intent);
        tabHost.addTab(spec);
 
        intent = new Intent().setClass(this, HelpTab.class);
        spec = tabHost
                .newTabSpec("Aide")
                .setIndicator("Info", res.getDrawable(android.R.drawable.ic_menu_help))
                .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, SettingsTab.class);
        spec = tabHost
                .newTabSpec("Configuration")
                .setIndicator("Réglages", res.getDrawable(android.R.drawable.ic_menu_manage))
                .setContent(intent);
        tabHost.addTab(spec);
        
        //TODO Quitter ic_menu_close_clear_cancel
        
        
        String value = getIntent().getStringExtra("indiceTabCourant"); //        
        if(value != null && value != ""){
        	tabHost.setCurrentTab(Integer.parseInt(value));
        	Log.i("TAG", "Paramètre indice tab courant : " + value);
        }
        else {
        	tabHost.setCurrentTab(0);
        	Log.i("TAG", "Aucun indice tab courant passé en paramètre");
        }
    }
}