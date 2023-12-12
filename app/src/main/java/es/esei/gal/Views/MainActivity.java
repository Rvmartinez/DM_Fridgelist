package es.esei.gal.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import es.esei.gal.Adapters.FridgeLinesAdapter;
import es.esei.gal.Helpers.FridgeListDB;
import es.esei.gal.Models.FridgeListLinesModel;
import es.esei.gal.R;

public class MainActivity extends AppCompatActivity {
    EditText barcodeET,quantityET;
    Button addBtn,viewBtn;
    RecyclerView rv;
    FridgeLinesAdapter linesAdapter;
    int currentList;
    FridgeListDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new FridgeListDB(this);
        barcodeET = findViewById(R.id.barcodeET);
        quantityET = findViewById(R.id.quantityET);
        addBtn = findViewById(R.id.addBtn);
        rv = findViewById(R.id.fridgeListRV);
        currentList = getIntent().getIntExtra("FridgeListId",0);
        ArrayList<FridgeListLinesModel> lines =  db.getFridgeLines(currentList);
        linesAdapter = new FridgeLinesAdapter(this,lines);
        rv.setAdapter(linesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity,barcode = 0;
                try {
                    quantity = Integer.parseInt(quantityET.getText().toString());
                    barcode = Integer.parseInt(barcodeET.getText().toString());

                }catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"Error de formato", Toast.LENGTH_SHORT).show();
                    quantityET.requestFocus();
                    return;
                }
                if(quantity > 0 && barcode > 0)
                {
                    int position = db.insertFridgeLine(currentList,barcode,"TODO",quantity);
                    if(position != -1){
                        linesAdapter.setFridgeLines(db.getFridgeLines(currentList));
                        linesAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, barcode+" se ha añadido a la lista "+currentList, Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });
    }
}