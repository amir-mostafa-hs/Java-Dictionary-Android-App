package com.example.dictionaryapp;

public class DictionaryWord {
    public String Word;
    public String PersianTranslate;
    public String ArabicTranslate;
    public String Pronounce;
    public String Descriptions;

    // create constructor method for set class properties value
    public DictionaryWord(String word,String persianTranslate,String arabicTranslate,String pronounce,String descriptions) {
        this.Word = word;
        this.PersianTranslate = persianTranslate;
        this.ArabicTranslate = arabicTranslate;
        this.Pronounce = pronounce;
        this.Descriptions = descriptions;
    }
}
