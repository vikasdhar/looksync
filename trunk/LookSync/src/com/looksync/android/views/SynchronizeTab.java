package com.looksync.android.views;

import java.util.List;

import com.looksync.android.R;
import com.looksync.android.authenticator.ServiceExchange;
import com.looksync.android.handlerCalendars.CalendarHandler;
import com.looksync.android.handlerCalendars.CalendarOutlookHandler;
import com.looksync.android.preferences.Preferences;

//import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;





import com.independentsoft.exchange.FindFolderResponse;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
import com.independentsoft.exchange.StandardFolder;
 
public class SynchronizeTab extends Activity {
	
	/*public static final String PARAM_CONFIRMCREDENTIALS = "confirmCredentials";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";*/

	private static final String TAG = "SynchronizeTab"; //"AuthenticatorActivity";

    /*private AccountManager mAccountManager;
    private Thread mAuthThread;
    private String mAuthtoken;
    private String mAuthtokenType;*/

    /**
     * If set we are just checking that the user knows their credentials; this
     * doesn't cause the user's password to be changed on the device.
     */
    //private Boolean mConfirmCredentials = false;

    /** for posting authentication attempts back to UI thread */
    //private final Handler mHandler = new Handler();
    private TextView mMessage;
    private String mServer;
    //private EditText mServerEdit;
    private String mPassword;
    //private EditText mPasswordEdit;

    /** Was the original caller asking for an entirely new account? */
    //protected boolean mRequestNewAccount = false;

    private String mUsername;
    //private EditText mUsernameEdit;
    
    
	private RadioGroup mRadioGroup;
	ServiceExchange service = new ServiceExchange();
	
	public void onCreate(Bundle icicle) { //Bundle savedInstanceState //TODO important ! entrer dedans chaque fois qu'on accède à l'onglet Synchroniser et non que la première fois
        Log.i(TAG, "onCreate(" + icicle + ")");
        super.onCreate(icicle);
        //mAccountManager = AccountManager.get(this);
        //Log.i(TAG, "loading data from Intent");
        //final Intent intent = getIntent();
        //mUsername = intent.getStringExtra(PARAM_USERNAME);
        //mAuthtokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        //mRequestNewAccount = mUsername == null;
        /*mConfirmCredentials =
            intent.getBooleanExtra(PARAM_CONFIRMCREDENTIALS, false);*/

        //Log.i(TAG, "    request new: " + mRequestNewAccount);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.synchronize_tab); //setContentView(R.layout.login_activity_test);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
            android.R.drawable.ic_dialog_alert);

        mMessage = (TextView) findViewById(R.id.message);
        /*mServerEdit = (EditText) findViewById(R.id.server_edit);
        mUsernameEdit = (EditText) findViewById(R.id.username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);

        mUsernameEdit.setText(mUsername);
        mMessage.setText(getMessage());*/
        
        
        
        Preferences prefs = new Preferences(SynchronizeTab.this);
        mServer = prefs.getServeur();
		mUsername = prefs.getNomUtilisateur();
		mPassword = prefs.getMotDePasse();
        
        StringBuilder builder = new StringBuilder();
        builder.append("Server: " + mServer + "\n");
        builder.append("Username: " + mUsername + "\n");
        builder.append("Password: " + mPassword + "\n");
        
        //textView.setText(builder.toString());
        Log.i(TAG, builder.toString());
        
        handleLogin();
        
        
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
        
    /*
     * {@inheritDoc}
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getText(R.string.ui_activity_authenticating));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Log.i(TAG, "dialog cancel has been invoked");
                /*if (mAuthThread != null) {
                    mAuthThread.interrupt();
                    finish();
                }*/
            }
        });
        return dialog;
    }

    /**
     * Handles onClick event on the Submit button. Sends username/password to
     * the server for authentication.
     * 
     * @param view The Submit button for which this method is invoked
     */
    public void handleLogin(/*View view*/) {
        //if (mRequestNewAccount) {
            //mUsername = mUsernameEdit.getText().toString();
        //}
        //mServer = mServerEdit.getText().toString();
        //mPassword = mPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(mServer) || TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            mMessage.setText(getMessage());
        } else {
            showProgress();
            // Start authenticating...
            /*mAuthThread =
        		NetworkUtilities.attemptAuth(mUsername, mPassword);
                //NetworkUtilities.attemptAuth(mUsername, mPassword, mHandler,
                    //AuthenticatorActivity.this);*/
            
            
            
            try
            {
            	service.setService(mServer, mUsername, mPassword);
	            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(TAG, "Successful authentication");
                }
	            onAuthenticationResult(true);
            }
            catch (Exception e)
            {
                Log.e(TAG, "Error authenticating");
                onAuthenticationResult(false);
            }
        }
    }

    /**
     * Called when response is received from the server for confirm credentials
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller.
     * 
     * @param the confirmCredentials result.
     */
    /*protected void finishConfirmCredentials(boolean result) {
        Log.i(TAG, "finishConfirmCredentials()");
        final Account account = new Account(mUsername, Constants.ACCOUNT_TYPE);
        mAccountManager.setPassword(account, mPassword);
        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_BOOLEAN_RESULT, result);
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }*/

    /**
     * 
     * Called when response is received from the server for authentication
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller. Also sets
     * the authToken in AccountManager for this account.
     * 
     * @param the confirmCredentials result.
     */

     /*protected void finishLogin() {
        Log.i(TAG, "finishLogin()");
        final Account account = new Account(mUsername, Constants.ACCOUNT_TYPE);

        if (mRequestNewAccount) {
            mAccountManager.addAccountExplicitly(account, mPassword, null);
            // Set contacts sync for this account.
            ContentResolver.setSyncAutomatically(account,
                ContactsContract.AUTHORITY, true);
        } else {
            mAccountManager.setPassword(account, mPassword);
        }
        final Intent intent = new Intent();
        mAuthtoken = mPassword;
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, mUsername);
        intent
            .putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);
        if (mAuthtokenType != null
            && mAuthtokenType.equals(Constants.AUTHTOKEN_TYPE)) {
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, mAuthtoken);
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    //}*/

    /**
     * 
     * Called when response is received from the server for authentication
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller. Also sets
     * the authToken in AccountManager for this account.
     * 
     * @param the confirmCredentials result.
     */

     protected void finishLogin() {
        Log.i(TAG, "finishLogin()");
        
        
        try
        {
            FindFolderResponse findFolderResponse = service.getService().findFolder(StandardFolder.CALENDAR);

            RadioButton newRadioButton;
            
            if(findFolderResponse.getFolders().size() > 0) {
                mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_calendriers); 
                
	            //List calendar folders
	            for (int i = 0; i < findFolderResponse.getFolders().size(); i++)
	            {
	                Log.d(TAG, findFolderResponse.getFolders().get(i).getDisplayName());
	                Log.d(TAG, findFolderResponse.getFolders().get(i).getFolderId().getId());
	                
	                //adding a radio button programmatically
	                newRadioButton = new RadioButton(this);
	                newRadioButton.setText(findFolderResponse.getFolders().get(i).getDisplayName());
	                /*newRadioButton.setId(i);
	                LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
	                        RadioGroup.LayoutParams.WRAP_CONTENT,
	                        RadioGroup.LayoutParams.WRAP_CONTENT);*/
	                mRadioGroup.addView(newRadioButton, 0/*, layoutParams*/);
	            }
            }
            
            newRadioButton = new RadioButton(this);
            newRadioButton.setText("Calendrier");
            mRadioGroup.addView(newRadioButton, 0);
            
            
            

            Preferences prefs = new Preferences(SynchronizeTab.this);
        	String mCalendrier = prefs.getCalendrierParDefaut();
        	Log.d(TAG, "Préférence calendrier : " + mCalendrier);
        	
        	RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_calendriers);
        	int count = radioGroup.getChildCount();
            //ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
            for (int i=0;i<count;i++) {
                View o = radioGroup.getChildAt(i);
                if (o instanceof RadioButton) {
                    //listOfRadioButtons.add((RadioButton)o);
                	if(((RadioButton)o).getText().equals(mCalendrier)) {
                		Log.d(TAG, "Calendrier coché suivant préférence : " + mCalendrier);
                		((RadioButton)o).setChecked(true);
                	}
                }
            }
            
            mRadioGroup.setVisibility(View.VISIBLE);
            
            Button mBtnSynchroniser = (Button) findViewById(R.id.btn_synchroniser_maintenant);
            mBtnSynchroniser.setEnabled(true);
        }
        catch (ServiceException e)
        {
        	Log.d(TAG, e.getMessage());
        	Log.d(TAG, e.getXmlMessage());

            e.printStackTrace();
        }
     }
     
    /**
     * Hides the progress UI for a lengthy operation.
     */
    protected void hideProgress() {
        dismissDialog(0);
    }

    /**
     * Called when the authentication process completes (see attemptLogin()).
     */
    public void onAuthenticationResult(boolean result) {
        Log.i(TAG, "onAuthenticationResult(" + result + ")");
        // Hide the progress dialog
        hideProgress();
        if (result) {
            //if (!mConfirmCredentials) {
                finishLogin();
            //} else {
            //    finishConfirmCredentials(true);
            //}
        } else {
        //if(!result) {
            Log.e(TAG, "onAuthenticationResult: failed to authenticate");
            //if (mRequestNewAccount) {
                // "Please enter a valid username/password.
                mMessage
                    .setText(getText(R.string.login_activity_loginfail_text_both));
            /*} else {
                // "Please enter a valid password." (Used when the
                // account is already in the database but the password
                // doesn't work.)
                mMessage
                    .setText(getText(R.string.login_activity_loginfail_text_pwonly));
            }*/
        }
    }

    /**
     * Returns the message to be displayed at the top of the login dialog box.
     */
    private CharSequence getMessage() {
        getString(R.string.label);
        if (TextUtils.isEmpty(mUsername)) {
            // If no username, then we ask the user to log in using an
            // appropriate service.
            final CharSequence msg =
                getText(R.string.login_activity_newaccount_text);
            return msg;
        }

        if (TextUtils.isEmpty(mServer)) { //
            // We have an account but no server
            return "Server missing";
        }
        
        if (TextUtils.isEmpty(mPassword)) {
            // We have an account but no password
            return getText(R.string.login_activity_loginfail_text_pwmissing);
        }
        return null;
    }

    /**
     * Shows the progress UI for a lengthy operation.
     */
    protected void showProgress() {
        showDialog(0);
    }
}