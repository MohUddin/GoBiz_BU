package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.adapters.UnmetNeedsAndSolutionsAdapter;
import com.example.vaibh.gobiz.customviews.CustomViewPager;
import com.example.vaibh.gobiz.pojos.UnmetNeedAndSolution;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

public class ExamplesFragment2 extends HeaderAndSubheaderFragment {

    public static final String CAPTION = "CAPTION";
    public static final String UNMET_NEEDS_AND_SOLUTIONS = "UNMET_NEEDS_AND_SOLUTIONS";
    private static final String INSTRUCTIONS = "INSTRUCTIONS";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examples_2, container, false);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), "Examples");
        setupHeaders(view);

        setupNextButton(view);
        setupCaption(view);
        setupInstructions(view);
        setupExamplesList(view, (CustomViewPager) container);

        return view;
    }

    public void setUnmetNeedsAndSolutionsArrayList(ArrayList<UnmetNeedAndSolution> unmetNeedsAndSolutions) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putParcelableArrayList(UNMET_NEEDS_AND_SOLUTIONS, unmetNeedsAndSolutions);
        this.setArguments(args);
    }

    protected void setupExamplesList(View view, CustomViewPager pager) {
        Bundle bundle = this.getArguments();
        assert bundle != null;

        ArrayList<UnmetNeedAndSolution> unmetNeedAndSolutions = bundle.getParcelableArrayList(UNMET_NEEDS_AND_SOLUTIONS);
        ArrayAdapter adapter = new UnmetNeedsAndSolutionsAdapter(getContext(), unmetNeedAndSolutions, pager);

        ExpandableHeightListView listView = view.findViewById(R.id.examples_list);
        listView.setAdapter(adapter);
        listView.setExpanded(true);
    }

    public void setCaptionString(String string) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(CAPTION, string);
        this.setArguments(args);
    }

    protected void setupCaption(View view) {
        TextView caption = view.findViewById(R.id.caption);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        caption.setText(bundle.getString(CAPTION));
    }

    public void setInstructions(String s) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(INSTRUCTIONS, s);
        this.setArguments(args);
    }

    protected void setupInstructions(View v) {
        TextView instructionsView = v.findViewById(R.id.examples_instructions);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        instructionsView.setText(bundle.getString(INSTRUCTIONS));
    }

}
