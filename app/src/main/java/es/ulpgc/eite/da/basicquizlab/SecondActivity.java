package es.ulpgc.eite.da.basicquizlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    static final String LOG_TAG = SecondActivity.class.getSimpleName();
    private boolean buttonClicked;
    private Button noButton, yesButton;
    private TextView cheatText;
    private int Answer;
    private Boolean passToNextQuestion = false;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setTitle(R.string.cheat_title);
        startIntent();
        linkLayoutComponents();
    }


    private void linkLayoutComponents() {
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        cheatText = findViewById(R.id.cheatText);
    }

    private void startIntent() {
        intent = getIntent();
        Answer = intent.getIntExtra(MainActivity.EXTRA_ANSWER, 100);
    }

    public void onYesButtonClicked(View v) {
        if (buttonClicked) {
            return;
        }
        if (Answer == 0) {
            cheatText.setText(R.string.false_button_text);
            cheatText.setVisibility(View.VISIBLE);
        }
        if (Answer == 1) {
            cheatText.setText(R.string.true_button_text);
            cheatText.setVisibility(View.VISIBLE);
        }
        buttonClicked = true;
    }

    public void onNoButtonClicked(View v) {
        if (buttonClicked) {
            return;
        }
        buttonClicked = true;
    }

    public void onBackButtonClicked(View v) {
        finish();
    }
}