package com.example.vaibh.gobiz.fragments.content;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.vaibh.gobiz.LessonActivity;
import com.example.vaibh.gobiz.R;

import java.util.Objects;

public class NextButtonFragment extends Fragment {

    protected void setupNextButton(View view) {
        View nextButton = view.findViewById(R.id.next_button);
        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((LessonActivity) Objects.requireNonNull(getActivity())).gotoNextPage();
                }
            });
        }
    }
}
