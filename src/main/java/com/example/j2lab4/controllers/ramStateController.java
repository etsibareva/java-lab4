package com.example.j2lab4.controllers;

import com.example.j2lab4.ICpuObserver;
import com.example.j2lab4.MCpu;
import com.example.j2lab4.MProgram;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ramStateController implements ICpuObserver {
    MProgram prog; // пока программа одна, считаем, что д. объект - программа исполняемая на процессоре сейчас

    ramStateController(MProgram _prog) { prog = _prog; }

    @FXML
    VBox allRam;

    @Override
    public void event(MCpu cpu) {
        allRam.getChildren().clear();
        int i = 0;
        while (i < 1000)
        {
            HBox hbox = new HBox();
            if (i < 10) hbox.setSpacing(30);
            else if (i < 100) hbox.setSpacing(25);
            else hbox.setSpacing(20);

            Label l1 = new Label(i + " : " + cpu.getRam(i));
            if (prog.isRunAddress(i)) l1.setTextFill(Color.RED);

            Label l2 = new Label(i + 1 + " : " + cpu.getRam(i + 1));
            if (prog.isRunAddress(i+1)) l2.setTextFill(Color.RED);

            Label l3 = new Label(i + 2 + " : " + cpu.getRam(i + 2));
            if (prog.isRunAddress(i+2)) l3.setTextFill(Color.RED);

            Label l4 = new Label(i + 3 + " : " + cpu.getRam(i + 3));
            if (prog.isRunAddress(i+3)) l4.setTextFill(Color.RED);

            Label l5 = new Label(i + 4 + " : " + cpu.getRam(i + 4));
            if (prog.isRunAddress(i+4)) l5.setTextFill(Color.RED);

            hbox.getChildren().addAll(l1, l2, l3, l4, l5);
            allRam.getChildren().add(hbox);

            i+=5;
        }
    }
}
