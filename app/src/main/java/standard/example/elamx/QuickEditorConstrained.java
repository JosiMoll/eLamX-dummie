package standard.example.elamx;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class QuickEditorConstrained extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int lagenanzahl;
    ArrayList<View> viewsList = new ArrayList<>();
    ArrayList<Integer> angleList = new ArrayList<>();

    SharedPreferences sharedPreferences;


    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_editor_constraint);

        lagenanzahl = 0;

        // Toolbar als ActionBar einfügen -- + zusätzlich Override Methode weiter unten...
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.inflateMenu(R.menu.menu);
        // Back-Arrow hinzufügen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Standard Activity-Title entfernen
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Überschrift anpassen
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Quick Editor 2");
        // Icons der Toolbar ändern
        myToolbar.setOverflowIcon(getDrawable(R.drawable.ic_menu_black_24dp));
        myToolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        // Materialauswahl - Spinner
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materialien, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        // Versagenskriterium Spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.versagenskriterium, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        LinearLayout editorLayout = (LinearLayout) findViewById(R.id.editorlayout);



        // Delete Button deaktivieren für die erste Lage
        ImageButton deleteButton = (ImageButton) editorLayout.findViewById(R.id.deleteButton);
        deleteButton.setImageResource(R.drawable.ic_delete_white_24dp);
        deleteButton.setEnabled(false);

        // Laden von Lagen bei Start der Activity

        String s2 = sharedPreferences.getString("PREFS_KEY", "");
        StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
        ArrayList<Integer> loadedList = new ArrayList<Integer>();
        while (stringTokenizer.hasMoreTokens()){
            loadedList.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        //for (int i = 0; i < loadedList.size(); i++ ){
        //    onAddField(editorLayout);
        //}


        // Test BUTTON
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setText("Speichern");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Winkel - Liste speichern
                sharedPreferences = getSharedPreferences("PREFS_KEY", MODE_PRIVATE);

                // SharedPreferences Test
                String s = "";
                for (Integer i : angleList){
                    s += i + ",";
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("PREFS_KEY", s);
                editor.commit();

                // Aus SharedPreferences auslesen.



                Toast.makeText(getApplicationContext()  , "Array Gespeichert: " + angleList
                        , Toast.LENGTH_SHORT).show();




            }
        });

        onAddField(editorLayout);

    }

    public void onAddField(View v) {

        lagenanzahl++;

        // Layout bestimmen und Inflater initialisieren
        final LinearLayout editorlayout = (LinearLayout) findViewById(R.id.editorlayout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View neueLage = inflater.inflate(R.layout.quickeditorrow, null);

        // Array bekommt Eintrag praktisch für die nächste Lage, letzter Array-Eintrag muss bei Auswertung gelöscht werden, da er auf die ausgegraute unterste Schicht geht.
        angleList.add(9999);

        viewsList.add(neueLage);

        TextView textView = neueLage.findViewById(R.id.textView1);
        final ImageButton deleteButton = neueLage.findViewById(R.id.deleteButton);
        Button button1 = neueLage.findViewById(R.id.button1);
        Button button2 = neueLage.findViewById(R.id.button2);
        Button button3 = neueLage.findViewById(R.id.button3);
        Button button4 = neueLage.findViewById(R.id.button4);
        Button button5 = neueLage.findViewById(R.id.button5);

        // Wenn Button1 (-) in der untersten Lage ist muss er disabled werden da sonst im Array -9999 stehen kann, der 9999-Wert also nicht durch Buttonklick erzwungen ausgetauscht wird.
        if (angleList.get(viewsList.indexOf(neueLage)) == 9999){
            button1.setEnabled(false);
        }
        else{
            button1.setEnabled(true);
        }

        // disable delete-button (graue Lage)
        deleteButton.setImageResource(R.drawable.ic_delete_white_24dp);
        deleteButton.setEnabled(false);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button1 = neueLage.findViewById(R.id.button1);

                // Wenn Button=GRAU -> button blau machen
                if (button1.getCurrentTextColor() == getResources().getColor(R.color.colorGrey5)) {
                    buttonON(button1);
                } else {
                    buttonOFF(button1);
                }

                if (lagenanzahl == viewsList.indexOf(neueLage) + 1) {
                    onAddField(editorlayout);
                }
                int winkel = angleList.get(viewsList.indexOf(neueLage));
                angleList.set(viewsList.indexOf(neueLage), -1*angleList.get(viewsList.indexOf(neueLage)));


                updateLaminataufbau();

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button1 = neueLage.findViewById(R.id.button1);
                Button button2 = neueLage.findViewById(R.id.button2);
                Button button3 = neueLage.findViewById(R.id.button3);
                Button button4 = neueLage.findViewById(R.id.button4);
                Button button5 = neueLage.findViewById(R.id.button5);

                // Wenn Button=GRAU -> button blau machen
                if (button2.getCurrentTextColor() == getResources().getColor(R.color.colorGrey5)) {
                    buttonON(button2);
                    buttonOFF(button3);
                    buttonOFF(button4);
                    buttonOFF(button5);
                }

                // Wenn aktueller View unterster ist, dann wird neue Lage hinzugefügt
                if (lagenanzahl == viewsList.indexOf(neueLage) + 1) {
                    onAddField(editorlayout);
                }


                // Button2 (0 Grad) Klick: Button1(-) auf grau schalten und disablen. Winkel auf 0 Grad stellen
                buttonOFF(button1);
                button1.setEnabled(false);

                angleList.set(viewsList.indexOf(neueLage), 0);

                deleteButton.setImageResource(R.drawable.ic_delete_black_24dp);
                deleteButton.setEnabled(true);

                updateLaminataufbau();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button1 = neueLage.findViewById(R.id.button1);
                Button button2 = neueLage.findViewById(R.id.button2);
                Button button3 = neueLage.findViewById(R.id.button3);
                Button button4 = neueLage.findViewById(R.id.button4);
                Button button5 = neueLage.findViewById(R.id.button5);
                // Wenn Button=GRAU -> button blau machen
                if (button3.getCurrentTextColor() == getResources().getColor(R.color.colorGrey5)) {
                    buttonOFF(button2);
                    buttonON(button3);
                    buttonOFF(button4);
                    buttonOFF(button5);
                }
                if (lagenanzahl == viewsList.indexOf(neueLage) + 1) {
                    onAddField(editorlayout);
                }

                //buttonOFF(button1);
                button1.setEnabled(true);

                angleList.set(viewsList.indexOf(neueLage), 45);

                deleteButton.setImageResource(R.drawable.ic_delete_black_24dp);
                deleteButton.setEnabled(true);

                updateLaminataufbau();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button1 = neueLage.findViewById(R.id.button1);
                Button button2 = neueLage.findViewById(R.id.button2);
                Button button3 = neueLage.findViewById(R.id.button3);
                Button button4 = neueLage.findViewById(R.id.button4);
                Button button5 = neueLage.findViewById(R.id.button5);
                // Wenn Button=GRAU -> button blau machen
                if (button4.getCurrentTextColor() == getResources().getColor(R.color.colorGrey5)) {
                    buttonOFF(button2);
                    buttonOFF(button3);
                    buttonON(button4);
                    buttonOFF(button5);
                }
                if (lagenanzahl == viewsList.indexOf(neueLage) + 1) {
                    onAddField(editorlayout);
                }
                angleList.set(viewsList.indexOf(neueLage), 90);

                //buttonOFF(button1);
                button1.setEnabled(true);

                deleteButton.setImageResource(R.drawable.ic_delete_black_24dp);
                deleteButton.setEnabled(true);

                updateLaminataufbau();
            }

        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button5 = neueLage.findViewById(R.id.button5);

                Toast.makeText(getApplicationContext()  , "viewsList.indexOf: " + viewsList.indexOf(neueLage) +
                                                                "\n angleList.indexOf: " + angleList
                        , Toast.LENGTH_SHORT).show();
            }
        });


        // Textview mit der richtigen Lagennummer bezeichnen
        String lagewinkel = "Lage " + Integer.toString(lagenanzahl);
        textView.setText(lagewinkel);


        //ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        //scrollView.scrollTo(neueLage.getScrollX() , neueLage.getScrollY());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteField(neueLage);
                updateLaminataufbau();
            }
        });

        editorlayout.addView(neueLage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        neueLage.startAnimation(animation);

    }

    public void onDeleteField(View v) {

        LinearLayout editorlayout = (LinearLayout) findViewById(R.id.editorlayout);

        for (int lagennummer = viewsList.indexOf(v) + 1; lagennummer <= lagenanzahl; lagennummer++) {
            TextView textView = viewsList.get(lagennummer - 1).findViewById(R.id.textView1);
            textView.setText("Lage " + (lagennummer - 1));
        }

        angleList.remove(viewsList.indexOf(v));
        viewsList.remove(v);
        editorlayout.removeView(v);

        lagenanzahl--;
    }

    public void buttonON(Button button) {
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(button.getContext(), R.color.colorBlue));
        button.setTextColor(getApplication().getResources().getColor(R.color.colorBlack));
    }

    public void buttonOFF(Button button) {
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(button.getContext(), R.color.colorGrey7));
        button.setTextColor(getApplication().getResources().getColor(R.color.colorGrey5));
    }

    //public String laminataufbau ;


    public void updateLaminataufbau(){

        String laminataufbau = "[";

        for (int i = 0; i < angleList.size()-1; i++){
            String strlagenwinkel = Integer.toString(angleList.get(i));
            laminataufbau = laminataufbau + strlagenwinkel + "|";
        }

        String laminataufbau2 = laminataufbau.substring(0, laminataufbau.length()-1);

        laminataufbau2 = laminataufbau2 + "]";

        TextView textView = (TextView) findViewById(R.id.textview_laminataufbau);
        textView.setText(laminataufbau2);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
