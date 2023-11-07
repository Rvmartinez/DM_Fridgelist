package es.esei.gal.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.esei.gal.Helpers.ShoppingListDB;
import es.esei.gal.R;

public class MainActivity extends AppCompatActivity {
    EditText barcodeET,quantityET;
    Button addBtn,viewBtn;
    int currentList;
    ShoppingListDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new ShoppingListDB(this);
        barcodeET = findViewById(R.id.barcodeET);
        quantityET = findViewById(R.id.quantityET);
        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        currentList = db.insertShoppingList("prueba");
        if( currentList == -1)
            Toast.makeText(this,"Error insertando shopping list",Toast.LENGTH_LONG);
        else
            Toast.makeText(this,"Insertanda shopping list",Toast.LENGTH_LONG);


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainList.class));
            }
        });

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
                    if(db.insertShoppingLine(currentList,barcode,"TODO",quantity,0))
                        Toast.makeText(MainActivity.this, barcode+" se ha añadido a la lista "+currentList, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}