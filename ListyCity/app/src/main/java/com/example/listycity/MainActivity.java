package com.example.listycity;

import android.os.Bundle;
import android.view.View;
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

    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;
    private ListView cityListView;
    private EditText editCityName;
    private Button btnAdd, btnDelete, btnConfirm;

    private int selectedPosition = -1; // Store the selected city's position
//
     //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize references
        cityListView = findViewById(R.id.city_list);
        editCityName = findViewById(R.id.editCity);
        btnAdd = findViewById(R.id.addButton);
        btnDelete = findViewById(R.id.deleteButton);
        btnConfirm = findViewById(R.id.confirmButton);

        // Data source (initial list of cities)
        String[] cities = {"Edmonton", "Calgary", "Vancouver", "Toronto"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Adapter for the ListView
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityListView.setAdapter(cityAdapter);

        // Handle item selection in ListView
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Toast.makeText(MainActivity.this,
                    "Selected: " + dataList.get(position),
                    Toast.LENGTH_SHORT).show();
        });

        // Show the EditText and Confirm Button when Add City is pressed
        btnAdd.setOnClickListener(v -> {
            editCityName.setVisibility(View.VISIBLE);  // Show the EditText
            btnConfirm.setVisibility(View.VISIBLE);    // Show the Confirm button
        });

        // Confirm Button (Pressed after typing a name)
        btnConfirm.setOnClickListener(v -> {
            String newCity = editCityName.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);  // Add the city
                cityAdapter.notifyDataSetChanged();  // Update ListView
                editCityName.setText("");  // Clear input field
                editCityName.setVisibility(View.GONE); // Hide EditText
                btnConfirm.setVisibility(View.GONE);  // Hide Confirm button
            } else {
                Toast.makeText(MainActivity.this, "Enter a valid city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete City Button (Pressed after selecting a city)
        btnDelete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition); // Remove selected city
                cityAdapter.notifyDataSetChanged(); // Update ListView
                selectedPosition = -1; // Reset selection
            } else {
                Toast.makeText(MainActivity.this, "Select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
