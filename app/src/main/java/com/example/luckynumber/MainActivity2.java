package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView1= findViewById(R.id.textView2);
        textView2= findViewById(R.id.textView3);
        button= findViewById(R.id.btn1);
        Intent i = getIntent();
        String userName= i.getStringExtra("name");

        int random_num = generateRandomNumber();
        textView2.setText(" "+random_num);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,random_num);
            }
        });
    }

    private void shareData(String userName, int randomNum) {
        Intent i =new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,userName+" got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT,"His luck number is "+randomNum);
        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit =1000;
        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }
}