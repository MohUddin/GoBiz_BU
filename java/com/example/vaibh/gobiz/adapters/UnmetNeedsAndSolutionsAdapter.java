package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.customviews.CustomViewPager;
import com.example.vaibh.gobiz.pojos.UnmetNeedAndSolution;

import java.util.ArrayList;

public class UnmetNeedsAndSolutionsAdapter extends ArrayAdapter<UnmetNeedAndSolution> implements View.OnTouchListener {

    private CustomViewPager pager;

    private UnmetNeedsAndSolutionsAdapter(Context context, ArrayList<UnmetNeedAndSolution> unmetNeedAndSolutions) {
        super(context, 0, unmetNeedAndSolutions);
    }

    public UnmetNeedsAndSolutionsAdapter(Context context, ArrayList<UnmetNeedAndSolution> unmetNeedAndSolutions, CustomViewPager pager) {
        super(context, 0, unmetNeedAndSolutions);
        this.pager = pager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final UnmetNeedAndSolution unmetNeedAndSolution = getItem(position);
        assert unmetNeedAndSolution != null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_unmet_need_and_solution_item, parent, false);

            // address swiping bug
            convertView.findViewById(R.id.swipeable_overlay).setOnTouchListener(this);
            convertView.findViewById(R.id.swipeable_underlay).setOnTouchListener(this);
        }

        TextView unmetNeedView = convertView.findViewById(R.id.unmet_need);
        TextView solutionView = convertView.findViewById(R.id.solution);
        SwipeRevealLayout swipeRevealLayout = convertView.findViewById(R.id.swipe_reveal_layout);

        unmetNeedView.setText(unmetNeedAndSolution.getUnmetNeed());
        solutionView.setText(unmetNeedAndSolution.getSolution());

        // maintain state
        if (unmetNeedAndSolution.isOpen()) {
            swipeRevealLayout.open(false);
        } else {
            swipeRevealLayout.close(false);
        }

        swipeRevealLayout.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
            @Override
            public void onClosed(SwipeRevealLayout view) {
                unmetNeedAndSolution.setOpen(false);
            }

            @Override
            public void onOpened(SwipeRevealLayout view) {
                unmetNeedAndSolution.setOpen(true);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {

            }
        });

        return convertView;
    }

    // todo: figure out how to cleanly avoid duplicating this code block from StoryQuestionsFragment.java
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                pager.disableScroll(true);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                pager.disableScroll(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                pager.disableScroll(false);
                break;
        }
        return true;
    }
}
