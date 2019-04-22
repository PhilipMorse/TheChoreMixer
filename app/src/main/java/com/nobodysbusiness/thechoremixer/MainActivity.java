package com.nobodysbusiness.thechoremixer;

import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nobodysbusiness.thechoremixer.chorePackage.Chore;
import com.nobodysbusiness.thechoremixer.chorePackage.ChoreDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ChoreDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);
        db = Room.databaseBuilder(this, ChoreDatabase.class,"chore-db").fallbackToDestructiveMigration().build();
    }

    private void setupViewPager(ViewPager viewPager){
        UserMainTabAdapter adapter = new UserMainTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new GenerateChoreFragment(), "Generate");
        adapter.addFragment(new AddChoreFragment(), "Add");
        viewPager.setAdapter(adapter);

    }

    public void addChore(final Chore chore){

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.getChoreDAO().insertSingle(chore);
            }
        }).start();
    }

    public Chore[] generateChore(){
        return db.getChoreDAO().getRandomChore();
    }

    public void deleteCurrentChore(Chore chore){
        db.getChoreDAO().delete(chore);
    }
}
