package com.example.db_usermanagementsystem_gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {
    private final IntegerProperty id;
    private StringProperty company;
    private StringProperty name;
    private IntegerProperty score;

    public UserData(int id,String company,String name,int score){
        this.id=new SimpleIntegerProperty(id);
        this.company=new SimpleStringProperty(company);
        this.name=new SimpleStringProperty(name);
        this.score=new SimpleIntegerProperty(score);

    }

    // アクセサメソッド
    public IntegerProperty getId() {
        return id;
    }
    public StringProperty getCompany() {
        return company;
    }
    public StringProperty getName() {
        return name;
    }
    public IntegerProperty getScore() {
        return score;
    }

    public void setName(String name){
        this.name.setValue(name);
    }
    public void setCompany(String company){
        this.company.setValue(company);
    }
    public void setScore(int score){
        this.score.setValue(score);
    }

    public IntegerProperty idProperty(){
        return id;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty companyProperty(){
        return company;
    }
    public IntegerProperty scoreProperty() {
        return score;
    }

}

