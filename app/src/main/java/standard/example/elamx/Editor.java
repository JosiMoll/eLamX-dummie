package standard.example.elamx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Editor extends AppCompatActivity {

    Button button_back, button_test, button_speichern;
    EditText et_name_1, et_material_1, et_winkel_1, et_dicke_1;
    int winkel_1, dicke_1;

    SharedPreferences sharedpreferences;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        button_back = (Button) findViewById(R.id.button_back);
        button_test = (Button) findViewById(R.id.button_test);
        button_speichern = (Button) findViewById(R.id.button_speichern);
        et_name_1 = (EditText) findViewById(R.id.edit_name_1);
        et_material_1 = (EditText) findViewById(R.id.edit_material_1);
        et_winkel_1 = (EditText) findViewById(R.id.edit_winkel_1);
        et_dicke_1 = (EditText) findViewById(R.id.edit_dicke_1);

        //Shared Preferences anlegen
        sharedpreferences = getSharedPreferences("mypreferences", MODE_PRIVATE);


        // Speichern-Button: Variablen in SP speichern.
        button_speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SharedPreferences.Editor myeditor = myPref.edit();
                // SP initialisieren, 0 = private Mode
                //SharedPreferences pref = getApplicationContext().getSharedPreferences("", MODE_PRIVATE);
                //Editor benötigt um Änderungen an den SP durchzuführen
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("editor_name_1", et_name_1.getText().toString());
                editor.commit();

                Toast.makeText(Editor.this, "SharedPreferences: " + sharedpreferences.getString("editor_name_1", "default value"), Toast.LENGTH_SHORT).show();


                //et_name_1 = (EditText) findViewById(R.id.edit_name_1);
                //et_material_1 = (EditText) findViewById(R.id.edit_material_1);

                //winkel_1 = Integer.valueOf(et_winkel_1.getText().toString());
                //dicke_1 = Integer.valueOf(et_winkel_1.getText().toString());

            }
        });


        // Müssen noch "aktiviert" werden, z.b. auf die zurücktaste oder das bestätigen der Eingabe (sieht et_name_1)


        //Variable-Test-Button
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String name_1 = et_name_1.getText().toString();
                Toast.makeText(Editor.this, "name_1:  " + et_name_1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Back to Homescreen-Button
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Editor.this, HomeScreen.class);
                startActivity(myintent);
            }
        });


    }

    ;
}





