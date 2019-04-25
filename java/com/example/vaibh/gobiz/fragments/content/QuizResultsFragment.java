package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.adapters.LessonContentPagerAdapter;
import com.example.vaibh.gobiz.customviews.CustomViewPager;
import com.example.vaibh.gobiz.pojos.Module;
import com.example.vaibh.gobiz.pojos.ModuleMapLock;
import com.example.vaibh.gobiz.pojos.ModuleScore;

import java.util.Objects;

import static com.example.vaibh.gobiz.fragments.content.QuizFragment.MESSAGE;
import static com.example.vaibh.gobiz.fragments.content.QuizFragment.PASSED;
import static com.example.vaibh.gobiz.fragments.content.QuizFragment.SCORE_STRING;

public class QuizResultsFragment extends HeaderAndSubheaderFragment implements View.OnClickListener {

    private static final String REVIEW_TAG = "REVIEW_TAG";
    private static final String CONTINUE_TAG = "CONTINUE_TAG";
    private static final String Module = "Mod";
    private static String nextModule;
    private static Float UpdateScore;
    private static String TAG_SCORE = "FINAL_SCORE";
    private String[] ScoreNumDeno;
    CustomViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_results, container, false);
        setHeaderStrings(getString(R.string.label_lesson) + " " + String.valueOf(getLessonNumber()), getString(R.string.label_results));
        setupHeaders(view);

        String caption = getString(R.string.label_score);
        ((TextView) view.findViewById(R.id.caption)).setText(caption);

        Bundle args = getArguments();
        assert args != null;

        String score = args.getString(SCORE_STRING);


        ScoreNumDeno = score.split("/");
        if (ScoreNumDeno[1].equals('0')){
            UpdateScore = 0.0f;
        }
        else {
            UpdateScore = Float.parseFloat(ScoreNumDeno[0]) / Float.parseFloat(ScoreNumDeno[1]);
        }
        Log.d(TAG_SCORE, UpdateScore.toString());


        String message = args.getString(MESSAGE);
        boolean passedQuiz = args.getBoolean(PASSED);
        ((TextView) view.findViewById(R.id.score)).setText(score);
        ((TextView) view.findViewById(R.id.result_message)).setText(message);

        setupButtons(view, passedQuiz);

        viewPager = (CustomViewPager) container;
        assert viewPager != null;
        viewPager.disableScroll(true);

        return view;
    }

    private void setupButtons(View view, boolean quizPassed) {
        View upperButton = view.findViewById(R.id.upper_button);
        View lowerButton = view.findViewById(R.id.lower_button);

        if (!quizPassed) {
            upperButton.setVisibility(View.INVISIBLE);
            ((Button) lowerButton).setText(getString(R.string.label_review));
            lowerButton.setTag(REVIEW_TAG);
        } else {
            upperButton.setTag(REVIEW_TAG);
            lowerButton.setTag(CONTINUE_TAG);
            Log.d("Quiz Result",Boolean.toString(ModuleMapLock.moduleMap.containsKey("Mod1")));
            nextModule = Module + String.valueOf(getLessonNumber()+1);
            ModuleMapLock.editModule(nextModule);
        }

        ModuleScore.updateScore(Module + String.valueOf(getLessonNumber()), UpdateScore);

        upperButton.setOnClickListener(this);
        lowerButton.setOnClickListener(this);
    }


    private void onContinueClicked() {
        Objects.requireNonNull(getActivity()).finish();
    }

    private void onReviewClicked() {
        viewPager.disableScroll(false);
        viewPager.setCurrentItem(1, true);
        LessonContentPagerAdapter adapter = (LessonContentPagerAdapter) Objects.requireNonNull(viewPager.getAdapter());
        adapter.popFragment();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        String tag = view.getTag().toString();
        switch (tag) {
            case CONTINUE_TAG:
                onContinueClicked();
                break;
            case REVIEW_TAG:
                onReviewClicked();
                break;
            default:
                break;
        }
    }
}
