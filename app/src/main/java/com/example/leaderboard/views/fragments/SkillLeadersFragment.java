package com.example.leaderboard.views.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.leaderboard.R;
import com.example.leaderboard.adapters.SkillLeadersAdapter;
import com.example.leaderboard.data.models.SkillLeaders;
import com.example.leaderboard.data.retrofit.RetrofitApiCalls;
import com.example.leaderboard.data.retrofit.RetrofitClient;
import com.example.leaderboard.databinding.FragmentSkillLeadersBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillLeadersFragment extends Fragment {
    SkillLeadersAdapter adapter;
    RetrofitApiCalls service;
    private FragmentSkillLeadersBinding binding;

    public SkillLeadersFragment() {
        // Empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_skill_leaders, container, false);
        service = RetrofitClient.getRetrofitInstance().create(RetrofitApiCalls.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerLayout.startShimmer();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.shimmerLayout.stopShimmer();
    }

    public void initAdapter() {
        Call<List<SkillLeaders>> call = service.getSkillLeaders();
        call.enqueue(new Callback<List<SkillLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillLeaders>> call, Response<List<SkillLeaders>> response) {
                if (response.isSuccessful()) {
                    List<SkillLeaders> res = response.body();

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.rcvSkillLeaders.setVisibility(View.VISIBLE);

                    adapter = new SkillLeadersAdapter(getActivity(), res, leader -> {
                        //Do Nothing.
                    });
                    binding.rcvSkillLeaders.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    binding.rcvSkillLeaders.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SkillLeaders>> call, Throwable t) {
                binding.shimmerLayout.stopShimmer();
            }
        });
    }
}
