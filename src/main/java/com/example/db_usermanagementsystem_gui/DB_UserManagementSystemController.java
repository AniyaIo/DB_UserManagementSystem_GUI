package com.example.db_usermanagementsystem_gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.db_usermanagementsystem_gui.InputCheck.isInputData;
import DB.service.CompaniesService;
import DB.service.UsersService;


public class DB_UserManagementSystemController implements Initializable {
    @FXML
    private ComboBox<String> companyName;
    @FXML
    private TextField userNameInput;
    @FXML
    private TextField userScoreInput;
    @FXML
    private TableView<UserData> dataTable;
    @FXML
    private TableColumn<UserData, IntegerProperty> idColumn;
    @FXML
    private TableColumn<UserData, StringProperty> nameColumn;
    @FXML
    private TableColumn<UserData,StringProperty> companyColumn;
    @FXML
    private TableColumn<UserData, IntegerProperty> scoreColumn;

    //DB用ユーザデータ
    private ObservableList<UserData> data;
    private int id=0;
    private ObservableList<String> companyList;


    /**
     * 初期化処理
     * @param url
     * @param resourceBundle
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var usersTable=new UsersService();  //テーブル操作オブジェクト
        var companiesTable=new CompaniesService();
        companyList = FXCollections.observableArrayList();
        List<String> name= companiesTable.getAllName();
        for(var n:name){
            companyList.add(n);
        }

        //コンボボックスに企業名を入れる
        companyName.getItems().addAll(companyList);
        companyName.getSelectionModel().select(0);

        //////ユーザ一覧表示//////

        //テストデータ
        this.data =FXCollections.observableArrayList(
            new UserData(this.id++,companyList.get(0),"Tanaka",45),
            new UserData(this.id++,companyList.get(2),"Yamada",80),
            new UserData(this.id++,companyList.get(4),"Satou",72)
        );
        this.dataTable.setEditable(true);
        this.dataTable.getSelectionModel().setCellSelectionEnabled(false);
        this.dataTable.setItems(this.data);

        //行要素とフィールド紐づけ
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

    }

    /**
     * データ追加処理
     */
    @FXML
    protected void insertUserEvent() {
        String userCompany= companyName.getValue();
        String addName=userNameInput.getText();
        String addScore=userScoreInput.getText();

        //スコアと名前のチェック
        if(!isInputData(addName,addScore)){
            return;
        }

        data.add(new UserData(this.id++,userCompany,addName,Integer.valueOf(addScore)));
    }

    /**
     * データ削除処理
     */
    @FXML
    protected void deleteUserEvent(){
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        this.data.remove(itemList);
    }

    /**
     * データ更新処理
     */
    @FXML
    protected void updateUserEvent(){
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        System.out.println(itemList);
        if(!isInputData(nameColumn.getText(),userScoreInput.getText())){
            return;
        }

        itemList.setCompany(companyName.getValue());
        itemList.setName(userNameInput.getText());
        itemList.setScore(Integer.parseInt(userScoreInput.getText()));
    }

    /**
     * テーブルのアイテム選択処理
     */
    @FXML
    protected void selectColumnEvent(){
        //選択されているアイテム(UserData型)を取得
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        //取得したアイテムをtextAreaにset
        try {
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        }catch (NullPointerException e){
            itemList=dataTable.getItems().getFirst();
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        }

    }


}