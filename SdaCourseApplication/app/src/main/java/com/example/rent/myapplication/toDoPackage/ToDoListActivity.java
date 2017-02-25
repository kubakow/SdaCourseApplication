package com.example.rent.myapplication.toDoPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.myapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChange{
    private ActionMode actionMode;
    private ToDoListAdapter toDoListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        toDoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter(toDoListAdapter );
        final EditText editText = (EditText) findViewById(R.id.to_do_edittext);
        toDoListAdapter.setCheckListener(this);
        Button addButton = (Button) findViewById(R.id.to_do_add_button);;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoListAdapter.addItem(editText.getText().toString());
                editText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.to_do_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.delete_to_do: {
//                toDoListAdapter.deleteCheckedItems();
//                break;
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onItemCheckStateChange(int checkedItemsCount) {
        if(checkedItemsCount>0) {
            createActionModeBar();
            actionMode.setTitle("Check items: " + checkedItemsCount);
        }else{ getSupportActionBar().setTitle("To do list");
            if(actionMode!=null)
                actionMode.finish();}
        }

    private void createActionModeBar() {
        actionMode = startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.to_do_action,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch(item.getItemId()){
                    case R.id.delete_to_do_action_mode: {
                        toDoListAdapter.deleteCheckedItems();
                        return true;
                    }
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
            }
        });
    }
}
