package com.example.kutchhi;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class FragmentNumbers extends Fragment {


    public FragmentNumbers() {
        // Required empty public constructor
    }
    private MediaPlayer mediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "one","एकडो", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("two", "one","ब", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("three", "one","त्रै", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("four", "one","चार", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("five", "one","पंज", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("six", "one","छः", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("seven", "one","सत", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("eight", "one","अठ", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("nine", "one","नॅा", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("ten", "one","डों", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("eleven", "one","अग्यारो", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("twelve", "one","बारो", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("thirteen", "one","तेरौ", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("fourteen", "one","चौडों", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("fifteen", "one","पंद्रो", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("sixteen", "one","सोळो", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("seventeen", "one","सत्रौ", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("eighteen", "one","अठरौ", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("nineteen", "one","औगनीस", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        words.add(new Word("twenty", "one","वीस", R.drawable.ic_launcher_foreground,R.raw.placeholder_audio));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words);
        ListView listView = (ListView) rootView.findViewById(R.id.wordlist);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Word word= words.get(position);
                mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResource());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }
}