package standard.example.elamx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends Activity {

    Button button1, button2, button3, button_editor;
    TextView details;
    String editor_name_1;
    SharedPreferences sharedpreferences;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_aktivity);

        // Aus SP die gespeicherte editor_name_1 laden
        sharedpreferences = getSharedPreferences("mypreferences", Context.MODE_PRIVATE);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button_editor = (Button) findViewById(R.id.button_editor);
        details = (TextView) findViewById(R.id.textView14);


        details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String stringname = sharedpreferences.getString("editor_name_1", "default value");
                Toast.makeText(HomeScreen.this, "Variable: Lage 1: " + stringname, Toast.LENGTH_SHORT).show();

                //Toast.makeText(HomeScreen.this, "SharedPreferences: " + sharedpreferences.getString("editor_name_1", "default value"), Toast.LENGTH_SHORT).show();
            }
        });


        // Gehe zu:   LAMINAT OVERVIEW
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeScreen.this, LaminatOverview.class);
                startActivity(myIntent);
            }
        });


        // Gehe zu: LAMINAT EDITOR
        button_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeScreen.this, Editor.class);
                startActivity(myIntent);
            }
        });


    }

}
