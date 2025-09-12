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

    EditText editCity;  // Declare EditText to get user input

    String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        cityList = findViewById(R.id.city_list);
        editCity = findViewById(R.id.editCity);  // Initialize EditText
        Button addButton = findViewById(R.id.addButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        // Sample city list
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Create an ArrayAdapter for the ListView
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // City selection
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });

        // Add new city functionality
        addButton.setOnClickListener(v -> {
            String newCity = editCity.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);  // Add new city to the list
                cityAdapter.notifyDataSetChanged();  // Notify adapter to update ListView
                editCity.setText("");  // Clear input field
            } else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete selected city functionality
        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);  // Remove selected city
                cityAdapter.notifyDataSetChanged();  // Notify adapter to update ListView
                selectedCity = null;  // Clear selected city
            } else {
                Toast.makeText(MainActivity.this, "No city selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}