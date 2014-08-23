package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Models.Ingredient;
import Models.ShortRecipe;
import app.leftovers.hackathon.sparc.com.leftovers.R;

public class SearchResultsActivity extends Activity {

    private ListView resultsListView;
    ArrayAdapter<String> resultsAdapter;
    private ArrayList<ShortRecipe> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        resultsListView = (ListView) findViewById(R.id.search_results_list);


        // add ingredients adapter
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
