package com.looksync.android.views;

import com.looksync.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class SynchronizeTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synchronize_tab);
        
        
        //listener du bouton Synchroniser
        Button btnSynchroniser = (Button) findViewById(R.id.btn_synchroniser_maintenant);
        btnSynchroniser.setOnClickListener(new View.OnClickListener() {
    		//@Override
    		public void onClick(View v) {
                Intent myIntent = new Intent(SynchronizeTab.this, SynchronizeProgressTab.class);
                startActivity(myIntent);
            }
        });
    }
}