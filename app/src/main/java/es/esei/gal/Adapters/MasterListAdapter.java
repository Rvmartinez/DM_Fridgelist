package es.esei.gal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.esei.gal.Helpers.FridgeListDB;
import es.esei.gal.Models.MasterListModel;
import es.esei.gal.R;
import es.esei.gal.Views.MainActivity;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListViewHolder> {
    private Context context;
    FridgeListDB db;
    ArrayList<MasterListModel> masterList;
    public MasterListAdapter(Context context,ArrayList<MasterListModel> masterList) {
        this.context = context;
        this.masterList = masterList;
    }

    private void Init() {
        db = new FridgeListDB(context);
        this.masterList = db.getAllFridgeLists();
    }

    @NonNull
    @Override
    public MasterListAdapter.MasterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shoplist_card,parent,false);
        return new MasterListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MasterListAdapter.MasterListViewHolder holder, int position) {
        holder.listName.setText(masterList.get(position).getName().toString());
        holder.category.setText(masterList.get(position).getCategory().toString());

    }

    @Override
    public int getItemCount() {
        return masterList.size();
    }


    public class MasterListViewHolder extends RecyclerView.ViewHolder {
        TextView listName,category;
        int position;
        public MasterListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("FridgeListId",getLayoutPosition());
                    view.getContext().startActivity(intent);
                }
            });
            listName = itemView.findViewById(R.id.masterListNameTV);
            category = itemView.findViewById(R.id.masterListCategoryTV);
        }
    }
}
