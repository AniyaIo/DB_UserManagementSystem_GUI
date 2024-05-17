package com.example.db_usermanagementsystem_gui;

import static com.example.db_usermanagementsystem_gui.DisplayWindow.alertMessage;

public class InputCheck {
    /**
     * データチェック
     * @param name
     * @param score
     * @return
     */
    public static boolean isInputData(String name,String score){
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
    private static boolean isName(String data){
        return !(data.isEmpty() || data.isBlank());
    }
    private static boolean isScore(String data){
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
