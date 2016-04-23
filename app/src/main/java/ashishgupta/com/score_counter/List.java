package ashishgupta.com.score_counter;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private ListView listView;
    private Intent intent;  // for starting new activities
    private MyDBHandler handler; // for database Manipulation
    private ArrayList<String> arr,scoreA,scoreB,winner,TeamA,TeamB;
    private String date;
    private Intent data; // for catching data from new activites
    private int option;
    private Cursor c;
    AlertDialog.Builder alertDialog;  // for retrieving imformation from database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView=(ListView)findViewById(R.id.listView);
        handler=new MyDBHandler(this);
        alertDialog=new AlertDialog.Builder(this);

        arr=new ArrayList<String>();

        data=getIntent();
        option=data.getIntExtra("Option", 0);


        // selection according to option

        if(option==1) {   c = handler.all();  }
        else if(option==2) {

            scoreA=new ArrayList<String>();
            scoreB=new ArrayList<String>();
            winner=new ArrayList<String>();
            TeamA=new ArrayList<String>();
            TeamB=new ArrayList<String>();

            date=data.getStringExtra("Date");
            c=handler.databasetostring(date);  }


        // checking database count

        if(c.getCount()==0)
        { Toast.makeText(getApplicationContext(), "EMPTY", Toast.LENGTH_LONG).show(); return; }

        try {
            if(option==1) {
                while (c.moveToNext()) {
                    arr.add(c.getString(0));

                }
            }
            else if(option==2)
            {
                while (c.moveToNext()) {
                    TeamA.add(c.getString(2));
                    TeamB.add(c.getString(3));
                    arr.add(c.getString(2)+" Vs "+c.getString(3));
                    scoreA.add(c.getString(4));
                    scoreB.add(c.getString(5));
                    winner.add(c.getString(6));
                }
            }


        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG);
        }

        // setting list in listview

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);

        // OnItemClickListner

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(option==1)
                {
                    intent=new Intent(List.this,List.class);
                    intent.putExtra("Date",arr.get(position).toString());
                    intent.putExtra("Option",2);
                    startActivity(intent);
                }
                else
                {
                    alertDialog.setTitle("Winner : "+winner.get(position).toString());
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage(TeamA.get(position)+" - Score :"+scoreA.get(position).toString()+"\n\n "+TeamB.get(position)+" - Score :"+scoreB.get(position).toString());
                    alertDialog.show();
                }
            }
        });

    }
}
