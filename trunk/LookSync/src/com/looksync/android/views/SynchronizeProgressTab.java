package com.looksync.android.views;

import java.util.ArrayList;
import java.util.List;

import com.independentsoft.exchange.Appointment;
import com.looksync.android.R;
import com.looksync.android.common.NetworkUtilities;
import com.looksync.android.preferences.Preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
 
public class SynchronizeProgressTab extends Activity {
	
    private static final String TAG = "SynchronizeProgressTab";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synchronize_progress_tab);
        
        
        //listener du bouton Annuler
        Button btnAnnuler = (Button) findViewById(R.id.btn_annuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
    		//@Override
    		public void onClick(View v) {
                Intent myIntent = new Intent(SynchronizeProgressTab.this, TabAndroidActivity.class);
                myIntent.putExtra("indiceTabCourant", "1"); //TODO constante
                startActivity(myIntent);
            }
        });
        
        
        
        
        
        
        
        
        
        
    	
        //TODO important à mettre dans SyncAdapter
        
    	String calendrierASynchroniser = null;
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_calendriers); //TODO !!! autre activité
        Log.d(TAG, "ici");
    	int count = radioGroup.getChildCount();
        for (int i=0;i<count;i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
            	if(((RadioButton)o).isChecked()) {
            		calendrierASynchroniser = (String) ((RadioButton)o).getText();
            		Log.d(TAG, "Calendrier coché au final : " + calendrierASynchroniser);
            	}
            }
        }
    	
        List<Appointment> events;
    	try { //TODO
			events =
		         NetworkUtilities.fetchAppointment(/*account, authtoken,
		             mLastUpdated*/SynchronizeProgressTab.this, calendrierASynchroniser);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
    }
}