package com.example.bncc2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CounterFragment extends Fragment {

    private int count = 0;

    public CounterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_counter, container, false);

        TextView tvCount = rootView.findViewById(R.id.tvCount);
        Button btnDecrease = rootView.findViewById(R.id.btnDecrease);
        Button btnIncrease = rootView.findViewById(R.id.btnIncrease);
        Button btnReset = rootView.findViewById(R.id.btnReset);

        btnIncrease.setOnClickListener(v -> {
            count++;
            tvCount.setText(String.valueOf(count));
        });

        btnDecrease.setOnClickListener(v -> {
            count--;
            tvCount.setText(String.valueOf(count));
        });

        btnReset.setOnClickListener(v -> {
            count = 0;
            tvCount.setText(String.valueOf(count));
        });

        return rootView;
    }
}
