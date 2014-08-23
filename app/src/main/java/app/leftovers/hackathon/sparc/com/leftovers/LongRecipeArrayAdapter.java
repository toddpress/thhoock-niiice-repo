package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import Models.LongRecipe;

public class LongRecipeArrayAdapter extends ArrayAdapter<LongRecipe> {
    Context context;
    int layoutResourceId;
    ArrayList<LongRecipe> data = new ArrayList<LongRecipe>();

    public LongRecipeArrayAdapter(Context context, int layoutResourceId, ArrayList<LongRecipe> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LongRecipeHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new LongRecipeHolder();
            holder.title = (TextView) row.findViewById(R.id.searchRecipeTitle);
            holder.image = (SmartImageView) row.findViewById(R.id.searchRecipeImage);
            row.setTag(holder);
        } else {
            holder = (LongRecipeHolder) row.getTag();
        }
        LongRecipe recipe = data.get(position);
        holder.title.setText(recipe.getTitle());
        holder.image.setImageUrl(recipe.getImage_url());

        return row;

    }

    static class LongRecipeHolder {
        TextView title;
        SmartImageView image;
    }
}