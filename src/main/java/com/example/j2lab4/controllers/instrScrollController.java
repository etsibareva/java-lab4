package com.example.j2lab4.controllers;

import com.example.j2lab4.*;
import com.example.j2lab4.IProgObserver;
import com.example.j2lab4.MProgram;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class instrScrollController implements IProgObserver {
    @FXML
    VBox allInstruct;

    @Override
    public void event(MProgram p) {
        allInstruct.getChildren().clear();

        for (Command c : p.copy()) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    appMain.class.getResource("instructControl.fxml"));
            instrController ic = new instrController();
            fxmlLoader.setController(ic);
            try {
                Pane pane = fxmlLoader.load();
                ic.setInstruction(c, p);
                allInstruct.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
