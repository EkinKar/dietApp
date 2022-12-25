package com.example.dietapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllFoods extends AppCompatActivity {

    private ListView listViewFoods;
    private List<Food> foodList;
    private ArrayAdapter<Food> adapter;
    private final List<Food> selectedFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_foods);

        listViewFoods = findViewById(R.id.listViewFoods);
        foodList = new ArrayList<>();
        adapter = new ArrayAdapter<Food>(this, R.layout.food_item_layout, R.id.textViewFoodName, foodList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckBox checkBoxFood = view.findViewById(R.id.checkBoxFood);
                TextView textViewFoodName = view.findViewById(R.id.textViewFoodName);
                Food food = foodList.get(position);
                checkBoxFood.setChecked(food.isSelected());
                textViewFoodName.setText(food.getName());
                checkBoxFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        food.setSelected(isChecked);
                        if (isChecked) {
                            selectedFoods.add(food);
                        } else {
                            selectedFoods.remove(food);
                        }
                    }
                });
                return view;
            }
        };
        listViewFoods.setAdapter(adapter);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("foods");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Food food = snapshot.getValue(Food.class);
                    foodList.add(food);
                }
                adapter.notifyDataSetChanged();

                listViewFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Food food = foodList.get(i);
                        TextView textViewFoodName = view.findViewById(R.id.textViewFoodName);
                        textViewFoodName.setText(food.getName());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(userId).child("diets");
                    for (Food food : selectedFoods) {
                        ref.push().setValue(food);
                    }
                    Toast.makeText(AllFoods.this, "Selected foods saved to the diet list!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}