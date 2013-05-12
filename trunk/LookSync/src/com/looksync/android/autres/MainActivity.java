package com.looksync.android.autres;

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
import android.os.Bundle;
import android.widget.TextView;
 
public class MainActivity extends Activity {
  private TextView coucou = null;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
         
    coucou = new TextView(this);
    coucou.setText("Bonjour, vous avez lancé l'aplication LookSync.");
    setContentView(coucou);
  }
 
}
