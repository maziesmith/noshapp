package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MealListActivity extends AppCompatActivity {

    ListView lvMealList;
    Button btnAddMeal;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
//    String[] listItem = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        lvMealList = findViewById(R.id.lvMealList);
        btnAddMeal = findViewById(R.id.btnAddMeal);

//        listItem[0] = "Spaghetti bolognese";
//        listItem[1] = "Hamburger";
//        listItem[2] = "Pączki";

        listItem = new ArrayList<>();

        viewData();

        btnAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AddMealActivity.class);
                startActivity(intent);
            }
        });
    }

    private void viewData() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Cursor cursor = databaseAccess.viewMealListData();

        if(cursor.getCount()==0){
            Toast.makeText(this, "Brak danych", Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItem);
        lvMealList.setAdapter(adapter);
        databaseAccess.close();
    }


}
