package com.example.hp.context_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hp on 21-08-2017.
 */

/* Array Adaptar returns a view for each object in a collection of data objects you provide,
and can be used with list-based user interface
 */
public class Custom_Adapter extends ArrayAdapter { //Creating a class and extend it with BaseAdapter
    //Declaring variables
    private Context context;
    private String[] name;
    private String[] number;

    public Custom_Adapter(Context context, String[] name, String[] number) { //Creating method
        super(context, R.layout.row_custom, name);
        //Giving reference to the objects
        this.name = name;
        this.number = number;
        this.context = context;
    }

    @Override
    // getView method will creates new View for every view that is added to our adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        //LayoutInflator is use to manipulate XML file into its corresponding View objects
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.row_custom, parent, false);

        // Sets the positions of name and phone number in list view
        String contactName = name[position];
        String contactNumber = number[position];

        // Creating and initializing Text view object by id
        TextView textView3 = (TextView) customView.findViewById(R.id.name);
        TextView textView4 = (TextView) customView.findViewById(R.id.number);

        // It displays the  name and phone number at specified position in data set.
        textView3.setText(contactName);
        textView4.setText(contactNumber);
        return customView;


    }
}
