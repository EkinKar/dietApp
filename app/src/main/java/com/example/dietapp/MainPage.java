package com.example.dietapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

    }

    public void getBmiPage(View v) {
        startActivity(new Intent(MainPage.this, CalculateBmi.class));

    }

    public void getDietListPage(View v) {
        startActivity(new Intent(MainPage.this, DietLists.class));

    }

    public void createDietListPage(View v) {
        startActivity(new Intent(MainPage.this, AllFoods.class));

    }

    public void getBlogsPage(View v) {
        startActivity(new Intent(MainPage.this, Blogs.class));

    }

}
