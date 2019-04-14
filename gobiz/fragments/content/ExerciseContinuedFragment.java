package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibh.gobiz.R;

public class ExerciseContinuedFragment extends ExerciseFragment2 {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_continued, container, false);

        setHeaderStrings(getString(R.string.label_lesson) + " " + String.valueOf(getLessonNumber()), getString(R.string.label_exercise));
        setupHeaders(view);

        setupNextButton(view);
        setupExerciseText(view);

        return view;
    }

}
