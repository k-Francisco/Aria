package com.example.ksfgh.aria.View.fragments;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ksfgh.aria.Adapters.HomeGenreCatalogsAdapter;
import com.example.ksfgh.aria.Adapters.HomePlaylistAdapter;
import com.example.ksfgh.aria.Adapters.HomeWeekTopBandAdapter;
import com.example.ksfgh.aria.Model.PlaylistModel;
import com.example.ksfgh.aria.R;
import com.example.ksfgh.aria.Rest.RetrofitClient;
import com.example.ksfgh.aria.View.activities.HomeScreen;
import com.example.ksfgh.aria.ViewModel.HomeViewModel;
import com.example.ksfgh.aria.databinding.FragmentHomeBinding;

import org.simple.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private FragmentHomeBinding fragmentHomeBinding;
    private HomePlaylistAdapter adapter;
    private HomeWeekTopBandAdapter adapter2;
    private HomeGenreCatalogsAdapter adapter3;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        homeViewModel = new HomeViewModel((HomeScreen) this.getActivity());
        adapter = new HomePlaylistAdapter(homeViewModel);
        adapter2 = new HomeWeekTopBandAdapter(homeViewModel);
        adapter3 = new HomeGenreCatalogsAdapter(homeViewModel);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container, false);

            fragmentHomeBinding.recyclerView.setLayoutManager(
                    new LinearLayoutManager(fragmentHomeBinding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false)
            );

            fragmentHomeBinding.recyclerView2.setLayoutManager(
                    new LinearLayoutManager(fragmentHomeBinding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false)
            );

            fragmentHomeBinding.recyclerView3.setLayoutManager(
                    new GridLayoutManager(fragmentHomeBinding.getRoot().getContext(), 2, GridLayoutManager.VERTICAL, false){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    }
            );

            fragmentHomeBinding.recyclerView.setAdapter(adapter);
            fragmentHomeBinding.recyclerView2.setAdapter(adapter2);
            fragmentHomeBinding.recyclerView3.setAdapter(adapter3);

        return  fragmentHomeBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(homeViewModel);
    }
}
