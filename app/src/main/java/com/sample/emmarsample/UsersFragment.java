package com.sample.emmarsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.sample.emmarsample.databinding.FragmentUsersBinding;
import com.sample.emmarsample.models.servicemodels.Results;


public class UsersFragment extends Fragment implements UserListAdapter.UserClickListener {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_ERROR = 500;

    private FragmentUsersBinding binding;
    UserListAdapter adapter;
    private UserViewModel viewModel;

    public UsersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new UserListAdapter(this);
        binding.rvUsers.setAdapter(adapter);
        viewModel.getUsers().observe(getViewLifecycleOwner(), userScreenModel -> {
            requireActivity().runOnUiThread(() -> {
                switch (userScreenModel.getStatus()) {
                    case STATUS_SUCCESS: {
                        adapter.addItems(userScreenModel.getUsers());
                    }
                    case STATUS_ERROR: {

                    }
                }
            });

        });
        viewModel.loadUsers(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setScreenTitle(getString(R.string.user_list));
    }

    @Override
    public void onUserClicked(Results user) {
        viewModel.selectedUser = user;
        NavHostFragment.findNavController(this).navigate(R.id.action_UsersFragment_to_SecondFragment);
    }
}