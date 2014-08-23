package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Models.Ingredient;


public class SearchActivity extends Activity {


    private ImageButton addIngredientButton;
    private Button searchRecipesButton;
    private EditText addIngredientEditText;
    private ListView addedIngredientsList;
    private ArrayList<Ingredient> list;
    ArrayAdapter<Ingredient> ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        addIngredientEditText = (EditText) findViewById(R.id.add_ingredient);
        addIngredientButton = (ImageButton) findViewById(R.id.add_ingredient_button);
        searchRecipesButton = (Button) findViewById(R.id.find_recipes_button);
        addedIngredientsList = (ListView) findViewById(R.id.ingredients_list);

        //on click listeners
        addIngredientButton.setOnClickListener(addIngredientsClicked);
        searchRecipesButton.setOnClickListener(searchClicked);


        list = new ArrayList<Ingredient>();
        ingredientsAdapter = new IngredientArrayAdapter(this, R.layout.ingredient_lv_row, list);
        addedIngredientsList.setAdapter(ingredientsAdapter);
    }


    private View.OnClickListener addIngredientsClicked = new View.OnClickListener() {
        public void onClick(View v) {


            String entry = addIngredientEditText.getText().toString().trim();

            if (entry != null && entry.length() > 0) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredient(entry);

                list.add(ingredient);
                ingredientsAdapter.notifyDataSetChanged();
                addIngredientEditText.setText("");
            }
        }

    };



    private View.OnClickListener searchClicked = new View.OnClickListener() {
        public void onClick(View v) {


            if (list.size() > 0) {

                int counter = 0 ;
                String result = "";

                for (Ingredient i : list) {
                    if (counter == 0) {
                        result = i.getIngredient();
                    }
                    else {
                        result = result + "," + i.getIngredient();
                    }
                    counter ++;
                }

                Log.v("string", result);

                Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
                intent.putExtra("ingredientsString", result);
                startActivity(intent);
            } else {
                Toast.makeText(SearchActivity.this, "You haven't added any ingredients", Toast.LENGTH_SHORT).show();
            }

        }
    };



}





