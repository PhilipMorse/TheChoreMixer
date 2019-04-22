package com.nobodysbusiness.thechoremixer;

import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nobodysbusiness.thechoremixer.chorePackage.Chore;
import com.nobodysbusiness.thechoremixer.chorePackage.ChoreDatabase;

import java.util.ArrayList;
import java.util.List;

public class GenerateChoreFragment extends Fragment {

    private Button generateChoreButton, completeChoreButton;
    private TextView taskTextView;
    private Chore currentChore;
    private Handler mainHandler = new Handler();
    private String TAG = "GENERATECHORE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generate_chore, container, false);
        generateChoreButton = view.findViewById(R.id.generateChoreButton);
        completeChoreButton = view.findViewById(R.id.completeChoreButton);
        taskTextView = view.findViewById(R.id.taskTextView);
        generateChoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Chore[] chores;
                        chores = ((MainActivity)getActivity()).generateChore();
                        if (chores.length>0){
                            currentChore=chores[0];
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    taskTextView.setText(currentChore.getName());
                                }
                            });
                        }
                        else {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    taskTextView.setText("All Chores Complete!");
                                }
                            });

                        }
                    }
                }).start();

            }
        });
        completeChoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentChore!=null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity)getActivity()).deleteCurrentChore(currentChore);
                            currentChore = new Chore();
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    taskTextView.setText("Generate a New Chore");
                                }
                            });
                        }
                    }).start();

                }

            }
        });
        return view;
    }
}
