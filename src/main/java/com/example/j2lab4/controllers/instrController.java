package com.example.j2lab4.controllers;

import com.example.j2lab4.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class instrController {
    @FXML
    Label instrName;

    @FXML
    Label arg1;

    @FXML
    Label arg2;

    Command command;

    MProgram prog;

    public void setInstruction(Command c, MProgram p){
        prog = p;
        command = c;
        instrName.setText(command.getInstr());

        if (command.isRun()) {
            instrName.setTextFill(Color.RED);
        }

        if (c.getInstr().equals("ld") || c.getInstr().equals("st") || c.getInstr().equals("init")) {
            arg1.setText(command.getArg1());
            arg2.setText(command.getArg2());
        }
    }

    @FXML
    public void removeInstruction() {  prog.removeInstr(command);    }

    @FXML
    public void replaceLeft() { prog.left(command); }

    @FXML
    public void replaceRight() { prog.right(command); }
}
