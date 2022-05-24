package com.example.check_math;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextToSpeech ttobj;
    EditText ed_val1, ed_val2, ed_ans;
    TextView tv_message;
    Button btn_rand, btn_anscheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        btn_anscheck=(Button)findViewById(R.id.btn_anscheck);
        btn_rand=(Button)findViewById(R.id.btn_rand);
        ed_val1 = (EditText) findViewById(R.id.ed_val1);
        ed_val2 = (EditText) findViewById(R.id.ed_val2);

        ttobj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        // ** Set's random numbers!! **
        btn_rand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Random r = new Random();
                int randomNumber = r.nextInt(149);
                int randomNumber1 = r.nextInt(150);
                ed_val1.setText(String.valueOf(randomNumber));
                ed_val2.setText(String.valueOf(randomNumber1));

                ttobj.speak(ed_val1.getText().toString() + "plus " + ed_val2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

                                        }
                                    });
        ttobj.setLanguage(Locale.US);

    // ** checks the answer!!!! **
    btn_anscheck.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int ans, val1, val2;
            EditText ed_ans = findViewById(R.id.ed_ans);
            TextView message = findViewById(R.id.tv_message);

            val1 = Integer.parseInt(ed_val1.getText().toString());
            val2 = Integer.parseInt(ed_val2.getText().toString());
            ans = val1 + val2;
            if (ans == val1+val2) {
                message.setText("Correct! Great Job!!");

            } else {
                message.setText("Incorrect Answer! Try Again!!");

            }


            ttobj.speak(ed_ans.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

        }
        });


        }
}




