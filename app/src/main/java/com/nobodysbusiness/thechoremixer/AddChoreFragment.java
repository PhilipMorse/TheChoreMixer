package com.nobodysbusiness.thechoremixer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddChoreFragment extends Fragment {

    private Button submitButton;
    private EditText choreEditText;
    private SeekBar durationSeekBar;
    private TextView durationTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_chore, container, false);
        submitButton = view.findViewById(R.id.submitButton);
        durationSeekBar = view.findViewById(R.id.durationSeekBar);
        durationTextView = view.findViewById(R.id.durationTextView);
        choreEditText = view.findViewById(R.id.choreEditText);
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress==0){
                    durationTextView.setText("Short");
                }else if (progress==1){
                    durationTextView.setText("Medium");
                }else if (progress==2){
                    durationTextView.setText("Long");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }
}
