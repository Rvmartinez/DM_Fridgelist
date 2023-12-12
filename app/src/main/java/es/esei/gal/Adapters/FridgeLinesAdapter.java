package es.esei.gal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.esei.gal.Models.FridgeListLinesModel;
import es.esei.gal.R;

public class FridgeLinesAdapter extends RecyclerView.Adapter<FridgeLinesAdapter.FridgeLinesViewHolder> {
    ArrayList<FridgeListLinesModel> fridgeLines;
    Context context;
    public FridgeLinesAdapter(Context context, ArrayList<FridgeListLinesModel> fridgeLines) {
        this.context = context;
        this.fridgeLines = fridgeLines;
    }

    public void setFridgeLines(ArrayList<FridgeListLinesModel> fridgeLines) {
        this.fridgeLines = fridgeLines;
    }

    @NonNull
    @Override
    public FridgeLinesAdapter.FridgeLinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shop_item,parent,false);
        return new FridgeLinesAdapter.FridgeLinesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeLinesAdapter.FridgeLinesViewHolder holder, int position) {
        holder.FormatTV.setText(fridgeLines.get(position).getWeighted()  ? "Kg":"Uds");
        holder.itemNameTV.setText(fridgeLines.get(position).getItemName());
        holder.qtyTV.setText(String.valueOf(fridgeLines.get(position).getQty()));
    }

    @Override
    public int getItemCount() {
        return fridgeLines.size();
    }

    public class FridgeLinesViewHolder extends RecyclerView.ViewHolder{
        TextView itemNameTV,FormatTV,qtyTV;
        public FridgeLinesViewHolder(@NonNull View itemView) {
            super(itemView);
            FormatTV = itemView.findViewById(R.id.shopItemFormatTV);
            qtyTV = itemView.findViewById(R.id.shopItemQuantityTV);
            itemNameTV = itemView.findViewById(R.id.shopItemNameTV);
        }
    }
}
