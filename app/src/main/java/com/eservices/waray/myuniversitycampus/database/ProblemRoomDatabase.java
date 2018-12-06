package com.eservices.waray.myuniversitycampus.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.eservices.waray.myuniversitycampus.entity.Problem;

@Database(entities = {Problem.class}, version = 2, exportSchema = false)
public abstract class ProblemRoomDatabase extends RoomDatabase {

    public abstract ProblemDao problemDao();

    private static volatile ProblemRoomDatabase INSTANCE;

    static ProblemRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (ProblemRoomDatabase.class){
                if(INSTANCE==null){
                    //creating database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProblemRoomDatabase.class,
                            "problem_database")
                            //drop tables and data when upgrading database
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase database){
            super.onOpen(database);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ProblemDao problemDao;

        PopulateDbAsync(ProblemRoomDatabase database){
            problemDao = database.problemDao();
        }

        @Override
        protected Void doInBackground(final Void ...params){
//            problemDao.deleteAll();
            return null;
        }
    }


}
