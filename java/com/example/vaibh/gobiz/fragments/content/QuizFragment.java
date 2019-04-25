package com.example.vaibh.gobiz.fragments.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibh.gobiz.LessonActivity;
import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.adapters.LessonContentPagerAdapter;
import com.example.vaibh.gobiz.adapters.QuizQuestionsAdapter;
import com.example.vaibh.gobiz.pojos.QuizQuestion;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.Objects;

public class QuizFragment extends HeaderAndSubheaderFragment {

    private static final String CAPTION = "CAPTION";
    private static final String QUESTIONS = "QUESTIONS";
    public static final String MESSAGE = "MESSAGE";
    public static final String SCORE_STRING = "SCORE_STRING";
    public static final String PASSED = "PASSED";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        setHeaderStrings(getString(R.string.label_lesson) + " " + getLessonNumber(), getString(R.string.label_quiz));
        setupHeaders(view);
        setupCaption(view);
        setupQuestions(view);
        setupNextButton(view);

        return view;
    }

    @Override
    protected void setupNextButton(View view) {
        View nextButton = view.findViewById(R.id.next_button);
        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // get question objects
                    View root = (View) view.getParent();
                    ExpandableHeightListView listView = root.findViewById(R.id.quiz_questions_list);

                    ArrayList<QuizQuestion> questions = new ArrayList<>();

                    for (int i = 0; i < listView.getCount(); i++) {
                        QuizQuestion question = (QuizQuestion) listView.getItemAtPosition(i);
                        questions.add(question);
                    }

                    // ensure all were answered and that they passed
                    int numAnswered = 0;
                    int numCorrect = 0;

                    for (QuizQuestion question : questions) {
                        if (question.getSelectedIndex() != -1) {
                            numAnswered += 1;
                        }
                        if (question.getSelectedIndex() == question.getAnswerIndex()) {
                            numCorrect += 1;
                        }
                    }

                    if (numAnswered < questions.size()) {
                        Toast.makeText(getActivity(), getString(R.string.please_answer_all_questions), Toast.LENGTH_SHORT).show();
                    } else {
                        QuizResultsFragment f = new QuizResultsFragment();
                        f.setLessonNumber(getLessonNumber());

                        boolean passedQuiz = numCorrect >= questions.size() - 1;
                        String message;
                        if (passedQuiz) {
                            message = getString(R.string.passed_quiz_string);
                        } else {
                            message = getString(R.string.message_failed_quiz);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString(MESSAGE, message);
                        bundle.putString(SCORE_STRING, String.valueOf(numCorrect) + "/" + String.valueOf(questions.size()));
                        bundle.putBoolean(PASSED, passedQuiz);

                        f.setArguments(bundle);

                        ViewPager viewPager = Objects.requireNonNull(getActivity()).findViewById(R.id.fragment_container);
                        LessonContentPagerAdapter adapter = (LessonContentPagerAdapter) Objects.requireNonNull(viewPager.getAdapter());
                        adapter.addFragment(f);
                        viewPager.getAdapter().notifyDataSetChanged();
                        ((LessonActivity) getActivity()).gotoNextPage();
                    }
                }
            });
        }
    }

    public void setQuestionsList(ArrayList<QuizQuestion> arr) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putParcelableArrayList(QUESTIONS, arr);
        this.setArguments(args);
    }

    private void setupQuestions(View view) {
        ExpandableHeightListView listView = view.findViewById(R.id.quiz_questions_list);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        Context context = getActivity();
        assert context != null;

        ArrayList<QuizQuestion> arr = bundle.getParcelableArrayList(QUESTIONS);
        assert arr != null;

        for (QuizQuestion question : arr) {
            question.setSelectedIndex(-1);
        }

        QuizQuestionsAdapter adapter = new QuizQuestionsAdapter(context, arr);
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

    private void setupCaption(View view) {
        TextView caption = view.findViewById(R.id.caption);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        caption.setText(bundle.getString(CAPTION));
    }

}
