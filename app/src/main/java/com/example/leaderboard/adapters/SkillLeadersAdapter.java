package com.example.leaderboard.adapters;



import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.data.models.SkillLeaders;
import com.example.leaderboard.databinding.ItemSkillLeadersLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillLeadersAdapter extends RecyclerView.Adapter<SkillLeadersAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private Context mCtx;
    private List<SkillLeaders> skills_list;

    public SkillLeadersAdapter(Context mCtx, List<SkillLeaders> skills_list, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.skills_list = skills_list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        ItemSkillLeadersLayoutBinding binding = ItemSkillLeadersLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillLeaders skillLeaders = skills_list.get(position);
        holder.bind(skillLeaders);
    }

    @Override
    public int getItemCount() {
        return skills_list.size();
    }

    public interface OnItemClickListener {
        void onClick(SkillLeaders leader);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSkillLeadersLayoutBinding binding;

        public ViewHolder(ItemSkillLeadersLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> {
                listener.onClick(skills_list.get(getLayoutPosition()));
            });
        }

        public void bind(SkillLeaders list) {
            binding.mtvName.setText(list.getName());
            binding.mtvScoreCountry.setText(list.getScore() + " skill IQ Score. " + list.getCountry());
            Picasso.get()
                    .load(Uri.parse(list.getBadgeUrl()))
                    .placeholder(R.drawable.ic_loader)
                    .into(binding.imgViewBadge);

            binding.executePendingBindings();
        }
    }
}
