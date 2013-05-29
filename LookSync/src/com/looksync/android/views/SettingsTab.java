package com.looksync.android.views;

import com.looksync.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class SettingsTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_tab);
        
        
        //listener du bouton A propos de LookSync
        Button btnAPropos = (Button) findViewById(R.id.btn_a_propos);
        btnAPropos.setOnClickListener(new View.OnClickListener() {
    		//@Override
    		public void onClick(View v) {
                Intent myIntent = new Intent(SettingsTab.this, AboutTab.class);
                startActivity(myIntent);
            }
        });
    }
}