package com.msg.mygiftday;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private CallbackManager callbackManager;
    private final Bundle bParams = new Bundle();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        this.fbInitialize();
    }

    private void fbInitialize() {

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                ProfileTracker p = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {

                        Profile profileFB;

                        if (profile == null) {
                            profileFB = profile2;
                        } else {
                            profileFB = profile;
                        }

                        if (!profileFB.getId().equals("")) {

                            bParams.putString("id_facebook", profileFB.getId());
                            bParams.putString("name", profileFB.getFirstName()+ " "+profileFB.getMiddleName());
                            bParams.putString("last", profileFB.getLastName());

                        }
                    }
                };
                p.startTracking();

                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (user != null) {
                            if (user.optString("birthday") != null) {
                                bParams.putString("birthday", user.optString("birthday"));
                                bParams.putString("email", user.optString("email"));
                                Log.e("Jeisson", user.optString("email"));
                            }
                        }
                    }
                }).executeAsync();

                new InsertClient().execute();

            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("FbExError", exception.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class InsertClient extends AsyncTask<Bundle, String, Boolean> {

        private ProgressBar progressBar;
        private String URL_INSERT_CLIENT = "http://54.148.104.160/mygiftday/api/index/insert";
        private String json = "";
        private String respuesta = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
            this.progressBar.setVisibility(View.VISIBLE);
        }

        protected Boolean doInBackground(Bundle... urls) {

            try {

                HttpPost httpPost = new HttpPost(URL_INSERT_CLIENT);
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse;

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("id_facebook", bParams.getString("id_facebook"));
                jsonObject.accumulate("name", bParams.getString("name"));
                jsonObject.accumulate("last", bParams.getString("last"));
                jsonObject.accumulate("email", bParams.getString("email"));
                jsonObject.accumulate("birthday", bParams.getString("birthday"));
                json = jsonObject.toString();

                StringEntity se = new StringEntity(json);
                httpPost.setEntity(se);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                httpResponse = httpclient.execute(httpPost);
                this.respuesta = EntityUtils.toString(httpResponse.getEntity());

            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            this.progressBar.setVisibility(View.GONE);
            SqlLiteHelper usdbh = new SqlLiteHelper(MainActivity.this, "user", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            db.execSQL("INSERT INTO user (id, id_facebook) VALUES (1, "+bParams.getString("id_facebook")+")");
            Intent service = new Intent(MainActivity.this, Search.class);
            startActivity(service);
        }
    }
}
