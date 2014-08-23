package Models;

/**
 * Created by tylerroach on 8/23/14.
 */
public class ShortRecipe {


    private String title;
    private String url;
    private String image_url;
    private String recipe_id;

    // Getters
    public String getTitle() {
        return this.title;
    }
    public String getUrl() {
        return this.title;
    }
    public String getImage_url() {return this.image_url;}
    public String getRecipe_id() {return this.recipe_id;}


    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setImage_url(String image_url) {this.image_url = image_url;}
    public void setRecipe_id(String recipe_id) {this.recipe_id = recipe_id;}



}
