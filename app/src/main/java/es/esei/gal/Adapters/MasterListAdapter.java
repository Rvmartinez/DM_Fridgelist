package es.esei.gal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.esei.gal.Models.MasterListModel;
import es.esei.gal.R;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListViewHolder> {
    private Context context;
    ArrayList<MasterListModel> masterList;
    public MasterListAdapter(Context context, ArrayList<MasterListModel> masterList) {
        this.context = context;
        this.masterList = masterList;
    }

    @NonNull
    @Override
    public MasterListAdapter.MasterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_main_list,parent,false);
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
        public MasterListViewHolder(@NonNull View itemView) {
            super(itemView);
            //listName = itemView.findViewById(R.id.masterListNameTV);
            category = itemView.findViewById(R.id.masterListCategoryTV);
        }
    }
}
