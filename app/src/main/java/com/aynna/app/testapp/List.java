package com.aynna.app.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List extends AppCompatActivity {

    String [] mobiles = {"Android", "Iphone", "WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        populate();

    }

    private void populate() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_components, mobiles);
        ListView listView = (ListView) findViewById(R.id.mobileList);
        listView.setAdapter(adapter);
    }

    public void logIn(View view){
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }
}
