package com.looksync.android.views;

import com.looksync.android.R;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
//import android.provider.SyncStateContract.Constants;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.looksync.android.R;
import com.looksync.android.common.Constants;
import com.looksync.android.common.NetworkUtilities;







import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.independentsoft.exchange.Appointment;
import com.independentsoft.exchange.Body;
import com.independentsoft.exchange.ItemId;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;

/**
 * Activity which displays settings screen to the user.
 */
public class SettingsTab_ancien extends Activity {
	
	public static final String PARAM_CONFIRMCREDENTIALS = "confirmCredentials";
    public static final String PARAM_PASSWORD = "Utilisatéur428"; //"password";
    public static final String PARAM_USERNAME = "Administrateur"; //"username";
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    private static final String TAG = "SettingsTab"; //"AuthenticatorActivity";

    private AccountManager mAccountManager;
    private Thread mAuthThread;
    private String mAuthtoken;
    private String mAuthtokenType;

    /**
     * If set we are just checking that the user knows their credentials; this
     * doesn't cause the user's password to be changed on the device.
     */
    private Boolean mConfirmCredentials = false;

    /** for posting authentication attempts back to UI thread */
    private final Handler mHandler = new Handler();
    private TextView mMessage;
    private String mServer; //
    private EditText mServerEdit;
    private String mPassword;
    private EditText mPasswordEdit;

    /** Was the original caller asking for an entirely new account? */
    protected boolean mRequestNewAccount = false;

    private String mUsername;
    private EditText mUsernameEdit;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle icicle) { //Bundle savedInstanceState
        Log.i(TAG, "onCreate(" + icicle + ")");
        super.onCreate(icicle);
        //mAccountManager = AccountManager.get(this);
        Log.i(TAG, "loading data from Intent");
        //final Intent intent = getIntent();
        //mUsername = intent.getStringExtra(PARAM_USERNAME);
        //mAuthtokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        mRequestNewAccount = mUsername == null;
        /*mConfirmCredentials =
            intent.getBooleanExtra(PARAM_CONFIRMCREDENTIALS, false);*/

        Log.i(TAG, "    request new: " + mRequestNewAccount);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.settings_tab_ancien); //setContentView(R.layout.login_activity_test);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
            android.R.drawable.ic_dialog_alert);

        mMessage = (TextView) findViewById(R.id.message);
        mServerEdit = (EditText) findViewById(R.id.server_edit);
        mUsernameEdit = (EditText) findViewById(R.id.username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);

        mUsernameEdit.setText(mUsername);
        mMessage.setText(getMessage());
        
        
        
        
        //listener du bouton A propos de LookSync
        Button btnAPropos = (Button) findViewById(R.id.btn_a_propos);
        btnAPropos.setOnClickListener(new View.OnClickListener() {
    		//@Override
    		public void onClick(View v) {
                Intent myIntent = new Intent(SettingsTab_ancien.this, AboutTab.class);
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
                if (mAuthThread != null) {
                    mAuthThread.interrupt();
                    finish();
                }
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
    public void handleLogin(View view) {
        if (mRequestNewAccount) {
            mUsername = mUsernameEdit.getText().toString();
        }
        mServer = mServerEdit.getText().toString();
        mPassword = mPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(mServer) || TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            mMessage.setText(getMessage());
        } else {
            showProgress();
            // Start authenticating...
            /*mAuthThread =
        		NetworkUtilities.attemptAuth(mUsername, mPassword);
                //NetworkUtilities.attemptAuth(mUsername, mPassword, mHandler,
                    //AuthenticatorActivity.this);*/
            
            
            
            //Service service = new Service("https://myserver/ews/Exchange.asmx", "username", "password");
	    	//Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "LOOKSYNC\\Administrateur", "Utilisatéur428"); => faux
            //Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428");
            //ou : Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428", "LOOKSYNC");
            try
            {
	            Service service = new Service("https://" + mServer + "/ews/Exchange.asmx", mUsername, mPassword);
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
        /*if (result) {
            if (!mConfirmCredentials) {
                finishLogin();
            } else {
                finishConfirmCredentials(true);
            }
        } else {*/
        if(!result) {
            Log.e(TAG, "onAuthenticationResult: failed to authenticate");
            if (mRequestNewAccount) {
                // "Please enter a valid username/password.
                mMessage
                    .setText(getText(R.string.login_activity_loginfail_text_both));
            } else {
                // "Please enter a valid password." (Used when the
                // account is already in the database but the password
                // doesn't work.)
                mMessage
                    .setText(getText(R.string.login_activity_loginfail_text_pwonly));
            }
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