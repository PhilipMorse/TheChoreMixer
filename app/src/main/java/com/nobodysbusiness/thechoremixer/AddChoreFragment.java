package com.nobodysbusiness.thechoremixer;

import android.arch.persistence.room.Room;
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
import android.widget.Toast;

import com.nobodysbusiness.thechoremixer.chorePackage.Chore;
import com.nobodysbusiness.thechoremixer.chorePackage.ChoreDAO;
import com.nobodysbusiness.thechoremixer.chorePackage.ChoreDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddChoreFragment extends Fragment {

    private Button submitButton;
    private EditText choreEditText;
    private SeekBar durationSeekBar;
    private TextView durationTextView;
    private ChoreDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_chore, container, false);
        submitButton = view.findViewById(R.id.submitButton);
        durationSeekBar = view.findViewById(R.id.durationSeekBar);
        durationTextView = view.findViewById(R.id.durationTextView);
        choreEditText = view.findViewById(R.id.choreEditText);
        db = Room.databaseBuilder(getActivity(), ChoreDatabase.class,"chore-db").fallbackToDestructiveMigration().build();
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
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choreEditText.getText().toString().trim().length() == 0){
                    Toast.makeText(getActivity(), "Please Enter a Chore!", Toast.LENGTH_SHORT).show();
                }
                else{
                    final Chore chore = new Chore();
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    chore.setName(choreEditText.getText().toString());
                    chore.setDuration(durationTextView.getText().toString());
                    chore.setDate(df.format(date));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.getChoreDAO().insertSingle(chore);
                        }
                    }).start();
                    choreEditText.getText().clear();

                }
            }
        });
        return view;
    }
}
