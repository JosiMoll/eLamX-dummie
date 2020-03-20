package standard.example.elamx;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class QuickEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_editor);


        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materialien, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);

        // Toolbar als ActionBar einfügen
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
        mTitle.setText("Quick Editor");
        // Icons der Toolbar ändern
        myToolbar.setOverflowIcon(getDrawable(R.drawable.ic_menu_black_24dp));
        myToolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        // Edittext soll Focus verlieren bei click irgendwo

        final EditText editText = (EditText) findViewById(R.id.edittext);
        LinearLayout focuslayout = (LinearLayout) findViewById(R.id.focuslayout);

        focuslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.clearFocus();
            }
        });

    }

    // Benötigt zum Anzeigen vom Options-Menu in der Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}






