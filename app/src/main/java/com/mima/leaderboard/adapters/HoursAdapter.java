package com.mima.leaderboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mima.leaderboard.R;
import com.mima.leaderboard.HoursLeader;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {
    List<HoursLeader> leaders;

    public HoursAdapter(List<HoursLeader> body) {
        leaders=body;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learning_leaders,parent,false);
        return new HoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
            holder.bind(leaders.get(position));
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{
        private TextView name, description;
        private ImageView image;


        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            image= itemView.findViewById(R.id.image);
        }

        public void bind(HoursLeader leader){
            name.setText(leader.getName());
            description.setText(leader.getHours() +" learning hours, " + leader.getCountry());
            Picasso.get().load(leader.getBadgeUrl()).into(image);
        }
    }
}
