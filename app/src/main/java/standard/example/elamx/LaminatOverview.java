package standard.example.elamx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LaminatOverview extends AppCompatActivity {

    Button button1, button2, button3, button4;
    CardView cardView1, cardView2, cardView3;
    private TextView tx1, tx2, tx3, tx4, tx5, tx6, tx_versagen, tx_dehnung, tx_spannung, tx_einklappen, tx_klappe;
    private int save ;

    private LinearLayout parentlayout; //Parentlayout in dem Felder hinzugefügt werden


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laminat_overview);

        parentlayout = (LinearLayout) findViewById(R.id.parent_layout); // Parent Layout wird der ID zugewiesen

        button1 = (Button) findViewById(R.id.lage1button1);
        button2 = (Button) findViewById(R.id.lage1button2);
        button3 = (Button) findViewById(R.id.lage1button3);
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

        cardView1 = (CardView) findViewById(R.id.cardview1);
        cardView2 = (CardView) findViewById(R.id.cardview2);
        cardView3 = (CardView) findViewById(R.id.cardview3);

        tx1 = (TextView) findViewById(R.id.tv1);
        tx2 = (TextView) findViewById(R.id.tv2);
        tx3 = (TextView) findViewById(R.id.tv3);
        tx4 = (TextView) findViewById(R.id.tv4);
        tx5 = (TextView) findViewById(R.id.tv5);
        tx6 = (TextView) findViewById(R.id.tv6);
        tx_dehnung = (TextView) findViewById(R.id.textview_dehnungen);
        tx_spannung = (TextView) findViewById(R.id.textview_spannungen);
        tx_versagen = (TextView) findViewById(R.id.textview_versagen);

        int table_status = 1;// Tabelle auf Status 1 anzeigen bei öffnen der Activity
        changeTable(1);

        tx_versagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTable(1);
            }
        });

        tx_spannung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTable(2);
            }
        });

        tx_dehnung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeTable(3);
            }
        });

        // Ab hier, Ein- Ausklappen Test

        tx_einklappen = (TextView) findViewById(R.id.tx_einklappen);
        tx_klappe = (TextView) findViewById(R.id.tx_klappe);
        save = 0;

        tx_einklappen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(save == 0){
                    tx_klappe.setText("\nZeile 2 \nZeile 3 \nZeile 4");
                    tx_einklappen.setText("Einklappen");
                    save = 1;
                }
                else{
                    tx_klappe.setText("");
                    tx_einklappen.setText("Ausklappen");
                    save = 0;
                }

            }
        });

    }

    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowview = inflater.inflate(R.layout.field2, null);
        //Add the new row before the add field button
        parentlayout.addView(rowview);

    }

    private void changeTable(int table_status){
        if (table_status == 1){
            tx_versagen.setTextColor(getResources().getColor(R.color.colorBlack));
            tx_spannung.setTextColor(getResources().getColor(R.color.colorGrey5));
            tx_dehnung.setTextColor(getResources().getColor(R.color.colorGrey5));

            cardView1.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            cardView2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            cardView3.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        }
        if (table_status == 2){
            tx_versagen.setTextColor(getResources().getColor(R.color.colorGrey5));
            tx_spannung.setTextColor(getResources().getColor(R.color.colorBlack));
            tx_dehnung.setTextColor(getResources().getColor(R.color.colorGrey5));

            cardView1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            cardView2.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            cardView3.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        }
        if (table_status == 3){
            tx_versagen.setTextColor(getResources().getColor(R.color.colorGrey5));
            tx_spannung.setTextColor(getResources().getColor(R.color.colorGrey5));
            tx_dehnung.setTextColor(getResources().getColor(R.color.colorBlack));

            cardView1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            cardView2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            cardView3.setBackgroundColor(getResources().getColor(R.color.colorBlue));

        }

    }


}
