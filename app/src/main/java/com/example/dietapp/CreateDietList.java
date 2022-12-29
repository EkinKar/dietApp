package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.NumberPicker;
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
// Ekin
public class CreateDietList extends AppCompatActivity {

    private ListView listViewFoods;
    private List<Food> foodList;
    private ArrayAdapter<Food> adapter;
    private final List<Food> selectedFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet_list);

        listViewFoods = findViewById(R.id.listViewFoods);
        foodList = new ArrayList<>();
        adapter = new ArrayAdapter<Food>(this, R.layout.activity_food_item_layout, R.id.textViewFoodName, foodList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // function that lets us chose the amount of foods
                View view = super.getView(position, convertView, parent);

                CheckBox checkBoxFood = view.findViewById(R.id.checkBoxFood);
                TextView textViewFoodName = view.findViewById(R.id.textViewFoodName);
                NumberPicker numberPickerAmount = view.findViewById(R.id.numberPickerAmount);
                checkBoxFood.setTag(position);
                checkBoxFood.setOnCheckedChangeListener(null);
                numberPickerAmount.setTag(position);
                numberPickerAmount.setOnValueChangedListener(null);

                Food food = foodList.get(position);
                checkBoxFood.setChecked(food.isSelected());
                textViewFoodName.setText(food.getName());
                numberPickerAmount.setMinValue(1);
                numberPickerAmount.setMaxValue(5);
                numberPickerAmount.setValue(1);
                final int pos = (Integer) checkBoxFood.getTag();
                checkBoxFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        Food food = foodList.get(pos);
                        food.setSelected(isChecked);
                        if (isChecked) {
                            food.setTotalCalories();
                            selectedFoods.add(food);
                        } else {
                            selectedFoods.remove(food);
                        }
                    }
                });

                final int pos2 = (Integer) numberPickerAmount.getTag();
                numberPickerAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                        Food food = foodList.get(pos2);
                        food.setAmount(newValue);
                    }
                });


                return view;
            }
        };
        listViewFoods.setAdapter(adapter);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("foods");
        // function that shows the available foods
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
            // function that saves the selected foods to the db
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(userId).child("diets");
                    ref.setValue(selectedFoods, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(CreateDietList.this, "Foods saved to database.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateDietList.this, ShowDietPlan.class));
                            } else {
                                Toast.makeText(CreateDietList.this, "Error saving foods to database.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}