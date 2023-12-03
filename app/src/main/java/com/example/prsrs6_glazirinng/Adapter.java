package com.example.prsrs6_glazirinng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Weather> weather;
    private final Context context;
    public Adapter(Context context, List<Weather>weather)
    {
        this.inflater = LayoutInflater.from(context);
        this.weather = weather;
        this.context = context;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull
                                                        ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Weather w = weather.get(position);
        Picasso.get().load(w.GetUrl()).fit().centerInside().into(holder.flagView);
        holder.nameView.setText(w.GetDate());
        holder.capitalView.setText(w.GetTemp());
    }

    @Override
    public int getItemCount() {
        return weather.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.imageView);
            nameView = view.findViewById(R.id.textView4);
            capitalView = view.findViewById(R.id.textView5);
        }
    }
}
