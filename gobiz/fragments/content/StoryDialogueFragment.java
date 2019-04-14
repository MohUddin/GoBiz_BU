package com.example.vaibh.gobiz.fragments.content;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibh.gobiz.R;

public class StoryDialogueFragment extends HeaderAndSubheaderFragment {

    public static final String DIALOGUE_STRING = "DIALOGUE_STRING";
    public static final String DIALOGUE_IMAGE_RESOURCE = "DIALOGUE_IMAGE_RESOURCE";

    public void setDialogueString(String text) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putString(StoryDialogueLeftFragment.DIALOGUE_STRING, text);
        this.setArguments(args);
    }

    public void setDialogueImageResource(int imageResource) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putInt(DIALOGUE_IMAGE_RESOURCE, imageResource);
        this.setArguments(args);
    }

    protected void setupDialogue(View view) {
        TextView dialogueView = view.findViewById(R.id.dialogue);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        String dialogueText = bundle.getString(DIALOGUE_STRING);
        dialogueView.setText(dialogueText);
    }

    protected void setupImage(View view) {
        ImageView dialogueImage = view.findViewById(R.id.dialogue_image);

        Bundle bundle = this.getArguments();
        assert bundle != null;

        dialogueImage.setImageResource(bundle.getInt(DIALOGUE_IMAGE_RESOURCE));
    }

    protected void setupFragment(View view) {
        setHeaderStrings("Lesson " + String.valueOf(getLessonNumber()), "Story");
        setupHeaders(view);
        setupNextButton(view);
        setupDialogue(view);
        setupImage(view);
    }
}
