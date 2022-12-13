package com.example.inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText winner;
    private Button btn;
    private Boolean saved = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String NAME = "NAME";
    public static final String TEAM= "TEAM";
    public static final Boolean FLAG = true;

    @Override
    protected void onStop() {
        super.onStop();
        if(!saved){
            String name1 = name.getText().toString().trim();
            String team = winner.getText().toString().trim();
            if (!name1.isEmpty() && !team.isEmpty())
                editor.putString(NAME,name1);
                editor.putString(TEAM,team);
                editor.putBoolean("FLAG",FLAG);
                editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        winner =findViewById(R.id.winner);
        btn = findViewById(R.id.btnsave);
        setUpSharedPrefs();
        CheckData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saved = true;

            }
        });
    }

    private void setUpSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }
    public void btn(View view) {
    }

    private void CheckData (){
        boolean f = prefs.getBoolean("FLAG",false);
        if (f){
            String name2 =prefs.getString(NAME,"");
            String team2 = prefs.getString(TEAM,"");
            name.setText(name2);
            winner.setText(team2);
        }
    }
}