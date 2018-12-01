package com.eservices.waray.myuniversitycampus;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;

public class ProblemRepository {

    private ProblemDao problemDao;
    private LiveData<List<Problem>> allProblems;
    Problem problem;

    public ProblemRepository(Application application){
        ProblemRoomDatabase database = ProblemRoomDatabase.getDatabase(application);
        problemDao = database.problemDao();
        allProblems = problemDao.getAllProblems();
    }

    public Problem getProblemById(int id){
        new getProblemAsyncTask(problemDao).execute(id);
        return problem;
    }

    public LiveData<Problem> getLiveDataProblem(int id) {
        return problemDao.getLiveDataProblemById(id);
    }

    public LiveData<List<Problem>> getAllProblems(){
        return allProblems;
    }

    public void insertProblem(Problem problem){
        new insertAsyncTask(problemDao).execute(problem);
    }

    public void deleteProblem(int id){
        new deleteProblemByIdAsyncTask(problemDao).execute(id);
    }

    private class deleteProblemByIdAsyncTask extends AsyncTask<Integer, Void, Void>{

        private ProblemDao mAsyncTaskDao;

        deleteProblemByIdAsyncTask(ProblemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            mAsyncTaskDao.deleteProblemById(integers[0]);
            return null;
        }
    }

    private class getProblemAsyncTask extends AsyncTask<Integer, Void, Problem>{

        private ProblemDao mAsyncTaskDao;

        getProblemAsyncTask(ProblemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Problem doInBackground(Integer... integers) {
            return mAsyncTaskDao.getOneProblemById(integers[0]);
        }

        @Override
        protected void onPostExecute(Problem problem1){
            problem = problem1;
        }

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
