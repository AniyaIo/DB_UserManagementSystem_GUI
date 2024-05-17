package com.example.db_usermanagementsystem_gui;

import javafx.scene.control.Alert;

public class DisplayWindow {
    //メッセージ画面を出す
    public static void alertMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("問題が発生しました");
        alert.setContentText(message);
        alert.show();
    }
}
