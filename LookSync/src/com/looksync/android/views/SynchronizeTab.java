package com.looksync.android.views;

import com.looksync.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;





import com.independentsoft.exchange.FindFolderResponse;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
import com.independentsoft.exchange.StandardFolder;
 
public class SynchronizeTab extends Activity {
	
	private static final String TAG = "SynchronizeTab";
	private RadioGroup mRadioGroup;
	
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
        
        
        try
        {
        	Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428"); //TODO
        	
            FindFolderResponse findFolderResponse = service.findFolder(StandardFolder.CALENDAR);

            if(findFolderResponse.getFolders().size() > 0) {
                mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_calendriers); 
                
	            //List calendar folders
	            for (int i = 0; i < findFolderResponse.getFolders().size(); i++)
	            {
	                Log.i(TAG, findFolderResponse.getFolders().get(i).getDisplayName());
	                Log.i(TAG, findFolderResponse.getFolders().get(i).getFolderId().getId());
	                
	                //adding a radio button programmatically
	                RadioButton newRadioButton = new RadioButton(this);
	                newRadioButton.setText("Synchroniser " + findFolderResponse.getFolders().get(i).getDisplayName());
	                /*newRadioButton.setId(i);
	                LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
	                        RadioGroup.LayoutParams.WRAP_CONTENT,
	                        RadioGroup.LayoutParams.WRAP_CONTENT);*/
	                mRadioGroup.addView(newRadioButton, 0/*, layoutParams*/);
	            }
            }
        }
        catch (ServiceException e)
        {
        	Log.i(TAG, e.getMessage());
        	Log.i(TAG, e.getXmlMessage());

            e.printStackTrace();
        }
    }
}