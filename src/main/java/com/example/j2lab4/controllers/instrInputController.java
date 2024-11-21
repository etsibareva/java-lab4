package com.example.j2lab4.controllers;

import com.example.j2lab4.BProgram;
import com.example.j2lab4.Command;
import com.example.j2lab4.MProgram;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class instrInputController {
    @FXML
    TextField instr;

    @FXML
    TextField arg1;

    @FXML
    TextField arg2;

    MProgram prog;

    instrInputController(MProgram _prog) { prog = _prog; }

    @FXML
    void inputInstruction() {
        Command command = new Command(instr.getText(), arg1.getText(), arg2.getText());
        prog.add(command);
    }

}
