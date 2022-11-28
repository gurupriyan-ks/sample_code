package com.sample.emmarsample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sample.emmarsample.databinding.UserItemBinding;
import com.sample.emmarsample.models.servicemodels.Results;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    UserClickListener listener;
    List<Results> items = new ArrayList<>();

    public UserListAdapter(UserClickListener clickListener) {
        this.listener = clickListener;
    }

    public UserListAdapter(List<Results> items, UserClickListener clickListener) {
        this.items = items;
        this.listener = clickListener;
    }

    public void addItems(List<Results> items) {
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.items.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.UserViewHolder holder, int position) {
        holder.bind(items.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            this.listener.onUserClicked(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public UserItemBinding binding;

        public UserViewHolder(@NonNull UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Results results) {
            setImage(results);
            binding.tvName.setText(results.getName().toString());
            binding.tvEmail.setText(results.getEmail());
            binding.tvDate.setText(results.getRegistered().toString());
            binding.tvCountry.setText(String.format("Country|%s", results.getLocation().getCountry()));
        }

        private void setImage(Results results) {
            Glide.with(binding.getRoot().getContext())
                    .load(results.component11().getThumbnail())
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.ivUser);
        }
    }

    interface UserClickListener {
        void onUserClicked(Results user);
    }
}
