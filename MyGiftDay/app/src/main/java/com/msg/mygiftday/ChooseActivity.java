package com.msg.mygiftday;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ChooseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
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

    private void setSpinner(){

        Spinner s = (Spinner) findViewById(R.id.)

        // fill the ArrayList:
        List<Eventos> guys = new ArrayList<Eventos>();
        guys.add(new Eventos(, 18));
        guys.add(new Eventos(, 20));
        guys.add(new Eventos(, 50));
        MyAdapter adapter = new MyAdapter(guys);
        // apply the Adapter:
        s.setAdapter(adapter);
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
