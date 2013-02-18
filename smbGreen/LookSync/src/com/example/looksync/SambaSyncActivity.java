package com.example.looksync;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SambaSyncActivity extends Activity {
	
	private EditText etxtIp;
	private EditText etxtMdp;
	private EditText etxtUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //boutons
        Button btnEnregistrer;
        Button btnSynchroniser;
        
        Log.i("activity", "SambaSyncActivity");
        setContentView(R.layout.activity_samba_sync);
        
        //ajout des listeners sur clic bouton
        btnEnregistrer = (Button)findViewById(R.id.btnEnregistrer);
        btnSynchroniser = (Button)findViewById(R.id.btnSynchroniser);
        
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				Log.i("clic", "btnEnregistrer - Activity SambaSyncActivity");
				//récupérer les valeurs sur clic bouton:
		        //récupérer les zones de texte éditables
		        etxtIp = (EditText)findViewById(R.id.txtAdresseIP);
		        etxtMdp = (EditText)findViewById(R.id.txtMdp);
		        etxtUser = (EditText)findViewById(R.id.txtSyncUser);
		        //récupérer les valeurs des zones de texte
		        String txtIp = etxtIp.getText().toString();
		        String txtMdp = etxtMdp.getText().toString();
		        String txtUser = etxtUser.getText().toString();	
		        
		        //sauvegarde dans BDD sqllite
			}
		});
        
        btnSynchroniser.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.i("clic", "btnSynchroniser - Activity SambaSyncActivity");
				//récupérer les valeurs sur clic bouton:
		        //récupérer les zones de texte éditables
		        etxtIp = (EditText)findViewById(R.id.txtAdresseIP);
		        etxtMdp = (EditText)findViewById(R.id.txtMdp);
		        etxtUser = (EditText)findViewById(R.id.txtSyncUser);
		        //récupérer les valeurs des zones de texte
		        String txtIp = etxtIp.getText().toString();
		        String txtMdp = etxtMdp.getText().toString();
		        String txtUser = etxtUser.getText().toString();		
		        
		        //appel de ce qui va bien pour synchroniser
		        UseCIFS test = new UseCIFS();
		        test.setIp("192.168.1.123");
		        test.setMdp("afabuler");
		        test.setUser("Afabulations");
		        //connexion à proprement parler
		        test.connectSMB(); //gné? ça marche? Ôo
			}
		});
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_samba_sync, menu);
        return true;
    }
}
