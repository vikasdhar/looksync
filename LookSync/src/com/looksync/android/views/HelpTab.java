package com.looksync.android.views;

import com.looksync.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class HelpTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_tab);
        
        
        //listener du bouton A propos de LookSync
        Button btnAPropos = (Button) findViewById(R.id.btn_a_propos);
        btnAPropos.setOnClickListener(new View.OnClickListener() {
    		//@Override
    		public void onClick(View v) {
                Intent myIntent = new Intent(HelpTab.this, AboutTab.class);
                startActivity(myIntent);
            }
        });
    }
}