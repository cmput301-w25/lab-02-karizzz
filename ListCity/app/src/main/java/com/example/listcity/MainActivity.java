package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedCityIndex = -1; // Keeps track of the selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        
        EditText cityInput = findViewById(R.id.city_input);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);

        // Add city functionality
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged(); // Update the ListView
                    cityInput.setText(""); // Clear the input field
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Track selected city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityIndex = position;
            Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });

        // Delete city functionality
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCityIndex >= 0 && selectedCityIndex < dataList.size()) {
                    dataList.remove(selectedCityIndex);
                    cityAdapter.notifyDataSetChanged(); // Update the ListView
                    selectedCityIndex = -1; // Reset selection
                } else {
                    Toast.makeText(MainActivity.this, "Select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
