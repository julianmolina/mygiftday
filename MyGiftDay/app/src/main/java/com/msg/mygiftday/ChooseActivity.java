package com.msg.mygiftday;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.doubleclick.b;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ChooseActivity extends FragmentActivity {

    private final Bundle bParams = new Bundle();
    SqlLiteHelper usdbh = null;
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        new FetchAutocomplete().execute();

        EditText edt = (EditText) findViewById(R.id.edtDate);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog fromDatePickerDialog = new DatePickerDialog(ChooseActivity.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
                        EditText edt = (EditText) findViewById(R.id.edtDate);
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        edt.setText(dateFormatter.format(newDate.getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                fromDatePickerDialog.show();
            }
        });


        Button btnCompartir = (Button) findViewById(R.id.btnCompartir);
        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertEvent().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class InsertEvent extends AsyncTask<Bundle, String, Boolean> {

        private ProgressBar progressBar;
        private String URL_INSERT_CLIENT = "http://54.148.104.160/mygiftday/api/index/insertevent";
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

                HttpPost httpPost = new HttpPost("http://54.148.104.160/mygiftday/api/index/insertevent");
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse;

                SqlLiteHelper usdbh = new SqlLiteHelper(ChooseActivity.this, "user", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();

                //Cursor status_bar = db.rawQuery("SELECT id_facebook FROM user where id = 1", null);
                int fb_id = 39029023;
                /**if (status_bar.moveToFirst()) {
                    do {
                        String id = status_bar.getString(1);
                        fb_id = Integer.parseInt(id);
                    } while(status_bar.moveToNext());
                }**/

                EditText url = (EditText) findViewById(R.id.edtUrl);
                EditText iwant = (EditText) findViewById(R.id.edtIwant);
                EditText date = (EditText) findViewById(R.id.edtDate);
                EditText amount = (EditText) findViewById(R.id.edtMonto);
                EditText value = (EditText) findViewById(R.id.edtValue);

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("id_facebook", fb_id);
                jsonObject.accumulate("id_event_type", bParams.getString("id_event_type"));
                jsonObject.accumulate("url", url.getText().equals("") ? "" : url.getText());
                jsonObject.accumulate("iwant", iwant.getText().equals("") ? "" : iwant.getText());
                jsonObject.accumulate("date", date.getText().equals("") ? "" : date.getText());
                jsonObject.accumulate("amount", amount.getText().equals("") ? "" : amount.getText());
                jsonObject.accumulate("value_product", value.getText().equals("") ? "" : value.getText());
                json = jsonObject.toString();
                Log.e("jjsjaksja", json);

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
            Log.e("lkdlasklda", this.respuesta);
            Intent service = new Intent(ChooseActivity.this, Gift.class);
            startActivity(service);
        }
    }

    public class FetchAutocomplete extends AsyncTask<String, Integer, String> {

        private String result;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String url = "http://54.148.104.160/mygiftday/api/index/eventos";

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                String json = "";
                JSONObject jsonObject = new JSONObject();
                json = jsonObject.toString();

                StringEntity se = new StringEntity(json);
                httpPost.setEntity(se);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                HttpResponse httpResponse = httpclient.execute(httpPost);
                this.result = EntityUtils.toString(httpResponse.getEntity());

            } catch (Exception e) {
                Log.e("FuckingException", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){

            if(!this.result.equals("")){

                try {

                    JSONArray clientes = new JSONArray(this.result);
                    List<Eventos> guys = new ArrayList<Eventos>();

                    for(int i = 0; i < clientes.length(); i++) {
                        JSONObject obj = clientes.getJSONObject(i);
                        // fill the ArrayList:
                        guys.add(new Eventos(obj.getInt("id_event_type"), obj.getString("event_type")));
                    }

                    Spinner s = (Spinner) findViewById(R.id.spinner);
                    MyAdapter adapter = new MyAdapter(guys);
                    // apply the Adapter:
                    s.setAdapter(adapter);
                    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Eventos item = (Eventos) parent.getItemAtPosition(position);
                            bParams.putString("id_event_type", String.valueOf(item.getIdEvent()));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            bParams.putString("id_event_type", "1");
                        }
                    });

                } catch (Exception e) {
                    Log.e("FuckingException2", e.getMessage());
                }

            }
        }
    }

    /**
     * This is your own Adapter implementation which displays
     * the ArrayList of "Guy"-Objects.
     */
    private class MyAdapter extends BaseAdapter implements SpinnerAdapter {

        /**
         * The internal data (the ArrayList with the Objects).
         */
        private final List<Eventos> data;

        public MyAdapter(List<Eventos> data){
            this.data = data;
        }

        /**
         * Returns the Size of the ArrayList
         */
        @Override
        public int getCount() {
            return data.size();
        }

        /**
         * Returns one Element of the ArrayList
         * at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        /**
         * Returns the View that is shown when a element was
         * selected.
         */
        @Override
        public View getView(int position, View recycle, ViewGroup parent) {

            TextView text;
            if (recycle != null){
                // Re-use the recycled view here!
                text = (TextView) recycle;
            } else {
                // No recycled view, inflate the "original" from the platform:
                text = (TextView) getLayoutInflater().inflate(
                        android.R.layout.simple_dropdown_item_1line, parent, false
                );
            }
            text.setTextColor(Color.BLACK);
            text.setText(data.get(position).event);
            return text;
        }


    }

    /**
     * A simple class which holds some information-fields
     * about some Guys.
     */
    private class Eventos {

        public int id_event;
        public String event;

        public Eventos(int id, String ev){
            this.id_event = id;
            this.event = ev;
        }

        public int getIdEvent() {
            return this.id_event;
        }

        public String getEvent() {
            return this.event;
        }
    }
}
