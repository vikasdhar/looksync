package com.looksync.android.views;

import com.looksync.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class SynchronizeProgressTab extends Activity {
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
    }
}