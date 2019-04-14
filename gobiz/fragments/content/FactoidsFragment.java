package com.example.vaibh.gobiz.fragments.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.adapters.FactoidsAdapter;
import com.example.vaibh.gobiz.pojos.Factoid;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

public class FactoidsFragment extends HeaderAndSubheaderFragment {

    private static final String FACTOIDS = "FACTOIDS";
    private static final String CAPTION = "CAPTION";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factoids, container, false);

        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), getString(R.string.label_factoids));
        setupHeaders(view);

        setupCaption(view);
        setupFactoids(view);
        setupNextButton(view);

        return view;
    }

    private void setupCaption(View view) {
        TextView caption = view.findViewById(R.id.caption);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        caption.setText(bundle.getString(CAPTION));
    }

    private void setupFactoids(View view) {
        ExpandableHeightListView listView = view.findViewById(R.id.factoids_list);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        Context context = getActivity();
        assert context != null;

        ArrayList<Factoid> arr = bundle.getParcelableArrayList(FACTOIDS);
        assert arr != null;

        FactoidsAdapter adapter = new FactoidsAdapter(context, arr);
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

    public void setFactoidsList(ArrayList<Factoid> arr) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putParcelableArrayList(FACTOIDS, arr);
        this.setArguments(args);
    }
}
