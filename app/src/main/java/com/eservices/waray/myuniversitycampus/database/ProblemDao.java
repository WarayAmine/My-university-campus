package com.eservices.waray.myuniversitycampus.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;

@Dao
public interface ProblemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProblem(Problem problem);

    @Update
    void updateProblem(Problem problem);

    @Query("SELECT * FROM Problem WHERE id = :problemId")
    Problem getOneProblemById (int problemId);

    @Query("SELECT * FROM Problem WHERE id = :problemId")
    LiveData<Problem> getLiveDataProblemById (int problemId);

    @Query("SELECT * FROM Problem WHERE isSolved = 0 ORDER BY date DESC")
    LiveData<List<Problem>> getAllUnsolvedProblems();

    @Query("DELETE FROM Problem")
    void deleteAll();

    @Query("DELETE FROM Problem WHERE id = :problemId")
    void deleteProblemById(int problemId);

    @Query("UPDATE Problem SET isSolved = 1 WHERE id = :problemId")
    void solveProblem(int problemId);
}
