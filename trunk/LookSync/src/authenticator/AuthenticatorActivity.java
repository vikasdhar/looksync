package authenticator;

import com.example.looksync.R;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.SyncStateContract.Constants;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

//voir exemple: SampleSyncAdapter
//activité demandant les identifiants à l'utilisateur
public class AuthenticatorActivity extends AccountAuthenticatorActivity {

	public static final String PARAM_USERNAME = null;
	
	public static final String PARAM_AUTHTOKEN_TYPE = null;
	
	//Méthodes d'interface graphique...
}
