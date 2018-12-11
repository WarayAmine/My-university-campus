package com.eservices.waray.myuniversitycampus.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.eservices.waray.myuniversitycampus.database.ProblemRepository;
import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;


public class ProblemViewModel extends AndroidViewModel {

    private ProblemRepository problemRepository;

    public ProblemViewModel(Application application){
        super(application);
        problemRepository = new ProblemRepository(application);
    }

    public Problem getProblemById(int id){
        return problemRepository.getProblemById(id);
    }

    public LiveData<Problem> getLiveDataProblem(int id){
        return problemRepository.getLiveDataProblem(id);
    }

    public LiveData<List<Problem>> getAllUnsolvedProblems(){
        return problemRepository.getAllUnsolvedProblems();
    }

    public void insertProblem(Problem problem){
        problemRepository.insertProblem(problem);
    }

    public void deleteProblemById(int id){
        problemRepository.deleteProblem(id);
    }

    public void solveProblem(int id){
        problemRepository.solveProblem(id);
    }

    public void deleteAllProblems(){
        problemRepository.deleteAll();
    }
}
