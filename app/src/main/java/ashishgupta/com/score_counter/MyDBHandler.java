package ashishgupta.com.score_counter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDBHandler extends SQLiteOpenHelper
{   public String TAG="HANDLER MESSAGE ";
    private static final int DATABSE_VERSION = 1;
    private static final String DATABASE_NAME="Score.db";
    private static final String TABLE_NAME="Game";
    private static final String COLUMN_ID="ID";
    private static final String DATE="DATE";
    private static final String TEAM_A="TEAM_A";
    private static final String TEAM_B="TEAM_B";
    private static final String WINNER="WINNER";
    private static final String SCORE_A="SCORE_A";
    private static final String SCORE_B="SCORE_B";





    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + DATE + " VARCHAR(20),"
                + TEAM_A + " VARCHAR(20) ,"
                + TEAM_B + " VARCHAR(20) ,"
                + SCORE_A + " VARCHAR(3) ,"
                + SCORE_B + " VARCHAR(3) ,"
                + WINNER + " VARCHAR(20));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long add_to_db( Gameinfo game)
    {
        ContentValues value=new ContentValues();
        SQLiteDatabase db=getWritableDatabase();
        value.put(DATE,game.getDate());
        value.put(TEAM_A,game.getTeamA());
        value.put(TEAM_B,game.getTeamB());
        value.put(SCORE_A,game.getScoreA());
        value.put(SCORE_B,game.getScoreB());
        value.put(WINNER,game.getWinner());
        long  i = db.insert(TABLE_NAME, null, value);
        db.close();
        return i;

    }


    public Cursor databasetostring(String date)
    {
        SQLiteDatabase db=getWritableDatabase();
        String Query="SELECT * FROM "+TABLE_NAME +" WHERE DATE = \'"+date+"\'";
        Cursor c = db.rawQuery(Query,null);
        return c;
    }
    public Cursor all()
    {

        SQLiteDatabase db=getWritableDatabase();
        String Query="SELECT DISTINCT DATE FROM "+TABLE_NAME ;
        Cursor c = db.rawQuery(Query,null);
        return c;
    }
}
