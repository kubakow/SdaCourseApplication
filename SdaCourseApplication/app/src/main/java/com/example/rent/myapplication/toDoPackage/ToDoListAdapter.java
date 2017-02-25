package com.example.rent.myapplication.toDoPackage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rent.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {



    private List<ListItem> items = new ArrayList<>();
    private OnItemCheckStateChange checkListener;

    public void setCheckListener(OnItemCheckStateChange checkListener){
        this.checkListener = checkListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ListItem listItem = items.get(position);
    holder.textView.setText(items.get(position).getText());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
                if(checkListener!=null){
                    checkListener.onItemCheckStateChange(getCheckedItemsCount());
                }
            }
        });
        holder.checkBox.setChecked(listItem.isChecked());
    }

    public int getCheckedItemsCount(){
        int counter = 0;
        for (ListItem item: items) {
            if(item.isChecked()){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView textView;
        public MyViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.single_to_do_item_list);
            checkBox = (CheckBox) itemView.findViewById(R.id.to_do_single_item_checkbox);
        }
    }
    public void deselectAllItems(){
        for(ListItem item : items){
            if(item.isChecked())
            item.setChecked(false);
        }
        notifyDataSetChanged();
    }
    public void addItem(String string){
        items.add(new ListItem(string));
        notifyDataSetChanged();
    }

    public void deleteCheckedItems(){
        for(int i =items.size()-1; i>=0; i--){
            if(items.get(i).isChecked()){
                items.remove(i);
            }
        }
        notifyDataSetChanged();
        if(checkListener!=null){
            checkListener.onItemCheckStateChange(getCheckedItemsCount());
        }
    }
    public List<ListItem> getItems(){
        return items;
    }
    public void setItems(List<ListItem> items){
        this.items = items;
        notifyDataSetChanged();
    }
}
