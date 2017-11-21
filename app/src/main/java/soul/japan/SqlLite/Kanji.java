package soul.japan.SqlLite;

/**
 * Created by xavi_ on 19/11/2017.
 */

public class Kanji {
    //private variables
    int _id;
    String _kanji;

    // Empty constructor
    public Kanji(){

    }
    // constructor
    public Kanji(int id, String kanji){
        this._id = id;
    }

    public Kanji( String kanji){
        this._kanji = kanji;
    }


    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getKanji(){
        return this._kanji;
    }

    // setting name
    public void setKanji(String kanji){
        this._kanji = kanji;
    }

}
