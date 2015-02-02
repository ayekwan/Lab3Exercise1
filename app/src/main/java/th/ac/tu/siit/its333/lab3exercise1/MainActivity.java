package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    TextView TVGP;
    TextView TVC;
    TextView TVGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        TVGP = (TextView)findViewById(R.id.tvGP);
        TVC = (TextView)findViewById(R.id.tvCR);
        TVGPA = (TextView)findViewById(R.id.tvGPA);
        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public double calGrade(){
        double sum = 0.0;
        for(int i = 0; i < listGrades.size(); i++){
            sum += lookup(listGrades.get(i)) * listCredits.get(i);
        }
        return sum;
    }

    public double lookup(String grade){

        switch (grade){
            case "A":
                return 4.0;

            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "D+":
                return 1.5;
            case "D":
                return 1.0;
            case "F":
                return 0.0;



        }
        return 0;
    }

    public double calCredits(){
        double sum = 0.0;
        for(int i = 0; i < listCredits.size(); i++){
            sum += listCredits.get(i);
        }
        return sum;
    }

    public void buttonClicked(View v) {
        int id = v.getId();

        switch(id) {
            case R.id.button2:
                Intent i = new Intent(this, CourseActivity.class);
                startActivityForResult(i, 88);
                break;
            case R.id.button4:
                Intent j = new Intent(this, CourseListActivity.class);
                String s = "List of Courses\n";

                for(int k = 0; k < listCodes.size(); k++) {
                    s += listCodes.get(k) + "(" + listCredits.get(k) + "credits) = " + listGrades.get(k) +"\n";
                    j.putExtra("xxx", s);
                }
                //putExtra attaches a value named "toAct3" to the intent.

                startActivity(j);
                break;
            case R.id.button:
                listCodes.clear();
                listCredits.clear();
                listGrades.clear();
                TVGP.setText(calGrade() + "");
                TVC.setText(calCredits() + "");
                TVGPA.setText("0");
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                //Retrieve the returned value from Activity3
                //use getStringExtra when the returned value is a string.
                //Otherwise, we can use getIntExtra, getDoubleExtra
                String val1 = data.getStringExtra("val1");
                listCodes.add(val1);

                Integer val2 = Integer.parseInt(data.getStringExtra("val2"));
                listCredits.add(val2);

                String val3 = data.getStringExtra("val3");
                listGrades.add(val3);

            }

            TVGP.setText(calGrade() + "");
            TVC.setText(calCredits() + "");
            TVGPA.setText(calGrade()/calCredits() + "");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
