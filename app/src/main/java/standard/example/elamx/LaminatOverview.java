package standard.example.elamx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LaminatOverview extends AppCompatActivity {

    Button button1, button2, button3, button4;
    private LinearLayout parentlayout; //Parentlayout in dem Felder hinzugefügt werden


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laminat_overview);

        parentlayout = (LinearLayout) findViewById(R.id.parent_layout); // Parent Layout wird der ID zugewiesen

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button_editor);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(LaminatOverview.this, HomeScreen.class);
                startActivity(myIntent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(LaminatOverview.this, "Menü öffnet sich", Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddField(v);
            }
        });
    }

    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowview = inflater.inflate(R.layout.field2, null);
        //Add the new row before the add field button
        parentlayout.addView(rowview);
        button1.findViewById(R.id.button2);

    }


}
