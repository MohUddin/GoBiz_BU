package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class ExerciseFragment2 extends HeaderAndSubheaderFragment {

    private static final String INSTRUCTIONS = "INSTRUCTIONS";
    private static final String EXERCISE = "EXERCISE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_2, container, false);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), getString(R.string.label_exercise));
        setupHeaders(view);

        setupNextButton(view);
        setupInstructionsText(view);
        setupExerciseText(view);

        return view;
    }

    private void setupInstructionsText(View view) {
        TextView instructionsView =  view.findViewById(R.id.instructions);
        Bundle bundle = this.getArguments();
        assert bundle != null;

        instructionsView.setText(bundle.getString(INSTRUCTIONS));
    }

    protected void setupExerciseText(View view) {
        TextView exerciseView =  view.findViewById(R.id.exercise_text);
        Bundle bundle = this.getArguments();
        assert bundle != null;

        exerciseView.setText(bundle.getString(EXERCISE));
    }

    public void setInstructionsString(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(INSTRUCTIONS, str);
        this.setArguments(args);
    }

    public void setExerciseString(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(EXERCISE, str);
        this.setArguments(args);
    }

}
