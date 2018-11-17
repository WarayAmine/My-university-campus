package com.eservices.waray.myuniversitycampus.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.eservices.waray.myuniversitycampus.converters.DateConverter;
import com.eservices.waray.myuniversitycampus.converters.TimeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Problem implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    private String address;

    private String description;

    @NonNull
    private double lat;

    @NonNull
    private double lng;

    @NonNull
    @TypeConverters(ProblemType.class)
    private ProblemType type;

    @NonNull
    private boolean isSolved;

    @NonNull
    @TypeConverters(DateConverter.class)
    private Date date;

    @NonNull
    @TypeConverters(TimeConverter.class)
    private Date time;

    //constructor
    public Problem(String address, String description, @NonNull double lat, @NonNull double lng, @NonNull ProblemType type, @NonNull boolean isSolved, @NonNull Date date, @NonNull Date time) {
        this.address = address;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.isSolved = isSolved;
        this.date = date;
        this.time = time;
    }

    //getters
    @NonNull
    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @NonNull
    public double getLat() {
        return lat;
    }

    @NonNull
    public double getLng() {
        return lng;
    }

    @NonNull
    public ProblemType getType() {
        return type;
    }

    @NonNull
    public boolean isSolved() {
        return isSolved;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public Date getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    //setters

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLat(@NonNull double lat) {
        this.lat = lat;
    }

    public void setLng(@NonNull double lng) {
        this.lng = lng;
    }

    public void setType(@NonNull ProblemType type) {
        this.type = type;
    }

    public void setSolved(@NonNull boolean solved) {
        isSolved = solved;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public void setTime(@NonNull Date time) {
        this.time = time;
    }

    public static enum ProblemType {
        ARBRE_TRAILLER("Arbre à trailler", 0),
        ARBRE_ABATTRE("Arbre à abattre", 1),
        DETRUITS("Détruits", 2),
        HAIE_TRAILLER("Haie à trailler", 3),
        MAUVAISE_HERBE("Mauvaise herbe", 4),
        AUTRE("Autre", 5);

        private String typeName;
        private int typeValue;
        ProblemType(String name, int value){
            this.typeName=name;
            this.typeValue=value;
        }

        @TypeConverter
        public static ProblemType getProblemType(int value){
            for(ProblemType p : values()){
                if(p.typeValue == value)
                    return p;
            }
            return null;
        }

        @TypeConverter
        public static int getProblemTypeValue(ProblemType problemType){
            return problemType.typeValue;
        }

        public int getTypeValue(){
            return typeValue;
        }

        @Override
        public String toString(){
            return typeName;
        }


    }

}

