package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

import Loopj.HttpRequestClient;
import Models.LongRecipe;


public class FullRecipeActivity extends Activity {

    private TextView fullTitle;
    private SmartImageView fullImage;
    private Button fullButton;
    private ListView fullIngredientsListView;
    private LongRecipe fullRecipe;
    private ArrayList<String> fullIngredientsArray = null;
    private ArrayAdapter<String> listAdapter = null;

    private ProgressBar progress;

    private String recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);

        Bundle bundle = getIntent().getExtras();
        recipeId = bundle.getString("fullRecipeId");

        fullIngredientsArray = new ArrayList<String>();
        progress = (ProgressBar)findViewById(R.id.fullProgressBar);


        fullTitle = (TextView)findViewById(R.id.fullRecipeName);
        fullImage = (SmartImageView)findViewById(R.id.fullRecipeImage);
        fullButton = (Button)findViewById(R.id.fullRecipeButton);
        fullIngredientsListView = (ListView)findViewById(R.id.fullRecipeListView);

        fullRecipe = new LongRecipe();


        String url = Constants.API_GET + Constants.API_KEY + "&rId=" + recipeId;
        Log.v("url", url);

        progress.setVisibility(View.VISIBLE);
        HttpRequestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                JSONObject j = null;

                try {
                    j = response.getJSONObject("recipe");
                } catch (Exception E) {
                }

                try {

                    //set full recipe object
                    fullRecipe.setTitle(j.getString("title"));
                    fullRecipe.setUrl(j.getString("source_url"));
                    fullRecipe.setRecipe_id(j.getString("recipe_id"));
                    fullRecipe.setImage_url(j.getString("image_url"));
                    fullRecipe.setIngredients(j.getJSONArray("ingredients"));



                    //populate layout
                    fullTitle.setText(fullRecipe.getTitle());
                    fullImage.setImageUrl(fullRecipe.getImage_url());
                    fullButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
                            intent.putExtra("webviewUrl", fullRecipe.getUrl());
                            startActivity(intent);
                        }
                    });


                    for (int i = 0; i < fullRecipe.getIngredients().length(); i++) {
                        fullIngredientsArray.add(fullRecipe.getIngredients().getString(i));
                    }

                    listAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, fullIngredientsArray) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView text = (TextView) view.findViewById(android.R.id.text1);
                            text.setTextColor(getResources().getColor(R.color.light_gray));
                            return view;
                        }
                    };
//                    listAdapter = new ArrayAdapter<String>(FullRecipeActivity.this, android.R.layout.simple_list_item_1, fullIngredientsArray);
                    fullIngredientsListView.setAdapter(listAdapter);

                } catch (Exception E) {
                }

                progress.setVisibility(View.INVISIBLE);
            }




        });


    }

}
