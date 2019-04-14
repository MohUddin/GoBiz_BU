package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class ReviewFragment1 extends AnswersFragment {

    private static final String CAPTION = "CAPTION";
    private static final String QUESTION_1 = "QUESTION_1";
    private static final String QUESTION_2 = "QUESTION_2";
    private static final String END_NOTE = "END_NOTE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_1, container, false);
        setRoot(view);

        setHeaderStrings(getString(R.string.label_lesson) + " " + String.valueOf(getLessonNumber()), getString(R.string.label_review));
        setupHeaders(view);
        setupCaption(view);
        setupQuestions(view);
        setupNextButton(view);
        setupEndNote(view);

        return view;
    }

    public void setCaptionString(String string) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(CAPTION, string);
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

    public void setEndNoteString(String str) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(END_NOTE, str);
        this.setArguments(args);
    }

    private void setupCaption(View view) {
        TextView caption = view.findViewById(R.id.caption);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        caption.setText(bundle.getString(CAPTION));
    }

    private void setupQuestions(View view) {
        TextView question1 = view.findViewById(R.id.question_1);
        TextView question2 = view.findViewById(R.id.question_2);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        question1.setText(bundle.getString(QUESTION_1));
        question2.setText(bundle.getString(QUESTION_2));
    }

    private void setupEndNote(View view) {
        TextView endNoteView = view.findViewById(R.id.end_note);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        endNoteView.setText(bundle.getString(END_NOTE));
    }
}
