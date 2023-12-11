package es.esei.gal.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.esei.gal.Models.ItemModel;
import es.esei.gal.R;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>  {
    private Context context;
    private ArrayList<ItemModel> list;

    public ShopListAdapter(Context context, ArrayList<ItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShopListAdapter.ShopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_main_list,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListAdapter.ShopListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShopListViewHolder  extends  RecyclerView.ViewHolder{
        TextView itemName, quantity, format;
        public ShopListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.shopItemNameTV);
            quantity = itemView.findViewById(R.id.shopItemQuantityTV);
            format = itemView.findViewById(R.id.shopItemFormatTV);
        }
    }
}
