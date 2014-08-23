package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class SearchActivity extends Activity {


    private Button addIngredientButton;
    private Button searchRecipesButton;
    private TextView addIngredientTextView;
    private ListView addedIngredientsList;
    private ArrayList<String> list;
    ArrayAdapter<String> ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addIngredientTextView = (TextView) findViewById(R.id.add_ingredient);
        addIngredientButton = (Button) findViewById(R.id.add_ingredient_button);
        searchRecipesButton = (Button) findViewById(R.id.find_recipes_button);
        addedIngredientsList = (ListView) findViewById(R.id.ingredients_list);

        //on click listeners
        addIngredientButton.setOnClickListener(addIngredientsClicked);
        searchRecipesButton.setOnClickListener(searchClicked);


        list = new ArrayList<String>();
        ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        addedIngredientsList.setAdapter(ingredientsAdapter);
    }


    private View.OnClickListener addIngredientsClicked = new View.OnClickListener() {
        public void onClick(View v) {

            String ingredient = addIngredientTextView.getText().toString();

            if (ingredient.length() > 0) {
                list.add(addIngredientTextView.getText().toString());
                ingredientsAdapter.notifyDataSetChanged();
                addIngredientTextView.setText("");
            }

        }

    };

    private View.OnClickListener searchClicked = new View.OnClickListener() {
        public void onClick(View v) {


            if (list.size() > 0) {

                String ingredientsString = list.toString();
                ingredientsString = ingredientsString.substring(1, ingredientsString.length() - 1);
                Log.v("string", ingredientsString);

                Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
                intent.putExtra("ingredientsString", ingredientsString);
                startActivity(intent);
            } else {
                Toast.makeText(SearchActivity.this, "You haven't added any ingredients", Toast.LENGTH_SHORT).show();
            }

        }
    };



}





