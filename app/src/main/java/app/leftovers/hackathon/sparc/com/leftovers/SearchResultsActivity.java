package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Loopj.HttpRequestClient;
import Models.ShortRecipe;



public class SearchResultsActivity extends Activity {
    private GridView resultsGridView;

    ArrayAdapter<String> resultsAdapter; // in use?
    private ArrayList<ShortRecipe> list;
    private HttpRequestClient request;
    private String csv_list;
    ArrayAdapter<ShortRecipe> recipesAdapter;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Bundle bundle = getIntent().getExtras();
        csv_list = bundle.getString("ingredientsString");

        progress = (ProgressBar)findViewById(R.id.SearchProgressBar);
        resultsGridView  = (GridView) findViewById(R.id.search_results_grid);

        list = new ArrayList<ShortRecipe>();
        recipesAdapter = new RecipesArrayAdapter(this, R.layout.recipes_lv_row, list);
        resultsGridView.setAdapter(recipesAdapter);
        String url = Constants.API_SEARCH + Constants.API_KEY + "&q=" + csv_list;
        Log.v("url", url);

        progress.setVisibility(View.VISIBLE);

        HttpRequestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.v("test", "test");

                JSONArray recipes = null;


                try {
                    recipes = response.getJSONArray("recipes");
                } catch (Exception E) {
                }


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

                        list.add(recipe);
                        recipesAdapter.notifyDataSetChanged();



                    } catch (Exception E) {
                    }



                }

                progress.setVisibility(View.INVISIBLE);

                if (list.size() < 1) {
                    Toast toast = Toast.makeText(SearchResultsActivity.this, "No Results found", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

        });




        }



}
