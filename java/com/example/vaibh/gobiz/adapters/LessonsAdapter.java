package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.pojos.Module;
import com.example.vaibh.gobiz.pojos.ModuleMapLock;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;

import java.util.ArrayList;

public class LessonsAdapter extends ArrayAdapter<Module> {

    private boolean enabledModule = true;

    public LessonsAdapter(Context context, ArrayList<Module> modules) {
        super(context, 0, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Module module = getItem(position);

        Log.d("Module Value Lesson",Boolean.toString(ModuleMapLock.moduleMap.containsKey(module.getModuleNumber())));
        if (ModuleMapLock.moduleMap.get(module.getModuleNumber()).toString().equals("false")) {
            enabledModule = false;
        }
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_course_or_lesson_item, parent, false);
        }

        TextView lessonTitle = convertView.findViewById(R.id.itemName);
        TextView lessonDescription = convertView.findViewById(R.id.itemDescription);
        DonutProgress donutProgress = convertView.findViewById(R.id.donutProgress);


        assert module != null;
        lessonTitle.setText(module.getTitle());

        // todo: add description field to module class
        lessonDescription.setText(getContext().getString(R.string.lesson_description_placeholder));
        lessonTitle.setTypeface(null, Typeface.BOLD);

        // position <= 1 for hard locking unimplemented content
        boolean isUnlocked = enabledModule && position <= 1;
        if (!isUnlocked) {
            donutProgress.setProgress(0f);
            donutProgress.setText("");
            convertView.findViewById(R.id.padlock).setVisibility(View.VISIBLE);
        } else {
            convertView.findViewById(R.id.padlock).setVisibility(View.INVISIBLE);

            // todo: persist and track content progress rather than setting a constant value every time
            if (position == 0) {
                donutProgress.setProgress(100f);
            } else {
                donutProgress.setProgress(30f);
            }

            donutProgress.setText((int) (donutProgress.getProgress()) + "%");
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return enabledModule;
    }
}
