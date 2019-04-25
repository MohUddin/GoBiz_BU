package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class ExerciseFragment1 extends AnswersFragment {

    public static final String INSTRUCTIONS = "INSTRUCTIONS";
    public static final String PROMPT_1 = "PROMPT_1";
    public static final String PROMPT_2 = "PROMPT_2";
    public static final String FOLLOWUP = "FOLLOWUP";
    public static final String TAG = "TAG";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_1, container, false);
        setRoot(view);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), getString(R.string.label_exercise));
        setupHeaders(view);
        setupNextButton(view);

        Bundle args = getArguments();
        assert args != null;

        if (args.containsKey(TAG)) {
            String tag = args.getString(TAG);
            setTag(tag);
        }

        String instructions = args.getString(INSTRUCTIONS);
        String prompt1 = args.getString(PROMPT_1);
        String prompt2 = args.getString(PROMPT_2);
        String followup = args.getString(FOLLOWUP);

        TextView instructionsView = view.findViewById(R.id.exercise_instructions);
        instructionsView.setText(instructions);

        TextView promptView1 = view.findViewById(R.id.exercise_prompt_1);
        promptView1.setText(prompt1);

        TextView promptView2 = view.findViewById(R.id.exercise_prompt_2);
        promptView2.setText(prompt2);

        TextView followupView = view.findViewById(R.id.exercise_followup);
        followupView.setText(followup);

        return view;
    }
}
