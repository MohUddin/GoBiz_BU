package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class StoryIntroFragment extends HeaderAndSubheaderFragment {

    public static final String CAPTION = "CAPTION";
    public static final String STORY_INTRO = "STORY_INTRO";
    public static final String IMAGE_RES_ID = "IMAGE_RES_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_intro, container, false);
        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), "Story");
        setupHeaders(view);
        setupNextButton(view);

        Bundle args = getArguments();
        assert args != null;

        String caption = args.getString(CAPTION);
        String storyIntro = args.getString(STORY_INTRO);
        int imageResId = args.getInt(IMAGE_RES_ID);

        TextView captionView = view.findViewById(R.id.caption);
        captionView.setText(caption);

        TextView storyIntroView = view.findViewById(R.id.story_intro);
        storyIntroView.setText(storyIntro);

        ImageView imageView = view.findViewById(R.id.lesson_welcome_image);
        imageView.setImageDrawable(getResources().getDrawable(imageResId, null));

        return view;
    }
}
