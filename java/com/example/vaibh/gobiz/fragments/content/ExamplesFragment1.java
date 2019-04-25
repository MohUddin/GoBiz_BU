package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.adapters.StringExamplesAdapter;
import com.example.vaibh.gobiz.customviews.CustomViewPager;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

public class ExamplesFragment1 extends ExamplesFragment2 {

    private static final String STRING_EXAMPLES = "STRING_EXAMPLES";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examples_1, container, false);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), "Examples");
        setupHeaders(view);

        setupNextButton(view);
        setupCaption(view);
        setupExamplesList(view, (CustomViewPager) container);

        return view;
    }

    public void setStringExamples(ArrayList<String> examples) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putStringArrayList(STRING_EXAMPLES, examples);
        this.setArguments(args);
    }

    @Override
    protected void setupExamplesList(View view, CustomViewPager pager) {
        Bundle bundle = getArguments();
        assert bundle != null;

        ArrayList<String> examples = bundle.getStringArrayList(STRING_EXAMPLES);
        assert examples != null;

        ArrayList<String> leftExamples = new ArrayList<>(examples.subList(0, examples.size()/2));
        ArrayList<String> rightExamples = new ArrayList<>(examples.subList(examples.size()/2 + 1, examples.size()));

        StringExamplesAdapter leftAdapter = new StringExamplesAdapter(getContext(), leftExamples);
        StringExamplesAdapter rightAdapter = new StringExamplesAdapter(getContext(), rightExamples);

        ExpandableHeightListView leftListView = view.findViewById(R.id.examples_left_col);
        ExpandableHeightListView rightListView = view.findViewById(R.id.examples_right_col);

        leftListView.setAdapter(leftAdapter);
        rightListView.setAdapter(rightAdapter);

        leftListView.setExpanded(true);
        rightListView.setExpanded(true);
    }
}
