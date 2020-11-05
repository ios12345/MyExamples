package com.o3sa.myexamples;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.o3sa.myexamples.databinding.ActivityMainBinding;

public class NewNoteActivity extends AppCompatActivity  {


    EditText edtName;

    public static String NOTE_ADDED="note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

       Button addbutton=findViewById(R.id.addbutton);
         edtName=findViewById(R.id.edt_name);


       addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent=new Intent();
                if(TextUtils.isEmpty(edtName.getText())){

                    setResult(RESULT_CANCELED,resultIntent);
                }else{

                    String note=edtName.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED,note);
                    setResult(RESULT_OK,resultIntent);

                }

                finish();
            }
        });

    }

}
