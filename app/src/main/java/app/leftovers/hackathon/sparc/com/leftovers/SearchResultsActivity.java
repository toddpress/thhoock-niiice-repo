package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Loopj.HttpRequestClient;
import Models.ShortRecipe;



public class SearchResultsActivity extends Activity {

    private ListView resultsListView;
    ArrayAdapter<String> resultsAdapter;
    private ArrayList<ShortRecipe> list;
    private HttpRequestClient request;
    private String csv_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Bundle bundle = getIntent().getExtras();
        csv_list = bundle.getString("ingredientsString");

        resultsListView = (ListView) findViewById(R.id.search_results_list);

        String url = Constants.API_SEARCH + Constants.API_KEY + "&q=" + csv_list;
        Log.v("url", url);

        HttpRequestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.v("test", "test");

                JSONArray recipes = null;


                try {
                  recipes = response.getJSONArray("recipes");
                } catch (Exception E){}


                int recipeLength = recipes.length();
                for (int i = 0; i < recipeLength; i++) {

                    JSONObject j = new JSONObject();

                    try {
                        j = recipes.getJSONObject(i);
                        ShortRecipe recipe = new ShortRecipe();
                        recipe.setTitle(j.getString("title"));
                        recipe.setUrl(j.getString("source_url"));
                        recipe.setRecipe_id(j.getString("recipe_id"));
                        recipe.setImage_url(j.getString("image_url"));

                    } catch (Exception E){}


                }
            }

        });

        }




        // add ingredients adapter


}
