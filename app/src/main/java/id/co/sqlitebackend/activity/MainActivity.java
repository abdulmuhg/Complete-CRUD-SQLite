package id.co.sqlitebackend.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import id.co.sqlitebackend.R;
import id.co.sqlitebackend.adapter.Adapters;
import id.co.sqlitebackend.helper.SQLiteDBHandler;
import id.co.sqlitebackend.model.Contact;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Contact> itemList = new ArrayList<>();
    Adapters adapter;
    SQLiteDBHandler SQLite = new SQLiteDBHandler(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite = new SQLiteDBHandler(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);

        listView = findViewById(R.id.list_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                startActivity(intent);
            }
        });
        adapter = new Adapters(MainActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = String.valueOf(itemList.get(position).get_id());
                final int idi = itemList.get(position).get_id();
                final String name = itemList.get(position).get_name();
                final String address = itemList.get(position).get_address();

                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLite.delete(idi);
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }

    public void getAllData(){
        List<Contact> row = SQLite.getAllContacts();

        for (int i = 0; i < row.size(); i++){
            String id = String.valueOf(row.get(i).get_id());
            String poster = row.get(i).get_name();
            String title = row.get(i).get_address();

            Contact contact = new Contact();
            contact.set_id(Integer.parseInt(id));
            contact.set_name(poster);
            contact.set_address(title);

            itemList.add(contact);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}