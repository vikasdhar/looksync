package com.looksync.android.authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//voir exemple: SampleSyncAdapter
/**
 * Service to handle Account authentication. It instantiates the authenticator
 * and returns its IBinder.
 */
public class AuthenticatorService_test extends Service{
	private static final String TAG = "LookSyncAuthenticationService"; //"AuthenticationService";
	private Authenticator_test mAuthenticator;

    @Override
    public void onCreate() {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "LookSyncAuthenticationService Authentication Service started.");
        }
        mAuthenticator = new Authenticator_test(this);
    }

    @Override
    public void onDestroy() {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "LookSyncAuthenticationService Authentication Service stopped.");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "getBinder()...  returning the AccountAuthenticator binder for intent "
                    + intent);
        }
        return mAuthenticator.getIBinder();
    }
}
