package android.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer mp;
    private AudioManager mAM;

    private AudioManager.OnAudioFocusChangeListener mOnAudioChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mp.pause();
                mp.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mp.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mcomp = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  =inflater.inflate(R.layout.activity_common, container,false);
        mAM = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "Otōsan",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "Haha",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "Musuko",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "Musume",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "Nīsan",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "Otōto",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "Onēsan",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "Imōto",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother", "Sobo",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "Sofu",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word w = words.get(position);
                int result = mAM.requestAudioFocus(mOnAudioChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = MediaPlayer.create(getActivity(), w.getAudioId());
                    mp.start();
                    mp.setOnCompletionListener(mcomp);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mp!=null) {
            mp.release();
            mp = null;
            mAM.abandonAudioFocus(mOnAudioChangeListener);
        }
    }
}