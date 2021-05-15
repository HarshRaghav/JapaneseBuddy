package android.example.miwok;

import android.widget.ImageView;

public class Word {
    private String English;
    private String JapaneseTranslation;
    public static final int NO_Image=-1;
    private int ImageId=NO_Image;
    private int AudioId;
    public Word(String English,String JapaneseTranslation , int AudioId){
        this.English=English;
        this.JapaneseTranslation=JapaneseTranslation;
        this.AudioId=AudioId;
    }
    public Word(String English,String JapaneseTranslation,int ImageId , int AudioId){
        this.English=English;
        this.JapaneseTranslation=JapaneseTranslation;
        this.ImageId= ImageId;
        this.AudioId=AudioId;

    }
    public String getJapaneseTranslation(){
        return JapaneseTranslation;
    }
    public String getEnglishTranslation(){
        return English;
    }
    public boolean hasImage(){return NO_Image!=ImageId;}
    public int getImageReasourceId(){ return ImageId; }
    public int getAudioId(){ return AudioId;}

}
