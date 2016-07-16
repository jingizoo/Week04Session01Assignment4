package com.jalaj.firstapp.autosearchproject;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.jalaj.firstapp.autosearchproject.adapter.DatabaseDisplayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    SearchDBHelper searchDBHelper;
    AutoCompleteTextView saSearchBar;
    ArrayList<String> arrayList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saSearchBar = (AutoCompleteTextView)findViewById(R.id.saSearchBox);
        saSearchBar.addTextChangedListener(this);
         searchDBHelper = new SearchDBHelper(this);
       // searchDBHelper.buildDatabase();
        listView = (ListView) findViewById(R.id.listView);
        String [] results =  searchDBHelper.getProductFullName("");
arrayList = new ArrayList<String>();
        for(int i = 0; i < results.length; i++)
        {
            arrayList.add(i,results[i]);

            Log.i(this.toString(), results[i]);
        }
        ArrayAdapter <String> arrayAdapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results);
       arrayAdapter.setNotifyOnChange(true);
        saSearchBar.setThreshold(1);
        saSearchBar.setAdapter(arrayAdapter);

        DatabaseDisplayAdapter databaseDisplayAdapter = new DatabaseDisplayAdapter(this,arrayList);
        listView.setAdapter(databaseDisplayAdapter);
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        Log.i(s.toString(),start+" "+count);

    }
    public void onDestroy()
    {
        super.onDestroy();
        searchDBHelper.close();
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
