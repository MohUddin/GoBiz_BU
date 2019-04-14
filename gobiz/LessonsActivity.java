package com.example.vaibh.gobiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibh.gobiz.adapters.LessonsAdapter;
import com.example.vaibh.gobiz.pojos.Course;
import com.example.vaibh.gobiz.pojos.Module;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.vaibh.gobiz.CoursesActivity.COURSE_MODULE_MAP;
import static com.example.vaibh.gobiz.CoursesActivity.MODULES;

public class LessonsActivity extends AppCompatActivity {

    public ArrayList<Module> modules;
    public HashMap<Course, List<Module>> courseModuleMap;

    public static final String MODULE = "MODULE";
    public static final String LESSON_NUMBER = "LESSON_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        Intent intent = getIntent();
        final int courseNumber = intent.getIntExtra(CoursesActivity.COURSE, -1);
        modules = intent.getParcelableArrayListExtra(MODULES);
        courseModuleMap = (HashMap<Course, List<Module>>) intent.getSerializableExtra(COURSE_MODULE_MAP);

        TextView titleView = findViewById(R.id.title);
        titleView.setText(String.format(getString(R.string.lessons_title), courseNumber));

        LessonsAdapter adapter = new LessonsAdapter(this, modules);

        // mocked for testing lesson 2
        modules.add(0, new Module("Mod1", getString(R.string.lesson_2_course_name)));

        // mocked lesosns for demoing
        modules.add(new Module("Mod1", getString(R.string.lesson_3_course_name)));
        modules.add(new Module("Mod1", getString(R.string.lesson_4_course_name)));
        modules.add(new Module("Mod1", getString(R.string.lesson_5_course_name)));

        ExpandableHeightListView lessonsList = findViewById(R.id.lessons_list);
        lessonsList.setExpanded(true);
        lessonsList.setFocusable(false);
        lessonsList.setAdapter(adapter);

        lessonsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // only first 2 lessons will work for now
                if (i <= 1 || i == 4) {
                    Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(MODULE, modules.get(i));

                    // each course will have 5 lessons
                    bundle.putInt(LESSON_NUMBER, ((courseNumber-1) * 5) + (i+1));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(LessonsActivity.this, "Content Locked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
