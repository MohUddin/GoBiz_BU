package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibh.gobiz.R;

// todo: refactor all fragments to use composition rather than inheritance to get modular behavior
public class ConclusionFragment extends GenericHeaderImageCaptionScrollableBodyTextNextButtonFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conclusion, container, false);
        setupFragment(view);
        return view;
    }
}
