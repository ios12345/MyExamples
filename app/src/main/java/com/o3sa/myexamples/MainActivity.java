package com.o3sa.myexamples;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.o3sa.myexamples.databinding.ActivityMainBinding;
import com.o3sa.myexamples.retrofitapi.APIInterface;
import com.o3sa.myexamples.retrofitapi.RetrofitInstance;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final int NEW_NOTE_ACTIVITY_STATUS = 1;
    ActivityMainBinding activityMainBinding;

    private Observable<String> observable;
    private Observer<String> observer;

    String hellotxt="Hello from Rx java";

    NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        observable=Observable.just(hellotxt);
        observer=new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.safeSubscribe(observer);

        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);

        activityMainBinding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(intent,NEW_NOTE_ACTIVITY_STATUS);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_NOTE_ACTIVITY_STATUS&&resultCode==RESULT_OK){

            final String note_id= UUID.randomUUID().toString();
            Note note=new Note(note_id,data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            noteViewModel.insert(note);
            Toast.makeText(MainActivity.this,"data saved",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"cancelled",Toast.LENGTH_LONG).show();

        }
    }

    private void callingservice() {

        APIInterface apiInterface= RetrofitInstance.retrofit.create(APIInterface.class);

        apiInterface.getorders("1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body() != null) {
                    try {
                        String responseReceived = response.body().string();
                        JSONObject jsonObject=new JSONObject(responseReceived);
                        String status=jsonObject.getString("status");
                        if(status.equalsIgnoreCase("200")){
                            JSONArray jsonArray=new JSONArray(jsonObject.getString("results"));

                            for(int k=0;k<jsonArray.length();k++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(k);
                                String order_status=jsonObject1.getString("order_status");
                                Log.i("val::","val::"+order_status);
                            }
                        }




                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
