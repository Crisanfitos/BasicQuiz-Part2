package es.ulpgc.eite.da.basicquizlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button falseButton, trueButton, cheatButton, nextButton;
    private TextView questionText, replyText;

    private String[] questionArray;
    private int questionIndex = 0;
    private int[] replyArray;
    private boolean nextButtonEnabled;
    private Intent intent;

    public static final String EXTRA_ANSWER =
            "com.example.android.basicquiz.extra.ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.question_title);

        initLayoutData();
        linkLayoutComponents();
        initLayoutContent();
        enableLayoutButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLayoutData();
        initLayoutContent();
        enableLayoutButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("passQuestion", false)) {
                    questionIndex++;
                }
            }
        }
    }

    private void initLayoutData() {
        questionArray = getResources().getStringArray(R.array.question_array);
        replyArray = getResources().getIntArray(R.array.reply_array);
    }

    private void linkLayoutComponents() {
        falseButton = findViewById(R.id.falseButton);
        trueButton = findViewById(R.id.trueButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);

        questionText = findViewById(R.id.questionText);
        replyText = findViewById(R.id.replyText);
    }

    private void initLayoutContent() {
        questionText.setText(questionArray[questionIndex]);
        replyText.setText(R.string.empty_text);
    }

    private void enableLayoutButtons() {

        trueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onTrueButtonClicked(v);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onFalseButtonClicked(v);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onNextButtonClicked(v);
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onCheatButtonClicked(v);
            }
        });
    }


    //TODO: impedir que podamos hacer click en el boton
    // si ya hemos contestado a la pregunta
    private void onTrueButtonClicked(View v) {

        if (nextButtonEnabled) {
            return;
        }

        if (replyArray[questionIndex] == 1) {
            replyText.setText(R.string.correct_text);
        } else {
            replyText.setText(R.string.incorrect_text);
        }

        nextButtonEnabled = true;
    }

    //TODO: impedir que podamos hacer click en el boton
    // si ya hemos contestado a la pregunta
    private void onFalseButtonClicked(View v) {

        if (nextButtonEnabled) {
            return;
        }

        if (replyArray[questionIndex] == 0) {
            replyText.setText(R.string.correct_text);
        } else {
            replyText.setText(R.string.incorrect_text);
        }

        nextButtonEnabled = true;
    }

    //TODO: implementar boton para pasar a siguiente pantalla
    private void onCheatButtonClicked(View v) {
        // no implementado
        intent = new Intent(this, SecondActivity.class);
        int Answer = replyArray[questionIndex];
        intent.putExtra(EXTRA_ANSWER, Answer);
        startActivityForResult(intent, 0);
    }

    //TODO: impedir que podamos hacer click en el boton
    // si aun no hemos contestado a la pregunta
    private void onNextButtonClicked(View v) {

        if (!nextButtonEnabled) {
            return;
        }

        nextButtonEnabled = false;
        questionIndex++;

        // si queremos que el quiz acabe al llegar
        // a la ultima pregunta debemos comentar esta linea
        checkIndexData();

        if (questionIndex < questionArray.length) {
            initLayoutContent();
        }
    }

    //TODO: refactorizar en un método este codigo
    // por si queremos implementar otras opciones posibles
    private void checkIndexData() {

        // hacemos que si llegamos al final del quiz
        // volvamos a empezarlo nuevamente
        if (questionIndex == questionArray.length) {
            questionIndex = 0;
        }
    }
}

