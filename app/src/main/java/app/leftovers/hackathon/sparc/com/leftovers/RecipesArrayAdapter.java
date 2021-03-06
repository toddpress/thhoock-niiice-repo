package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import Models.ShortRecipe;

public class RecipesArrayAdapter extends ArrayAdapter<ShortRecipe> {
    Context context;
    int layoutResourceId;
    ArrayList<ShortRecipe> data = new ArrayList<ShortRecipe>();

    public RecipesArrayAdapter(Context context, int layoutResourceId, ArrayList<ShortRecipe> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecipesHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecipesHolder();
            holder.title = (TextView) row.findViewById(R.id.searchRecipeTitle);
            holder.image = (SmartImageView) row.findViewById(R.id.searchRecipeImage);
            row.setTag(holder);
        } else {
            holder = (RecipesHolder) row.getTag();
        }

        final RecipesHolder finalHolder = holder;
        ShortRecipe recipe = data.get(position);
        finalHolder.title.setText(recipe.getTitle());
        finalHolder.image.setImageUrl(recipe.getImage_url());
        finalHolder.recipeId = recipe.getRecipe_id();
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullRecipeActivity.class);
                intent.putExtra("fullRecipeId", finalHolder.recipeId);
                context.startActivity(intent);
            }
        });

        return row;

    }

    static class RecipesHolder {
        TextView title;
        SmartImageView image;
        String recipeId;
    }
}