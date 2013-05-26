package com.looksync.android.authenticator; //package com.example.android.samplesync.authenticator;

//import common.NetworkUtilities;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.accounts.NetworkErrorException; //
//import android.provider.SyncStateContract.Constants;
import android.text.TextUtils; //
import android.util.Log; //

import com.example.android.common.R;
import com.looksync.android.common.Constants;
import com.looksync.android.common.NetworkUtilities;

//voir exemple: SampleSyncAdapter
/**
 * This class is an implementation of AbstractAccountAuthenticator for
 * authenticating accounts //in the com.example.android.samplesync domain.
 */
public class Authenticator extends AbstractAccountAuthenticator{

	// Contexte du service d'authentification
	// Authentication Service context
    private Context mContext = null; //private final Context mContext;
    
    //initialisation du contexte
	public Authenticator(Context context) {
		super(context);
		mContext = context;
	}

	//ajoute un nouveau compte connu à l'authentificateur
	/**
     * {@inheritDoc}
     */
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response,
			String accountType, String authTokenType, String[] requiredFeatures, 
			Bundle options)
			throws NetworkErrorException { // 
		Log.v("Authenticator LOOKSYNC", "addAccount()");
        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response,
			Account account, Bundle options) throws NetworkErrorException { // 
		/*if (options != null && options.containsKey(AccountManager.KEY_PASSWORD)) {
            final String password =
                options.getString(AccountManager.KEY_PASSWORD);
            final boolean verified =
                onlineConfirmPassword(account.name, password);
            final Bundle result = new Bundle();
            result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, verified);
            return result;
        }
        // Launch AuthenticatorActivity to confirm credentials
        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AuthenticatorActivity.PARAM_USERNAME, account.name);
        intent.putExtra(AuthenticatorActivity.PARAM_CONFIRMCREDENTIALS, true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
            response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;*/
		return null;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response,
			String accountType) {
		return null; //throw new UnsupportedOperationException();
	}

	//si le compte est connu (on a déjà donné les identifiants), on renvoie un token comme quoi on est identifié
	/**
     * {@inheritDoc}
     */
	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options) //, Bundle loginOptions
			throws NetworkErrorException { //
		Log.v("Authenticator LOOKSYNC", "getAuthToken()");

        // If the caller requested an authToken type we don't support, then
        // return an error
		/*
        if (!authTokenType.equals(Constants.AUTHTOKEN_TYPE)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");
            return result;
        }
        */

        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        final AccountManager am = AccountManager.get(mContext);
        final String password = am.getPassword(account);
        if (password != null) {
/*            final String authToken = NetworkUtilities.authenticate(account.name, password);
            //final boolean verified =
            //	onlineConfirmPassword(account.name, password);
            if (!TextUtils.isEmpty(authToken)) { //if (verified) {
                final Bundle result = new Bundle();
                result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                result.putString(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);
                result.putString(AccountManager.KEY_AUTHTOKEN, authToken); //, password
                return result;
            }
*/        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity panel.
        // the password was missing or incorrect, return an Intent to an
        // Activity that will prompt the user for the password.
        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AuthenticatorActivity.PARAM_USERNAME, account.name);
        intent.putExtra(AuthenticatorActivity.PARAM_AUTHTOKEN_TYPE, authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getAuthTokenLabel(String authTokenType) {
		/*if (authTokenType.equals(Constants.AUTHTOKEN_TYPE)) {
            return mContext.getString(R.string.label);
        }*/
		return null;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response,
			Account account, String[] features) throws NetworkErrorException { //
		Log.v("AUthenticator LOOKSYNC", "hasFeatures()");
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
	}

	/**
     * Validates user's password on the server
     */
    /*private boolean onlineConfirmPassword(String username, String password) {
        return NetworkUtilities.authenticate(username, password,
            null/* Handler *//*, null*//* Context *//*);
    }*/
    
	/**
     * {@inheritDoc}
     */
	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options) //, Bundle loginOptions
			throws NetworkErrorException { //
		/*Account account, String authTokenType, Bundle loginOptions) {
        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AuthenticatorActivity.PARAM_USERNAME, account.name);
        intent.putExtra(AuthenticatorActivity.PARAM_AUTHTOKEN_TYPE,
            authTokenType);
        intent.putExtra(AuthenticatorActivity.PARAM_CONFIRMCREDENTIALS, false);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;*/
		return null;
	}
	
}
