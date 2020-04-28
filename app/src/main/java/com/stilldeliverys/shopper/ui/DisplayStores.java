package com.stilldeliverys.shopper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.stilldeliverys.shopper.R;
import com.stilldeliverys.shopper.core.BaseActivity;

public class DisplayStores extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stores);
        self=this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_search_menu_login, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
