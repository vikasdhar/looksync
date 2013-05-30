package com.looksync.android.common;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
/*import android.content.Context;
import android.os.Handler;*/
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.graphics.Bitmap; //
import android.graphics.BitmapFactory; //
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils; //

import java.io.BufferedReader; //
import java.io.ByteArrayOutputStream; //
import java.io.IOException; //
import java.io.InputStream; //
import java.io.InputStreamReader; //
import java.net.HttpURLConnection; //
import java.net.MalformedURLException; //
import java.net.URL; //
import java.util.ArrayList; //

import com.looksync.android.authenticator.ServiceExchange;
import com.looksync.android.models.OutlookAppointmentModel;
import com.looksync.android.preferences.Preferences;
import com.looksync.android.views.SynchronizeProgressTab;
import com.looksync.android.views.SynchronizeTab;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.independentsoft.exchange.And;
import com.independentsoft.exchange.Appointment;
import com.independentsoft.exchange.AppointmentPropertyPath;
import com.independentsoft.exchange.FindFolderResponse;
import com.independentsoft.exchange.FindItemResponse;
import com.independentsoft.exchange.Folder;
import com.independentsoft.exchange.FolderId;
import com.independentsoft.exchange.IsGreaterThanOrEqualTo;
import com.independentsoft.exchange.IsLessThanOrEqualTo;
import com.independentsoft.exchange.Restriction;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
import com.independentsoft.exchange.StandardFolder;


//voir exemple: SampleSyncAdapter
//utilisé par l'exemple SampleSyncAdapter pour se connecter à des serveurs tiers
/**
 * Provides utility methods for communicating with the server.
 */
public class NetworkUtilities {
	/** The tag used to log to adb console. */
    private static final String TAG = "NetworkUtilities";
    /** POST parameter name for the user's account name */
    public static final String PARAM_USERNAME = "LOOKSYNC\\Administrateur"; //"username";
    /** POST parameter name for the user's password */
    public static final String PARAM_PASSWORD = "Utilisatéur428"; //"password";
    //public static final String PARAM_UPDATED = "timestamp";
    //public static final String USER_AGENT = "AuthenticationService/1.0";
    //public static final int REGISTRATION_TIMEOUT = 30 * 1000; // ms
    /** POST parameter name for the user's authentication token */
    public static final String PARAM_AUTH_TOKEN = "authtoken"; //
    /** POST parameter name for the client's last-known sync state */
    public static final String PARAM_SYNC_STATE = "syncstate"; //
    /** POST parameter name for the sending client-edited contact info */
    public static final String PARAM_CONTACTS_DATA = "contacts"; //
    /** Timeout (in ms) we specify for each http request */
    public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000; //
    /** Base URL for the v2 Sample Sync Service */
    public static final String BASE_URL = 
    		"https://213.245.163.98"; //"https://samplesyncadapter2.appspot.com";
    /** URI for authentication service */
    public static final String AUTH_URI = BASE_URL + "/owa"; //"/auth";
    //public static final String FETCH_FRIEND_UPDATES_URI =
    	//BASE_URL + "/fetch_friend_updates";
    //public static final String FETCH_STATUS_URI = BASE_URL + "/fetch_status";
    //private static HttpClient mHttpClient;
    /** URI for sync service */
    public static final String SYNC_CONTACTS_URI = BASE_URL + "/sync"; //

    
    /*private NetworkUtilities() {
    } //*/
    
    
    /**
     * Configures the httpClient to connect to the URL provided.
     */
    /*public static void maybeCreateHttpClient() {
        if (mHttpClient == null) {
            mHttpClient = new DefaultHttpClient();
            final HttpParams params = mHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params,
                REGISTRATION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, REGISTRATION_TIMEOUT);
            ConnManagerParams.setTimeout(params, REGISTRATION_TIMEOUT);
        }
    }*/
    /*public static HttpClient getHttpClient() {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
        HttpConnectionParams.setSoTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
        ConnManagerParams.setTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
        return httpClient;
    }*/

    /**
     * Executes the network requests on a separate thread.
     * 
     * @param runnable The runnable instance containing network mOperations to
     *        be executed.
     */
    /*public static Thread performOnBackgroundThread(final Runnable runnable) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {

                }
            }
        };
        t.start();
        return t;
    }*/
    
    /**
     * Connects to the SampleSync test server, authenticates the provided
     * username and password. //the Voiper server
     *
     * @param username The server account username
     * @param password The server account password
     * //@param handler The hander instance from the calling UI thread.
     * //@param context The context of the calling Activity.
     * //@return String The authentication token returned by the server (or null)
     * //@return boolean The boolean result indicating whether the user was
     * //        successfully authenticated.
     */
    /*public static Service authenticate(String username, String password) {
    //public static String authenticate(String username, String password) {
    //public static boolean authenticate(String username, String password,
        //Handler handler, final Context context) {
        
        final HttpResponse resp;
        final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(PARAM_USERNAME, username));
        params.add(new BasicNameValuePair(PARAM_PASSWORD, password));
        final HttpEntity entity; //HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params);
        } catch (final UnsupportedEncodingException e) {
            // this should never happen.
            throw new IllegalStateException(e); //throw new AssertionError(e);
        }
        Log.i(TAG, "Authenticating to: " + AUTH_URI);
        final HttpPost post = new HttpPost(AUTH_URI);
        post.addHeader(entity.getContentType());
        post.setEntity(entity);
        try {
            resp = getHttpClient().execute(post); //resp = mHttpClient.execute(post);
            String authToken = null; //
            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream istream = (resp.getEntity() != null) ? resp.getEntity().getContent()
                        : null;
                if (istream != null) {
                    BufferedReader ireader = new BufferedReader(new InputStreamReader(istream));
                    authToken = ireader.readLine().trim();
                }
                /*if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(TAG, "Successful authentication");
                }
                sendResult(true, handler, context);
                return true;*/
    		/*}*/ /*else {
                if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(TAG, "Error authenticating" + resp.getStatusLine());
                }
                sendResult(false, handler, context);
                return false;
            }*/
    		/*if ((authToken != null) && (authToken.length() > 0)) {
                Log.v(TAG, "Successful authentication");
                return authToken;
            } else {
                Log.e(TAG, "Error authenticating" + resp.getStatusLine());
                return null;
            } //
        } catch (final IOException e) {
            Log.e(TAG, "IOException when getting authtoken", e);
            return null;
            *//*if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "IOException when getting authtoken", e);
            }
            sendResult(false, handler, context);
            return false;*/
    	/*} finally {
            Log.v(TAG, "getAuthtoken completing");
            *//*if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "getAuthtoken completing");
            }*/
        /*}
    }*/
    
    /**
     * Sends the authentication response from server back to the caller main UI
     * thread through its handler.
     * 
     * @param result The boolean holding authentication result
     * @param handler The main UI thread's handler instance.
     * @param context The caller Activity's context.
     */
    /*private static void sendResult(final Boolean result, final Handler handler,
        final Context context) {
        if (handler == null || context == null) {
            return;
        }
        handler.post(new Runnable() {
            public void run() {
                ((AuthenticatorActivity) context).onAuthenticationResult(result);
            }
        });
    }*/

    /**
     * Attempts to authenticate the user credentials on the server.
     * 
     * @param username The user's username
     * @param password The user's password to be authenticated
     * @param handler The main UI thread's handler instance.
     * @param context The caller Activity's context
     * @return Thread The thread on which the network mOperations are executed.
     */
    /*public static Thread attemptAuth(final String username,
		final String password) {
    //public static Thread attemptAuth(final String username,
        //final String password, final Handler handler, final Context context) {
        final Runnable runnable = new Runnable() {
            public void run() {
            	authenticate(username, password); //authenticate(username, password, handler, context);
            }
        };
        // run on background thread.
        return NetworkUtilities.performOnBackgroundThread(runnable);
    } //*/

    /**
     * Fetches the list of friend data updates from the server
     * 
     * @param account The account being synced.
     * @param authtoken The authtoken stored in AccountManager for this account
     * @param lastUpdated The last time that sync was performed
     * @return list The list of updates received from the server.
     * @throws java.text.ParseException 
     */
    public static List<Appointment> fetchAppointment(Context context, String calendrierASynchroniser) 
    	throws ParseException {
    	
        try
        {
        	Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428"); //TODO utiliser classe ServiceExchange
        	
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        	Preferences prefs = new Preferences(context);
        	String mPeriode = prefs.getPeriodeASynchroniser();
        	Log.d(TAG, "Préférence période : " + mPeriode);
        	
            //Date endTime = dateFormat.parse("2011-04-16 00:00:00");

        	FindItemResponse response;
        	Restriction restrict = null;
        	if(mPeriode != "tout") {
            	Calendar now = Calendar.getInstance();
	            now.add(Calendar.MONTH, -6); //TODO remettre Integer.parseInt(mPeriode)
	        	//Date startTime = dateFormat.parse("2011-04-15 00:00:00");
	            Date startTime = now.getTime();
	            Log.d(TAG, "Date de début période : " + startTime);
        	
	            IsGreaterThanOrEqualTo restriction1 = new IsGreaterThanOrEqualTo(AppointmentPropertyPath.START_TIME, startTime);

	        	//IsLessThanOrEqualTo restriction2 = new IsLessThanOrEqualTo(AppointmentPropertyPath.END_TIME, endTime);
	            //And restriction3 = new And(restriction1, restriction2);

	            restrict = restriction1;
    		}
        	
            FindFolderResponse findFolderResponse = service.findFolder(StandardFolder.CALENDAR);
            if(!calendrierASynchroniser.equals(calendrierASynchroniser))
            {
	            FolderId specifiedFolder = null;
	            for (int i = 0; i < findFolderResponse.getFolders().size(); i++) {
	            	if(findFolderResponse.getFolders().get(i).getDisplayName().equals(calendrierASynchroniser)) {
	            		specifiedFolder = findFolderResponse.getFolders().get(i).getFolderId();
	
	                	Log.d(TAG, "Calendrier spécifié : " + findFolderResponse.getFolders().get(i).getDisplayName());
	            	}
	            	break;
	            }
	            
	            response = service.findItem(specifiedFolder/*StandardFolder.CALENDAR*/, AppointmentPropertyPath.getAllPropertyPaths(), /*restriction3*/ restrict);
            }
            else
            {
            	response = null; //TODO important Traiter Calendrier créé de base
            }
            final ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
            
            for (int i = 0; i < response.getItems().size(); i++)
            {
                if (response.getItems().get(i) instanceof Appointment)
                {
                    Appointment appointment = (Appointment) response.getItems().get(i);

                    Log.d(TAG, "Subject = " + appointment.getSubject());
                    Log.d(TAG, "StartTime = " + appointment.getStartTime());
                    Log.d(TAG, "EndTime = " + appointment.getEndTime());
                    Log.d(TAG, "Body Preview = " + appointment.getBodyPlainText());
                    Log.d(TAG, "----------------------------------------------------------------");
                    
                    appointmentList.add(appointment);
                }
            }

            return appointmentList;
       }
        catch (ServiceException e)
        {
        	Log.e(TAG, e.getMessage());
        	Log.e(TAG, e.getXmlMessage());

            e.printStackTrace();
        }
        /*catch (ParseException e)
        {
            e.printStackTrace();
        }*/
        
        return null;
    }
    
    /**
     * Perform 2-way sync with the server-side contacts. We send a request that
     * includes all the locally-dirty contacts so that the server can process
     * those changes, and we receive (and return) a list of contacts that were
     * updated on the server-side that need to be updated locally.
     *
     * @param account The account being synced
     * @param authtoken The authtoken stored in the AccountManager for this
     *            account
     * @param serverSyncState A token returned from the server on the last sync
     * @param dirtyContacts A list of the contacts to send to the server
     * @return A list of contacts that we need to update locally
     */
    
    //Il faudra surement rajouter et utiliser une classe RawCalendar...
    
    /*
    public static List<RawContact> syncContacts(
            Account account, String authtoken, long serverSyncState, List<RawContact> dirtyContacts)
            throws JSONException, ParseException, IOException, AuthenticationException {
        // Convert our list of User objects into a list of JSONObject
        List<JSONObject> jsonContacts = new ArrayList<JSONObject>();
        for (RawContact rawContact : dirtyContacts) {
            jsonContacts.add(rawContact.toJSONObject());
        }

        // Create a special JSONArray of our JSON contacts
        JSONArray buffer = new JSONArray(jsonContacts);

        // Create an array that will hold the server-side contacts
        // that have been changed (returned by the server).
        final ArrayList<RawContact> serverDirtyList = new ArrayList<RawContact>();

        // Prepare our POST data
        final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(PARAM_USERNAME, account.name));
        params.add(new BasicNameValuePair(PARAM_AUTH_TOKEN, authtoken));
        params.add(new BasicNameValuePair(PARAM_CONTACTS_DATA, buffer.toString()));
        if (serverSyncState > 0) {
            params.add(new BasicNameValuePair(PARAM_SYNC_STATE, Long.toString(serverSyncState)));
        }
        Log.i(TAG, params.toString());
        HttpEntity entity = new UrlEncodedFormEntity(params);

        // Send the updated friends data to the server
        Log.i(TAG, "Syncing to: " + SYNC_CONTACTS_URI);
        final HttpPost post = new HttpPost(SYNC_CONTACTS_URI);
        post.addHeader(entity.getContentType());
        post.setEntity(entity);
        final HttpResponse resp = getHttpClient().execute(post);
        final String response = EntityUtils.toString(resp.getEntity());
        if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // Our request to the server was successful - so we assume
            // that they accepted all the changes we sent up, and
            // that the response includes the contacts that we need
            // to update on our side...
            final JSONArray serverContacts = new JSONArray(response);
            Log.d(TAG, response);
            for (int i = 0; i < serverContacts.length(); i++) {
                RawContact rawContact = RawContact.valueOf(serverContacts.getJSONObject(i));
                if (rawContact != null) {
                    serverDirtyList.add(rawContact);
                }
            }
        } else {
            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                Log.e(TAG, "Authentication exception in sending dirty contacts");
                throw new AuthenticationException();
            } else {
                Log.e(TAG, "Server error in sending dirty contacts: " + resp.getStatusLine());
                throw new IOException();
            }
        }

        return serverDirtyList;
    }
    */

    /**
     * Download the avatar image from the server.
     *
     * @param avatarUrl the URL pointing to the avatar image
     * @return a byte array with the raw JPEG avatar image
     */
    /*public static byte[] downloadAvatar(final String avatarUrl) {
        // If there is no avatar, we're done
        if (TextUtils.isEmpty(avatarUrl)) {
            return null;
        }

        try {
            Log.i(TAG, "Downloading avatar: " + avatarUrl);
            // Request the avatar image from the server, and create a bitmap
            // object from the stream we get back.
            URL url = new URL(avatarUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            try {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                final Bitmap avatar = BitmapFactory.decodeStream(connection.getInputStream(),
                        null, options);

                // Take the image we received from the server, whatever format it
                // happens to be in, and convert it to a JPEG image. Note: we're
                // not resizing the avatar - we assume that the image we get from
                // the server is a reasonable size...
                Log.i(TAG, "Converting avatar to JPEG");
                ByteArrayOutputStream convertStream = new ByteArrayOutputStream(
                        avatar.getWidth() * avatar.getHeight() * 4);
                avatar.compress(Bitmap.CompressFormat.JPEG, 95, convertStream);
                convertStream.flush();
                convertStream.close();
                // On pre-Honeycomb systems, it's important to call recycle on bitmaps
                avatar.recycle();
                return convertStream.toByteArray();
            } finally {
                connection.disconnect();
            }
        } catch (MalformedURLException muex) {
            // A bad URL - nothing we can really do about it here...
            Log.e(TAG, "Malformed avatar URL: " + avatarUrl);
        } catch (IOException ioex) {
            // If we're unable to download the avatar, it's a bummer but not the
            // end of the world. We'll try to get it next time we sync.
            Log.e(TAG, "Failed to download user avatar: " + avatarUrl);
        }
        return null;
    }*/
}
