package com.example.looksync;

/*import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}*/


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
 
public class MainActivity extends Activity implements OnItemClickListener {
  private TextView coucou = null;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
         
    coucou = new TextView(this);
    coucou.setText("Bonjour, vous me devez 1 000 000€.");
    setContentView(coucou);
    
    /*
    //on fait un test de connectivité
    UseCIFS test = new UseCIFS();
    test.setIp("192.168.1.123");
    test.setMdp("afabuler");
    test.setUser("Afabulations");
    //connexion à proprement parler
    test.connectSMB(); //gné? ça marche? Ôo
    */
    
    //test de l'activité SambaSyncActivity
    //Il faudra le mettre dans un bouton quand on fera l'interface de l'accueil
    startActivity(new Intent(this, SambaSyncActivity.class));
  }

  
  //le code pour Samba devrait être appelé au clic
  //on va donc faire un click listener qu'il faudra compléter plus tard
  //pour les tests, on appelle tout dans on create
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
  
  
  
 
}
