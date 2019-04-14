package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibh.gobiz.LessonActivity;
import com.example.vaibh.gobiz.R;

import java.util.Objects;

public class LessonSplashIntroFragment extends NextButtonFragment {

    private static final String NUMBER_STRING = "NUMBER_STRING";

    public static final String LESSON_NUM_STRING = "LESSON_NUM_STRING";
    public static final String LESSON_TITLE = "LESSON_TITLE";
    public static final String IMAGE_RES_ID = "IMAGE_RES_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        assert args != null;

        String lessonNumString = args.getString(LESSON_NUM_STRING);
        String lessonTitle = args.getString(LESSON_TITLE);
        int imageResId = args.getInt(IMAGE_RES_ID);
        int lessonNumber = ((LessonActivity) Objects.requireNonNull(getActivity())).getLessonNumber();

        int layoutId;
        if (lessonNumber == 1) {
            layoutId = R.layout.fragment_lesson_intro_1;
        } else if  (lessonNumber == 2) {
            layoutId = R.layout.fragment_lesson_intro_2;
        } else {
            // placeholder for later lessons
            layoutId = R.layout.fragment_lesson_intro_1;
        }

        View view = inflater.inflate(layoutId, container, false);

        ((TextView) view.findViewById(R.id.lesson_number_header)).setText(lessonNumString);
        ((TextView) view.findViewById(R.id.lesson_title)).setText(lessonTitle);
        if (lessonNumber == 1) {
            ((TextView) view.findViewById(R.id.lesson_subtitle)).setText(getString(R.string.lesson_2_intro_subtitle));
        }

        ((ImageView) view.findViewById(R.id.lesson_welcome_image)).setImageDrawable(getResources().getDrawable(imageResId, null));

        setupNextButton(view);
        return view;
    }
}
