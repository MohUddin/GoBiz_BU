package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class ExerciseBringingItBackFragment extends AnswersFragment {

    private static final String INSTRUCTIONS = "INSTRUCTIONS";
    private static final String PROMPT = "PROMPT";
    private static final String QUESTION_1 = "QUESTION_1";
    private static final String QUESTION_2 = "QUESTION_2";
    public static final String ANSWER_1 = "ANSWER_1";
    public static final String ANSWER_2 = "ANSWER_2";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_bringing_it_back, container, false);
        setRoot(view);
        setHeaderStrings(getString(R.string.label_lesson) + " " + String.valueOf(getLessonNumber()), getString(R.string.label_exercise));
        setupHeaders(view);
        setupInstructions(view);
        setupPrompt(view);
        setupQuestions(view);
        setupNextButton(view);

        return view;
    }

    public void setInstructionsString(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(INSTRUCTIONS, str);
        this.setArguments(args);
    }

    public void setPromptString(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(PROMPT, str);
        this.setArguments(args);
    }

    public void setQuestion1String(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(QUESTION_1, str);
        this.setArguments(args);
    }

    public void setQuestion2String(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(QUESTION_2, str);
        this.setArguments(args);
    }


    private void setupInstructions(View view) {
        TextView instructionsView = view.findViewById(R.id.instructions);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        instructionsView.setText(bundle.getString(INSTRUCTIONS));
    }

    private void setupPrompt(View view) {
        TextView promptView = view.findViewById(R.id.prompt);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        promptView.setText(bundle.getString(PROMPT));
    }

    private void setupQuestions(View view) {
        TextView question1 = view.findViewById(R.id.question_1);
        TextView question2 = view.findViewById(R.id.question_2);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        question1.setText(bundle.getString(QUESTION_1));
        question2.setText(bundle.getString(QUESTION_2));
    }
}
