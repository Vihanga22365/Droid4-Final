package com.example.electromartmad.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.electromartmad.R;
import com.example.electromartmad.cardpayment;
import com.example.electromartmad.cashpayment;

public class PaymentFragment extends Fragment {

    private PaymentViewModel paymentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        final TextView textView = root.findViewById(R.id.text_tools);
        Button cardPayBtn = root.findViewById(R.id.cardpaybtn);
        Button cashPayBtn = root.findViewById(R.id.cashpaybtn);

        paymentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        cardPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), cardpayment.class);
                startActivity(i);
            }
        });

        cashPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), cashpayment.class);
                startActivity(i);
            }
        });
        return root;
    }
}