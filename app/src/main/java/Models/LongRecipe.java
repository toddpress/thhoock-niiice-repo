package Models;

import java.util.ArrayList;

/**
 * Created by michaelshavens on 8/23/14.
 */
public class LongRecipe
{

    private String title;
    private String url;
    private String image_url;
    private String recipe_id;
    private ArrayList<String> ingredients;

    // Getters
    public String getTitle() {
        return this.title;
    }
    public String getUrl() {
        return this.title;
    }
    public String getImage_url() {return this.image_url;}
    public String getRecipe_id() {return this.recipe_id;}
    public ArrayList<String> getIngredients() {return this.ingredients;}

    // Setters
    public void setTitle(String ingredient) {
        this.url = url;
    }
    public void setUrl(String ingredient) {
        this.url = url;
    }
    public void setImage_url(String image_url) {this.image_url = image_url;}
    public void setRecipe_id(String recipe_id) {this.recipe_id = recipe_id;}
    public void setIngredients(ArrayList<String> ingredients) {this.ingredients = ingredients;}

}
