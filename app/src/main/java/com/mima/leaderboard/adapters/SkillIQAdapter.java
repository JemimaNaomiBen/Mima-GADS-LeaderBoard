package com.mima.leaderboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mima.leaderboard.R;
import com.mima.leaderboard.SkillIQLeader;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.SkillIQHolder> {

    List<SkillIQLeader> leaders ;

    public SkillIQAdapter(List<SkillIQLeader> in) {
        leaders = in;
    }

    @NonNull
    @Override
    public SkillIQHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill_iq_leaders,parent,false);

        return new SkillIQHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQHolder holder, int position) {
        holder.bind(leaders.get(position));
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class SkillIQHolder  extends  RecyclerView.ViewHolder{
        private TextView name, description;
        private ImageView image;

        public SkillIQHolder(@NonNull View itemView) {
            super(itemView);
           name=  itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);


        }

        public void bind(SkillIQLeader leader){
            name.setText(leader.getName());
            description.setText(leader.getScore() +" Skill IQ score, " + leader.getCountry());
            Picasso.get().load(leader.getBadgeUrl()).into(image);

        }
    }
}