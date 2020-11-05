package com.o3sa.myexamples;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NoteViewModel extends AndroidViewModel {


    public String TAG=this.getClass().getSimpleName();
    NoteRoomDatabase noteRoomDatabase;
    NoteDao noteDao;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRoomDatabase=NoteRoomDatabase.getDatabase(application);
        noteDao=noteRoomDatabase.noteDao();
    }

    @Override
    protected void onCleared() {

        Log.i(TAG,"View Model Destroyed");
        super.onCleared();
    }
    public void insert(Note note){
        new InsertAyncTask(noteDao).execute(note);

    }

    private class InsertAyncTask extends AsyncTask<Note,Void,Void>{


        NoteDao mnoteDao;

        public  InsertAyncTask(NoteDao mnoteDao){
            this.mnoteDao=mnoteDao;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            mnoteDao.insert(notes[0]);
            return null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
