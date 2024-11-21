package com.example.j2lab4.controllers;

import com.example.j2lab4.IProgObserver;
import com.example.j2lab4.MProgram;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class statisticController implements IProgObserver {
    @FXML
    VBox allStatistic;

    @Override
    public void event(MProgram p) {
        allStatistic.getChildren().clear();
        Map<String, Long> commandList = p.commandList();

        for (String s: commandList.keySet()) {
            Label l1 = new Label(String.valueOf(commandList.get(s)));
            Label l2 = new Label(String.valueOf(s));

            HBox hbox = new HBox();
            hbox.getChildren().addAll(l1, l2);

            hbox.setSpacing(100);

            allStatistic.getChildren().add(hbox);
        }
    }
}
