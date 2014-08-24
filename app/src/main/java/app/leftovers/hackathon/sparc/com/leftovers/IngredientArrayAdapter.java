package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Ingredient;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient> {
    Context context;
    int layoutResourceId;
    ArrayList<Ingredient> data = new ArrayList<Ingredient>();

    public IngredientArrayAdapter(Context context, int layoutResourceId, ArrayList<Ingredient> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        IngredientHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new IngredientHolder();
            holder.ingredient = (TextView) row.findViewById(R.id.ingredient_text);
            holder.btnDelete = (ImageButton) row.findViewById(R.id.remove_button);
            row.setTag(holder);
        } else {
            holder = (IngredientHolder) row.getTag();
        }
        Ingredient ingredient = data.get(position);
        holder.ingredient.setText(ingredient.getIngredient());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        return row;

    }

    static class IngredientHolder {
        TextView ingredient;
        ImageButton btnDelete;

    }
}