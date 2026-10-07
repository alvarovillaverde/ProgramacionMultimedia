package com.example.b005navigationdrawer.ui.accessible;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b005navigationdrawer.databinding.FragmentAccesibleBinding;

public class AccesibleFragment extends Fragment {
    private FragmentAccesibleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccesibleViewModel accesibleViewModel =
                new ViewModelProvider(this).get(AccesibleViewModel.class);

        binding = FragmentAccesibleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccesible;
        accesibleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
