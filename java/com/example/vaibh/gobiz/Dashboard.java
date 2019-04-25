package com.example.vaibh.gobiz;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibh.gobiz.pojos.Course;
import com.example.vaibh.gobiz.pojos.Module;
import com.example.vaibh.gobiz.pojos.ModuleMapLock;
import com.example.vaibh.gobiz.pojos.ModuleScore;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    public HashMap<Course, List<Module>> courseModuleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ModuleMapLock.getFromDatabase(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ModuleScore.getFromDatabase(FirebaseAuth.getInstance().getCurrentUser().getUid());
        setupDashboardItems();
    }

    private void setupDashboardItems() {
        View coursesContainer = findViewById(R.id.courses);
        View badgesContainer = findViewById(R.id.badges);
        View networkContainer = findViewById(R.id.network);
        View profileContainer = findViewById(R.id.profile);
        View settingsContainer = findViewById(R.id.settings);
        View aboutContainer = findViewById(R.id.about);

        List<View> containers = Arrays.asList(coursesContainer, badgesContainer, networkContainer, profileContainer, settingsContainer, aboutContainer);
        List<Integer> drawablesIds = Arrays.asList(R.drawable.courses, R.drawable.badges, R.drawable.network, R.drawable.profile, R.drawable.settings, R.drawable.about);
        List<String> labels = Arrays.asList(getString(R.string.label_courses), getString(R.string.label_badges), getString(R.string.label_network),getString(R.string.label_profile), getString(R.string.label_settings), getString(R.string.label_about));

        for (int i = 0; i < containers.size(); i++) {
            View container = containers.get(i);
            Drawable drawable = getDrawable(drawablesIds.get(i));
            String label = labels.get(i);

            ((ImageView) container.findViewById(R.id.item_image)).setImageDrawable(drawable);
            ((TextView) container.findViewById(R.id.item_label)).setText(label);
        }

        coursesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCourses(view);
            }
        });

        badgesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBadges(view);
            }
        });

        networkContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNetwork(view);
            }
        });

        profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProfile(view);
            }
        });

        settingsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSettings(view);
            }
        });

        aboutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAbout(view);
            }
        });
    }

    public void gotoCourses(View view) {
        Intent i = new Intent(getApplicationContext(), CoursesActivity.class);
        startActivity(i);
    }

    public void gotoBadges(View view) {
        Intent i = new Intent(getApplicationContext(), Badges.class);
        startActivity(i);
    }

    public void gotoNetwork(View view) {
        Intent i = new Intent(getApplicationContext(), Network.class);
        startActivity(i);
    }

    public void gotoProfile(View view) {
        Intent i = new Intent(getApplicationContext(), Profile.class);
        startActivity(i);
    }

    public void gotoSettings(View view) {
        Intent i = new Intent(getApplicationContext(), Settings.class);
        startActivity(i);
    }

    public void gotoAbout(View view) {
        Intent i = new Intent(getApplicationContext(), About.class);
        startActivity(i);
    }
}
