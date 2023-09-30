package es.ulpgc.eite.da.basicquizlab;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setTitle(R.string.cheat_title);
        Intent intent = getIntent();
        String answer = intent.getStringExtra(MainActivity.EXTRA_ANSWER);

    }


}