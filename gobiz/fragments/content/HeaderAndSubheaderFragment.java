package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class HeaderAndSubheaderFragment extends NextButtonFragment {

    public static final String HEADER = "HEADER";
    public static final String SUBHEADER = "SUBHEADER";
    protected int lessonNumber;

    public void setHeaderStrings(String header, String subheader) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(HEADER, header);
        args.putString(SUBHEADER, subheader);
        this.setArguments(args);
    }

    protected void setupHeaders(View view) {
        TextView header = view.findViewById(R.id.lesson_title);
        TextView subheader = view.findViewById(R.id.subheader);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        String headerText = bundle.getString(HEADER);
        String subheaderText = bundle.getString(SUBHEADER);

        header.setText(headerText);
        subheader.setText(subheaderText);
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
