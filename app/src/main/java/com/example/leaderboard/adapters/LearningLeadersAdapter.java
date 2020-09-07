package com.example.leaderboard.adapters;



import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.data.models.LearningLeaders;
import com.example.leaderboard.databinding.ItemLearningLeadersLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private Context mCtx;
    private List<LearningLeaders> leaders_list;

    public LearningLeadersAdapter(Context mCtx, List<LearningLeaders> leaders_list, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.leaders_list = leaders_list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        ItemLearningLeadersLayoutBinding binding = ItemLearningLeadersLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningLeaders learningLeaders = leaders_list.get(position);
        holder.bind(learningLeaders);
    }

    @Override
    public int getItemCount() {
        return leaders_list.size();
    }

    public interface OnItemClickListener {
        void onClick(LearningLeaders leader);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemLearningLeadersLayoutBinding binding;

        public ViewHolder(ItemLearningLeadersLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> {
                listener.onClick(leaders_list.get(getLayoutPosition()));
            });

        }

        public void bind(LearningLeaders list) {
            binding.mtvName.setText(list.getName());
            binding.mtvScoreCountry.setText(list.getHours() + " learning hours, " + list.getCountry());
            Picasso.get()
                    .load(Uri.parse(list.getBadgeUrl()))
                    .placeholder(R.drawable.ic_loader)
                    .into(binding.imgViewBadge);

            binding.executePendingBindings();
        }
    }
}

