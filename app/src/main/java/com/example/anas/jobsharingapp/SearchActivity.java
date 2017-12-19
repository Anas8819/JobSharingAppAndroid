package com.example.anas.jobsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SearchActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup = (RadioGroup) findViewById(R.id.radio);
                int id = radioGroup.getCheckedRadioButtonId();
                radioButton= (RadioButton) findViewById(id);
                String searchQuery = radioButton.getLayout().getText().toString().trim();
                if(id==2131427436)
                {
                    searchQuery="1";
                }else if(id==2131427437)
                {
                    searchQuery="2";
                }else if(id==2131427438)
                {
                    searchQuery="3";
                }else
                {
                    searchQuery="4";
                }
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                intent.putExtra("search",searchQuery);
                startActivity(intent);
            }
        });



    }
}
