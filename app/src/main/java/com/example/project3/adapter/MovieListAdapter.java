package com.example.project3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.R;
import com.example.project3.model.Movie;
import com.example.project3.model.MovieData;
import com.example.project3.network.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MoviewViewHolder> {
    private Context context;
    private List<MovieData> movieDatas;


    public MovieListAdapter(Context context) {
        this.context = context;
        movieDatas = new ArrayList<>();
    }
    private void add(MovieData item){
        movieDatas.add(item);
        notifyItemInserted(movieDatas.size()-1);
    }
    public void addAll(List<MovieData> movieDatas){
        for (MovieData movieData:movieDatas){
            add(movieData);
        }
    }
    public void remove (MovieData item){
        int position = movieDatas.indexOf(item);
        if (position>-1){
            movieDatas.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    private MovieData getItem(int i) {
        return movieDatas.get(i);
    }


    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        final MoviewViewHolder moviewViewHolder = new MoviewViewHolder(view);
        return moviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        final MovieData movieData = movieDatas.get(position);
        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return movieDatas.size();
    }

    public class MoviewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_data);
        }

        public void bind(MovieData movieData) {
            Picasso.get()
                    .load(Constant.IMG_URL+movieData.getPosterPath())
                    .into(img);
        }
    }
}
