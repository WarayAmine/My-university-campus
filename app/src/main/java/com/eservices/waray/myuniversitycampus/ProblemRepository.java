package com.eservices.waray.myuniversitycampus;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;

public class ProblemRepository {

    private ProblemDao problemDao;
    private LiveData<List<Problem>> allProblems;

    public ProblemRepository(Application application){
        ProblemRoomDatabase database = ProblemRoomDatabase.getDatabase(application);
        problemDao = database.problemDao();
        allProblems = problemDao.getAllProblems();
    }

    public LiveData<List<Problem>> getAllProblems(){
        return allProblems;
    }

    public void insertProblem(Problem problem){
        new insertAsyncTask(problemDao).execute(problem);
    }

    private static class insertAsyncTask extends AsyncTask<Problem, Void, Void> {

        private ProblemDao mAsyncTaskDao;

        insertAsyncTask(ProblemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Problem... params) {
            mAsyncTaskDao.insertProblem(params[0]);
            return null;
        }
    }

}
