package com.task.simplelogin.realm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.task.simplelogin.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmDatabase extends AppCompatActivity {
    private Realm realm;
    private RealmResults<DataModel> result;
    private DataDisplayAdapter dataDisplayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_database);
        realm = Realm.getDefaultInstance();
        List<DataModel> dataModelList = new ArrayList<>();
        dataModelList.add(new DataModel(1, "Ram", "28", "Hyderabad"));
        dataModelList.add(new DataModel(2, "Sam", "27", "Bengaluru"));
        dataModelList.add(new DataModel(3, "Amer", "29", "Jamnagar"));
        dataModelList.add(new DataModel(4, "Kumar", "25", "West Godavari"));
        dataModelList.add(new DataModel(5, "Harish", "30", "Vijayawada"));
//        realm.commitTransaction();

        realm.executeTransaction(realm -> {
            for (int i = 0; i < dataModelList.size(); i++) {
                realm.insertOrUpdate(dataModelList.get(i));
            }
        });
        RecyclerView data_recycler = findViewById(R.id.data_recycler);
        data_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        result = realm.where(DataModel.class).findAll();
        System.out.println("users   " + result);
        dataDisplayAdapter = new DataDisplayAdapter(getApplicationContext(), result);
        data_recycler.setAdapter(dataDisplayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.name:
                result = realm.where(DataModel.class).sort("name", Sort.ASCENDING).findAll();
                break;
            case R.id.age:
                result = realm.where(DataModel.class).sort("age",Sort.ASCENDING).findAll();
                break;
            case R.id.city:
                result = realm.where(DataModel.class).sort("city",Sort.ASCENDING).findAll();
                break;
        }
        dataDisplayAdapter.updateData(result);
        dataDisplayAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}