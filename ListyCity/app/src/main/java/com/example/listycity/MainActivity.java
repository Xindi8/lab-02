package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText editCity;

    String selectedCity = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addButton = findViewById(R.id.addButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        String []cities = {"Edmonton", "Vancouver", "moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(MainActivity.this,
                    "Selected: " + selectedCity,
                    Toast.LENGTH_SHORT).show();
        });

        // add new city
        addButton.setOnClickListener(v -> {
            String newCity = editCity.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                editCity.setText("");
            } else {
                Toast.makeText(MainActivity.this,
                        "Please enter a city name",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //delete city function
        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;
            } else {
                Toast.makeText(MainActivity.this,
                        "No city selected",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}