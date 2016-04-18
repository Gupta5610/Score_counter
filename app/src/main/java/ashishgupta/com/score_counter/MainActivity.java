package ashishgupta.com.score_counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView displayA,displayb;
    int scoreA=0,scoreB=0;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset=(Button)findViewById(R.id.reset);
        displayA=(TextView)findViewById(R.id.displayscoreA);
        displayb=(TextView)findViewById(R.id.displayscoreb);
        display();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreA=0;scoreB=0;display();
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
