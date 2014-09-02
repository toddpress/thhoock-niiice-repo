package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Models.Ingredient;


public class SearchActivity extends Activity {
    private final String TAG = "SearchActivity";
    private FrameLayout mainLayout;
    private FrameLayout helpLayout;
    private ImageButton addIngredientButton;
    private ImageButton clearIngredientsButton;
    private ImageButton getInfoButton;
    private Button searchRecipesButton;
    private EditText addIngredientEditText;
    private ListView addedIngredientsList;
    private ArrayList<Ingredient> list;
    ArrayAdapter<Ingredient> ingredientsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SpannableString s = new SpannableString("Foodini");
        s.setSpan(new TypefaceSpan(this, "Elephont-Light.otf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        setTitle(s);

        helpLayout = (FrameLayout)findViewById(R.id.help_layout_root);
        mainLayout = (FrameLayout)findViewById(R.id.search_layout_main);
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



    public void closeHelpClicked (View view) {
        View layout = findViewById(R.id.help_layout_root);
        ViewGroup parent = (ViewGroup) layout.getParent();
        parent.removeView(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.search_action_clear:

                if (list.size() > 0) {
                    list.removeAll(list);
                    ingredientsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SearchActivity.this, "You haven't added any ingredients", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.search_action_about:

                if (helpLayout == null){
                    View help = getLayoutInflater().inflate(R.layout.help_layout, mainLayout, false);
                    mainLayout.addView(help);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}





