package com.marija.listviewexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.countries);
        BaseAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TextView tv = (TextView) view;
                Intent intent = new Intent(MainActivity.this, ActivityB.class);
                intent.putExtra(ActivityB.EXTRA_COUNTRY_NAME,(String)listView.getItemAtPosition(position%countries.length));
                startActivity(intent);
            }
        });
    }

    private String[] countries = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola","Argentina"
            ,"Armenia","Austria","Bahamas","Bahrain", "Bangladesh","Barbados", "Belarus","Belgium",
            "Benin","Bhutan","Bolivia","Bosnia & Herzegovina","Botswana","Brazil","Bulgaria",
            "Burkina Faso","Burma","Burundi","Cambodia","Cameroon","Canada", "China","Colombia",
            "Comoros","Congo","Croatia","Cuba","Cyprus","Czech Republic","Denmark", "Georgia",
            "Germany","Ghana","Great Britain","Greece","Hungary","Holland","India","Iran","Iraq",
            "Italy","Somalia", "Spain", "Sri Lanka", "Sudan","Suriname", "Swaziland","Sweden",
            "Switzerland", "Syria","Uganda","Ukraine","United Arab Emirates","United Kingdom",
            "United States","Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam",
            "Yemen","Zaire","Zambia","Zimbabwe"};

    private int color = Color.BLACK;
    class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return countries.length * 10;
        }

        @Override
        public Object getItem(int position) {
            return countries[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textview ;//= (TextView)findViewById(position);
            Log.d("Marija", "Drawing view at position " + position);
            if( convertView == null) {
                textview = new TextView(MainActivity.this);
                textview.setTextSize(22);
                textview.setPadding(16, 16, 16, 16);
                Log.d("Marija", "Creating new view at position " + position);
            }else {
                textview = (TextView) convertView;
            }
            textview.setText(countries[position % countries.length]);
            textview.setTextColor(color);
            return textview;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_red:
                color = Color.BLUE;
                break;
            case R.id.action_pink:
                color = Color.MAGENTA;
                break;
            case R.id.action_blue:
                color = Color.BLUE;
                break;
        }
        listView.invalidateViews();

        return super.onOptionsItemSelected(item);
    }
}
