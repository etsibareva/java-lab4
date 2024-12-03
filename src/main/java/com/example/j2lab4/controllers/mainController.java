package com.example.j2lab4.controllers;

import com.example.j2lab4.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class mainController {
    Pane regState, ramState, statistic, scrollInstruct;

    MProgram prog = BProgram.build();
    MCpu cpu = BCpu.build();

    @FXML
    VBox allStatisticVBox;

    @FXML
    Pane allInstructPane;

    @FXML
    void initialize() throws IOException {
        FXMLLoader fxmlLoader;

        fxmlLoader = new FXMLLoader(appMain.class.getResource("regStateControl.fxml"));
        fxmlLoader.setController(new regStateController());
        regState = fxmlLoader.load();
        cpu.addObserver(fxmlLoader.getController());

        FXMLLoader f1 = new FXMLLoader(appMain.class.getResource("ramStateControl.fxml"));
        f1.setController(new ramStateController(prog));
        ramState = f1.load();
        cpu.addObserver(f1.getController());

        fxmlLoader = new FXMLLoader(appMain.class.getResource("statisticControl.fxml"));
        fxmlLoader.setController(new statisticController());
        statistic = fxmlLoader.load();
        prog.addObserver(fxmlLoader.getController());

        fxmlLoader = new FXMLLoader(appMain.class.getResource("scrollControl.fxml"));
        fxmlLoader.setController(new instrScrollController());
        scrollInstruct = fxmlLoader.load();
        prog.addObserver(fxmlLoader.getController());

        allStatisticVBox.setSpacing(5);
        allStatisticVBox.getChildren().addAll(regState, ramState, statistic);
        allInstructPane.getChildren().add(scrollInstruct);

        prog.setExec(new Executer(cpu));
    }

    @FXML
    public void addInstruction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(appMain.class.getResource("inputInstruct.fxml"));
        fxmlLoader.setController(new instrInputController(prog));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void runStep() {
        prog.runStep(cpu);
    }

    @FXML
    public void reloadProg() {
        prog.reload(cpu);
    }
}
