package com.example.vaibh.gobiz.fragments.content;

import android.view.View;
import android.widget.EditText;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.utils.SharedPrefsUtil;

import static com.example.vaibh.gobiz.fragments.content.ExerciseBringingItBackFragment.ANSWER_1;
import static com.example.vaibh.gobiz.fragments.content.ExerciseBringingItBackFragment.ANSWER_2;

public class AnswersFragment extends HeaderAndSubheaderFragment {

    private View root;
    private String tag = "";

    public void refreshAnswers() {
        if (root != null) {
            EditText answer1 = root.findViewById(R.id.answer_1);
            EditText answer2 = root.findViewById(R.id.answer_2);

            if (answer1 != null && answer2 != null) {
                SharedPrefsUtil sp = SharedPrefsUtil.getInstance(getActivity());
                String t1 = sp.getString(String.valueOf(getLessonNumber()) + tag + ANSWER_1, "");
                String t2 = sp.getString(String.valueOf(getLessonNumber()) + tag + ANSWER_2, "");
                answer1.setText(t1);
                answer2.setText(t2);
            }
        }
    }

    public void saveAnswers() {
        if (root != null) {
            EditText answer1 = root.findViewById(R.id.answer_1);
            EditText answer2 = root.findViewById(R.id.answer_2);

            if (answer1 != null && answer2 != null) {
                SharedPrefsUtil sp = SharedPrefsUtil.getInstance(getActivity());

                String t1 = answer1.getText().toString();
                sp.setString(String.valueOf(getLessonNumber()) + tag + ANSWER_1, t1);

                String t2 = answer2.getText().toString();
                sp.setString(String.valueOf(getLessonNumber()) + tag + ANSWER_2, t2);
            }
        }
    }

    protected void setRoot(View root) {
        this.root = root;
    }

    public void setTag(String s) { tag = s; }
}
