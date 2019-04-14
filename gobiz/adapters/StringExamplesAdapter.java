package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

import java.util.ArrayList;

public class StringExamplesAdapter extends ArrayAdapter<String> {

    public StringExamplesAdapter(Context context, ArrayList<String> examples) {
        super(context, 0, examples);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String example = getItem(position);
        assert example != null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_example_item, parent, false);
        }

        TextView exampleView = convertView.findViewById(R.id.example);
        exampleView.setText(example);

        return convertView;
    }
}
