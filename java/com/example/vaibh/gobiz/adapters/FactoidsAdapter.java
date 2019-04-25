package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.pojos.Factoid;

import java.util.ArrayList;

public class FactoidsAdapter extends ArrayAdapter<Factoid> {

    public FactoidsAdapter(Context context, ArrayList<Factoid> factoids) {
        super(context, 0, factoids);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Factoid factoid = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_factoid_item, parent, false);
        }

        TextView factView = convertView.findViewById(R.id.fact);
        ImageView iconView = convertView.findViewById(R.id.icon);

        assert factoid != null;
        factView.setText(factoid.getFact());
        iconView.setImageResource(factoid.getImageResource());
        return convertView;
    }
}
