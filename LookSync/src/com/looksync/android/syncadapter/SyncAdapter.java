package com.looksync.android.syncadapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

//import com.example.android.samplesync.client.User;
//import com.example.android.samplesync.client.User.Status;
//import com.example.android.samplesync.platform.ContactManager;

import org.apache.http.ParseException;
import org.apache.http.auth.AuthenticationException;
import org.json.JSONException;

import com.independentsoft.exchange.Appointment;
import com.looksync.android.common.Constants;
import com.looksync.android.common.NetworkUtilities;
import com.looksync.android.models.OutlookAppointmentModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * SyncAdapter implementation for syncing sample SyncAdapter contacts to the
 * platform ContactOperations provider.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "SyncAdapter";

    private final AccountManager mAccountManager;
    private final Context mContext;

    private Date mLastUpdated;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
        mAccountManager = AccountManager.get(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
        ContentProviderClient provider, SyncResult syncResult) {
    	//List<User> users;
        //List<Status> statuses;
    	List<Appointment> events;
        //String authtoken = null;
//         try {
        	 // use the account manager to request the credentials
             /*authtoken =
                mAccountManager.blockingGetAuthToken(account,
                    Constants.AUTHTOKEN_TYPE, true *//* notifyAuthFailure *//*);*/
             // fetch updates from the sample service over the cloud
             /*users =
                NetworkUtilities.fetchFriendUpdates(account, authtoken,
                    mLastUpdated);*/
//        	 try { //TODO
//				events =
//			         NetworkUtilities.fetchAppointment(/*account, authtoken,
//			             mLastUpdated*/);
//			} catch (java.text.ParseException e) {
//				e.printStackTrace();
//			}
/*            // update the last synced date.
            mLastUpdated = new Date();
            // update platform contacts.
            Log.d(TAG, "Calling contactManager's sync contacts");
            ContactManager.syncContacts(mContext, account.name, users);
            // fetch and update status messages for all the synced users.
            statuses = NetworkUtilities.fetchFriendStatuses(account, authtoken);
            ContactManager.insertStatuses(mContext, account.name, statuses);
        } catch (final AuthenticatorException e) {
            syncResult.stats.numParseExceptions++;
            Log.e(TAG, "AuthenticatorException", e);
        } catch (final OperationCanceledException e) {
            Log.e(TAG, "OperationCanceledExcetpion", e);
        } catch (final IOException e) {
            Log.e(TAG, "IOException", e);
            syncResult.stats.numIoExceptions++;
        } catch (final AuthenticationException e) {
            mAccountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE,
                authtoken);
            syncResult.stats.numAuthExceptions++;
            Log.e(TAG, "AuthenticationException", e);
        } catch (final ParseException e) {
            syncResult.stats.numParseExceptions++;
            Log.e(TAG, "ParseException", e);
        } catch (final JSONException e) {
            syncResult.stats.numParseExceptions++;
            Log.e(TAG, "JSONException", e);
        }*/
    }
}
