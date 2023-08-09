package com.example.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterFace;
import com.example.myapplication.Restaurant;
import com.example.myapplication.RestaurantAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterFace {

    private Spinner spinner;
    private Button menu_move;
    private Button restaurant_move;
    private Button recommand_move;
    private Button event_move;
    private RecyclerView recyclerView;
    private ArrayList<Restaurant> restaurantArrayList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference fireStoreDatabase = db.collection("restaurant");
    private RestaurantAdapter restaurantAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // RecommandActivity로 이동
        recommand_move = findViewById(R.id.recommandButton);
        recommand_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recommand_intent = new Intent(MainActivity.this, RecommandActivity.class);
                startActivity(recommand_intent); // Activity 이동
            }
        });

        // spinner 사용

        spinner = (Spinner)findViewById(R.id.mainSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sort_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 선택되면
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            // 아무것도 선택되지 않았을 때
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Restaurant recyclerview 부분
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantArrayList = new ArrayList<Restaurant>(); // Restaurant 객체를 담을 리스트 (어뎁터 쪽으로)
        restaurantAdapter = new RestaurantAdapter(MainActivity.this, restaurantArrayList);

        recyclerView.setAdapter(restaurantAdapter);
        EventChangeListener();

    }

    private void EventChangeListener(){

        db.collection("restaurant").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;

                        }

                        for(DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                restaurantArrayList.add(dc.getDocument().toObject(Restaurant.class));

                            }

                            restaurantAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {

    }

    //menubar 부분

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                return true;
            case R.id.action_map:
                Intent intent_map = new Intent(this, MapActivity.class);
                startActivity(intent_map);
                return true;

            case R.id.action_user:
                Intent intent_user = new Intent(this, UserActivity.class);
                startActivity(intent_user);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}