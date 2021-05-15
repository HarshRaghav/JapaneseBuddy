package android.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorId;
    WordAdapter(Activity context , ArrayList<Word> al , int colorId ){
        super(context,0,al);
        this.colorId = colorId;
    }
    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup parent) {
        Word current = getItem(position);
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent , false);
        }
        TextView mt = (TextView)listItemView.findViewById(R.id.japanese_text_view);
        mt.setText(current.getJapaneseTranslation());
        TextView et = (TextView)listItemView.findViewById(R.id.english_text_view);
        et.setText(current.getEnglishTranslation());


        ImageView icon  =(ImageView)listItemView.findViewById(R.id.image);
        if (current.hasImage()){
            icon.setImageResource(current.getImageReasourceId());
            icon.setVisibility(View.VISIBLE);
        }
        else{
            icon.setVisibility(View.GONE);
        }
        View textId = listItemView.findViewById(R.id.text_ll);
        int color = ContextCompat.getColor(getContext(),colorId);
        textId.setBackgroundColor(color);
        return listItemView;
    }
}
