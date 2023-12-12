package es.esei.gal.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import es.esei.gal.Adapters.MasterListAdapter;
import es.esei.gal.Helpers.FridgeListDB;
import es.esei.gal.Models.MasterListModel;
import es.esei.gal.R;

public class MainList extends AppCompatActivity {

    ArrayList<MasterListModel> list;
    RecyclerView rv;
    int currentList;
    FridgeListDB db;
    MasterListAdapter masterListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        db = new FridgeListDB(this);
        rv = findViewById(R.id.mainListRV);
        try{
            list = db.getAllFridgeLists();

        }catch(Exception e)
        {
            String s = e.getMessage();
        }
        masterListAdapter = new MasterListAdapter(this,list);
        rv.setAdapter(masterListAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}