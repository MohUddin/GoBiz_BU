package com.example.vaibh.gobiz.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;
import com.example.vaibh.gobiz.pojos.QuizQuestion;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizQuestionsAdapter extends ArrayAdapter<QuizQuestion> {

    public QuizQuestionsAdapter(Context context, ArrayList<QuizQuestion> questions) {
        super(context, 0, questions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final QuizQuestion question = getItem(position);
        assert question != null;

        ArrayList<String> options = question.getOptions();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_multiple_choice_item, parent, false);
        }

        TextView questionView = convertView.findViewById(R.id.question);
        questionView.setText(question.getQuestion());

        TextView questionCounter = convertView.findViewById(R.id.question_counter);
        questionCounter.setText(String.format(getContext().getString(R.string.question_counter), String.valueOf(position + 1), String.valueOf(getCount())));

        final ArrayList<View> optionContainers = new ArrayList<>(Arrays.asList(
                convertView.findViewById(R.id.option_1),
                convertView.findViewById(R.id.option_2),
                convertView.findViewById(R.id.option_3),
                convertView.findViewById(R.id.option_4)
        ));

        for (int i = 0; i < optionContainers.size(); i++) {
            final int index = i;
            View container = optionContainers.get(i);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (View container : optionContainers) {
                        container.setBackgroundColor(Color.parseColor(getContext().getString(R.string.default_background)));
                    }
                    view.setBackgroundColor(Color.parseColor(getContext().getString(R.string.selected_list_item_bg)));
                    question.setSelectedIndex(index);
                }
            });
        }

        ArrayList<String> letters = new ArrayList<>(Arrays.asList("A)", "B)", "C)", "D)"));

        for (int i = 0; i < optionContainers.size(); i++) {
            View v = optionContainers.get(i);
            TextView letterView = v.findViewById(R.id.letter_denominator);
            TextView optionView = v.findViewById(R.id.option);

            if (i < options.size()) {
                v.setVisibility(View.VISIBLE);

                letterView.setText(letters.get(i));
                optionView.setText(options.get(i));
            } else {
                v.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

}
