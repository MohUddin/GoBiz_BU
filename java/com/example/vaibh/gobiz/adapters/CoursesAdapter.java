package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.pojos.Course;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;

public class CoursesAdapter extends ArrayAdapter<Course> {

    public CoursesAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        assert course != null;

        // check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_course_or_lesson_item, parent, false);
        }

        // lookup view for data population
        DonutProgress donutProgress = convertView.findViewById(R.id.donutProgress);
        TextView nameView = convertView.findViewById(R.id.itemName);
        TextView descriptionView = convertView.findViewById(R.id.itemDescription);

        nameView.setText(Html.fromHtml(String.format("<b>Course %d:</b> %s", position + 1, course.getCourseName())));
        descriptionView.setText(course.getCourseDescription());
        Log.d("Adapter title", course.getCourseName());

        // for now, only first course is unlocked
        boolean isUnlocked = position == 0;
        if (!isUnlocked) {
            donutProgress.setProgress(0f);
            donutProgress.setText("");
            convertView.findViewById(R.id.padlock).setVisibility(View.VISIBLE);
        } else {
            convertView.findViewById(R.id.padlock).setVisibility(View.INVISIBLE);

            // todo: persist and track content progress rather than setting a constant value every time
            donutProgress.setProgress(75f);
            donutProgress.setText(Integer.toString(75) + "%");
        }

        return convertView;
    }
}
