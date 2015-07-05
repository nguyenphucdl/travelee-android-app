package com.example.nguyenphucuit.actionbarhelloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements TextView.OnEditorActionListener {

    private final String TAG = "MainActivity";

    private MenuItem myActionMenuItem;
    private EditText myActionEditEtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_bar, menu);

        // Here we get the action view we defined
        myActionMenuItem = menu.findItem(R.id.my_action);
        View actionView = myActionMenuItem.getActionView();


        // We then get the edit text view that is part of the action view
        if(actionView != null) {
            myActionEditEtext = (EditText) actionView.findViewById(R.id.myActionEditText);
            if(myActionEditEtext != null) {
                // We set a listener that will be called when the return/enter key is pressed
                Log.d(TAG, "[onCreateOptionsMenu] actionEditText setOnEditorActionListener!");
                myActionEditEtext.setOnEditorActionListener(this);
            }
        }
        else
        {
            Log.e(TAG, "[onCreateOptionsMenu] actionView is null!");
        }

        // For support of API level 14 and below, we use MenuItemCompat
        MenuItemCompat.setOnActionExpandListener(myActionMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                // Do something when collapsed
                Log.i(TAG, "[onMenuItemActionExpand] entering...!");
                return true; // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                // Do something when expanded
                if (myActionEditEtext != null) {
                    Log.i(TAG, "[onMenuItemActionCollapse] clear text!");
                    myActionEditEtext.setText("");
                }
                return true; // Return true to expand action view
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Log.i("Main Activity", "[OnTouchEvent] Action Down");
            toggleActionBar();
        }
        return true;
    }

    private void toggleActionBar() {
        //ActionBar actionBar = getActionBar();
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            if(actionBar.isShowing()) {
                Log.d(TAG, "[toggleActionBar] hide");
                actionBar.hide();
            }
            else {
                Log.d(TAG, "[toggleActionBar] show");
                actionBar.show();
            }
        }
        else
        {
            Log.e(TAG, "[toggleActionBar] actionBar is null!");
        }
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                // Code you want run when activity is clicked
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_record:
                Toast.makeText(this, "Record clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_save:
                Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_label:
                Toast.makeText(this, "Label clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_play:
                Toast.makeText(this, "Play clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Log.i(TAG, "[onEditorAction] entering...");
        if(keyEvent != null) {
            // When the return key is pressed, we get the text the user entered, display it and collapse the view
            if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER ) {
                CharSequence textInput = textView.getText();

                // Do something useful with the text
                Toast.makeText(this, textInput, Toast.LENGTH_SHORT).show();
                MenuItemCompat.collapseActionView(myActionMenuItem);
            }
        }
        return false;
    }
}
