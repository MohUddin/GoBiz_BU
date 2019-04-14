package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class TheoryModelFragment extends HeaderAndSubheaderFragment {

    public static final String THEORY_MODEL_TEXT = "THEORY_MODEL_TEXT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theory_model, container, false);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), getString(R.string.label_theory_model));
        setupHeaders(view);

        setupNextButton(view);
        setupTheoryModelText(view);

        return view;
    }

    public void setTheoryModelText(String text) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(THEORY_MODEL_TEXT, text);
        this.setArguments(args);
    }

    public void setupTheoryModelText(View view) {
        TextView theoryModelView =  view.findViewById(R.id.theory_model_text);
        Bundle bundle = this.getArguments();
        assert bundle != null;

        theoryModelView.setText(bundle.getString(THEORY_MODEL_TEXT));
    }
}
