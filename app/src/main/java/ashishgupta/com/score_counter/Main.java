package ashishgupta.com.score_counter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {
   LinearLayout l1;
    EditText Team1,Team2;
    TextView first,second;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        first= new TextView(this);
        second=new TextView(this);
        first.setText("First Team :-");
        second.setText("Second Team :-");
        l1=new LinearLayout(this);
        l1.setOrientation(LinearLayout.VERTICAL);
        l1.setPadding(50,10,10,10);
        Team1=new EditText(this);
        Team2=new EditText(this);
        l1.addView(first);
        l1.addView(Team1,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        l1.addView(second);
        l1.addView(Team2,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Team Name :-");
        builder.setView(l1);
        builder.setCancelable(false);
        builder.setNeutralButton("Continue ...", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Main.this,Scorekeeping.class);
                intent.putExtra("1",Team1.getText().toString());
                intent.putExtra("2",Team2.getText().toString());
                ((ViewGroup) l1.getParent()).removeView(l1);
                startActivity(intent);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ViewGroup) l1.getParent()).removeView(l1);
                dialog.cancel();
            }
        });

    }

    public void onclick(View view)
    {
        switch(view.getId())
        {
            case R.id.data: Intent intent= new Intent(Main.this,List.class);
                            intent.putExtra("Option",1);
                            startActivity(intent);
                            break;
            default:   builder.show();
        }
    }
}
