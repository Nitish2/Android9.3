package com.example.hp.context_menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list_View;
    Custom_Adapter customAdapter;
    int index = 0;

    //Initializing values or contact names in the array
    String[] name = {"Nitish", "Bhawani", "Mithun", "Zeenat", "Vishwas"};

    //Initializing values or phone numbers in the array
    String[] number = {"9898134567", "9378453678", "87303484458", "9999345678", "88834234570"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating and initializing list view object by id
        list_View = (ListView) findViewById(R.id.list_View);
        /* setAdapter () method sets a custom adapter as the source for all items that is to be
        displayed in the ListView
         */
        list_View.setAdapter(new Custom_Adapter(this, name, number));
        list_View.setOnItemClickListener(this);
        /*
        registerForContextMenu() method will register all items for a context menu by passing the
        ListView.
         */
        registerForContextMenu(list_View);
    }

    @Override
    // System call onCreateOptionsMenu() method when the user opens the menu for thefirst time
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    // creating onClick() method to perform longClick event on the menu item
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
    }

    @Override
    /*
     When the view receives a long-click event, then it calls onCreateContextMenu() method.
     This is where you define the menu items, usually by inflating a menu resource.
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Creating menu header
        menu.setHeaderTitle("Select Action");
        //Adding items into menu
        menu.add(0, v.getId(), 0, "Call"); //groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        try {
            // Through AdapterContextMenuInfo we can get the correct item in onContextItemSelected()
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.
                    getMenuInfo();

            index=info.position; //  info.position will give the index of selected item
            if(item.getTitle()=="Call" ){ //if statement when the item tittle equals to Call
                /**
                 * Creating an Intent object.
                 * Launching and Starting the CALL activity through intent.
                 */

                Intent callintent = new Intent(Intent.ACTION_DIAL);
                callintent.setData(Uri.parse("tel:"+number[index]));
                startActivity(callintent);
        }
            //else if statement when the item tittle equals to SMS
            else {


                if (item.getTitle()=="SMS" ) {
                    /**
                     * Creating an Intent object.
                     * Launching and Starting the SMS activity through intent.
                     */
                    Intent smsIntent = new Intent();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+ number[index])));
                    startActivity(smsIntent);
                } else {

                    return false;
                }
            }
            return true;
        } catch (Exception e) { // Catch Statement
            return true;
        }


    }


}