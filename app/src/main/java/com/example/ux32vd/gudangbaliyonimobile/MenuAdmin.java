package com.example.ux32vd.gudangbaliyonimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdmin extends AppCompatActivity{

    Button buttonpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        buttonpos = (Button)findViewById(R.id.btn_pos);

        buttonpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAdmin.this, MenuPos.class));
            }
        });
    }
}
