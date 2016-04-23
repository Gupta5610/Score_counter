package ashishgupta.com.score_counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Scorekeeping extends AppCompatActivity {

    TextView displayA,displayb,TeamA,TeamB;
    int scoreA=0,scoreB=0;
    Button reset,save,data;
    Intent intent;
    Gameinfo game;
    Calendar now;
    MyDBHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler=new MyDBHandler(Scorekeeping.this);
        setContentView(R.layout.activity_scorekeeping);
        reset=(Button)findViewById(R.id.reset);
        save=(Button)findViewById(R.id.save);
        data=(Button)findViewById(R.id.data);
        TeamA=(TextView)findViewById(R.id.TeamA);
        TeamB=(TextView)findViewById(R.id.TeamB);
        intent=getIntent();
        handler=new MyDBHandler(this);
        TeamA.setText(intent.getStringExtra("1").toString());
        TeamB.setText(intent.getStringExtra("2").toString());
        displayA=(TextView)findViewById(R.id.displayscoreA);
        displayb=(TextView)findViewById(R.id.displayscoreb);
        now=Calendar.getInstance();
        display();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreA = 0;
                scoreB = 0;
                display();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game=new Gameinfo();
                game.setDate(now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.YEAR));
                game.setTeamA(intent.getStringExtra("1").toString());
                game.setTeamB(intent.getStringExtra("2").toString());
                game.setScoreA(String.valueOf(scoreA));
                game.setScoreB(String.valueOf(scoreB));
                if(scoreA>scoreB)
                {game.setWinner(intent.getStringExtra("1").toString());}
                else if (scoreA<scoreB)
                {game.setWinner(intent.getStringExtra("2").toString());}
                else
                {game.setWinner(" --  DRAW -- ");}
                Long i=handler.add_to_db(game);
                Toast.makeText(getApplicationContext(),"Game No :"+i+ "Saved",Toast.LENGTH_SHORT).show();
                intent=new Intent(Scorekeeping.this,Main.class);
                startActivity(intent);

            }
        });



    }

    public void onclickA(View view)
    {
        switch (view.getId())
        {
            case R.id.pta1:scoreA+=1; display(); break;
            case R.id.pta2:scoreA+=2; display();break;
            case R.id.pta3:scoreA+=3; display();break;
        }
    }

    public void onclickB(View view)
    {
        switch (view.getId())
        {
            case R.id.ptb1:scoreB+=1; display(); break;
            case R.id.ptb2:scoreB+=2; display();break;
            case R.id.ptb3:scoreB+=3; display();break;
        }
    }


    public void display()
    {

        displayA.setText(String.valueOf(scoreA));
        displayb.setText(String.valueOf(scoreB));
    }
}
