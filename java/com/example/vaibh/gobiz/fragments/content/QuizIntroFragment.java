package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibh.gobiz.R;

public class QuizIntroFragment extends HeaderAndSubheaderFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_intro, container, false);
        setHeaderStrings(getString(R.string.label_lesson) + " " + getLessonNumber(), getString(R.string.label_quiz));
        setupHeaders(view);
        setupNextButton(view);

        return view;
    }
}
