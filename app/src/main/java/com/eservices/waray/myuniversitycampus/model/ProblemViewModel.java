package com.eservices.waray.myuniversitycampus.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.eservices.waray.myuniversitycampus.ProblemRepository;
import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;

public class ProblemViewModel extends AndroidViewModel {

    private ProblemRepository problemRepository;
    private LiveData<List<Problem>> allProblems;

    public ProblemViewModel(Application application){
        super(application);
        problemRepository = new ProblemRepository(application);
        allProblems = problemRepository.getAllProblems();
    }

    public Problem getProblemById(int id){
        return problemRepository.getProblemById(id);
    }

    public LiveData<Problem> getLiveDataProblem(int id){
        return problemRepository.getLiveDataProblem(id);
    }

    public LiveData<List<Problem>> getAllProblems(){
        return allProblems;
    }

    public void insertProblem(Problem problem){
        problemRepository.insertProblem(problem);
    }

    public void deleteProblemById(int id){
        problemRepository.deleteProblem(id);
    }
}
