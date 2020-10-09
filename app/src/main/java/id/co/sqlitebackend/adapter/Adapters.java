package id.co.sqlitebackend.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.sqlitebackend.model.Contact;
import id.co.sqlitebackend.R;

public class Adapters extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Contact> items;

    public Adapters(Activity activity, List<Contact> items) {
        this.activity = activity;
        this.items = items;
    }

    public int getCount(){
        return items.size();
    }

    public Object getItem(int location){
        return items.get(location);
    }

    public long getItemId(int position){
        return position;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView id = view.findViewById(R.id.id);
        TextView name = view.findViewById(R.id.name);
        TextView address = view.findViewById(R.id.address);

        Contact contact = items.get(i);

        id.setText(""+contact.get_id());
        name.setText(contact.get_name());
        address.setText(contact.get_address());

        return view;
    }
}
