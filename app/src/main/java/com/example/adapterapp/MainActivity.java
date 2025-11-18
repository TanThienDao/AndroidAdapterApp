package com.example.adapterapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ============================================================
        // UNDERSTANDING THE ADAPTER AS A "LOAD BALANCER"
        // ============================================================
        
        // 1 - AdapterView (ListView): The client requesting views
        //     Like clients sending requests to a load balancer
        listView = findViewById(R.id.listView);

        // 2 - Data Source: The data to be displayed
        //     Like the backend servers holding data
        String [] countries = {"USA", "Germany", "Saudi Arabia","France"};

        // 3 - Adapter: THE LOAD BALANCER!
        //     Acts as a bridge between the data source and the AdapterView
        //     Just like a load balancer sits between clients and servers
        //
        //     What the Adapter does (Load Balancing):
        //     ✓ Distributes data to views efficiently
        //     ✓ Recycles views that scroll off-screen (resource pooling)
        //     ✓ Caches view references (ViewHolder pattern)
        //     ✓ Prevents memory overload by limiting active views
        //
        //     Standard ArrayAdapter (commented below) does this automatically
        //     MyCustomeAdaptor shows HOW it works internally
       /* ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );*/

        MyCustomeAdaptor adapter = new MyCustomeAdaptor(this ,countries);

        // Link ListView with the Adapter (connect client to load balancer)
        listView.setAdapter(adapter);




    }
}