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
import java.util.ResourceBundle;

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
    private ObservableList<String> companyList = FXCollections.observableArrayList("Googla","Applo","Amazondo","Facewatch","Twitton");


    /**
     * 初期化処理
     * @param url
     * @param resourceBundle
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companyName.getItems().addAll(companyList);
        companyName.getSelectionModel().select(0);

        //////一覧表示//////

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

    //メッセージ画面を出す
    private void alertMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("問題が発生しました");
        alert.setContentText(message);
        alert.show();
    }

    /**
     * データチェック
     * @param name
     * @param score
     * @return
     */
    private boolean isInputData(String name,String score){
        boolean isName=isName(name);
        boolean isScore=isScore(score);

        if(!isName && !isScore){
            alertMessage("スコアと名前が正しく入力されていません");
        }else if(!isName){
            alertMessage("名前が正しく入力されていません");
        }else if(!isScore){
            alertMessage("スコアが正しく入力されていません");
        } else if ((Integer.valueOf(score) < 0) || Integer.valueOf(score) > 100) {
            alertMessage("スコアは0から100までの値しか入力できません");
            return false;
        }

        return isName && isScore;
    }

    //UserDataに入れる値のチェックを行う
    private boolean isName(String data){
        return !(data.isEmpty() || data.isBlank());
    }
    private boolean isScore(String data){
        boolean isScore = !(data.isEmpty() || data.isBlank());
        for (int i = 0; i < data.length(); i++) {
            if (!Character.isDigit(data.charAt(i))) {
                isScore = false;
                break;
            }
        }
        return isScore;
    }
}