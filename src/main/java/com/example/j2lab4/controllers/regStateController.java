package com.example.j2lab4.controllers;

import com.example.j2lab4.ICpuObserver;
import com.example.j2lab4.MCpu;
import com.example.j2lab4.MProgram;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class regStateController implements ICpuObserver {
    @FXML
    Label la;
    @FXML
    Label lb;
    @FXML
    Label lc;
    @FXML
    Label ld;

    @Override
    public void event(MCpu p) {
        la.setText(String.valueOf(p.getReg(0)));
        lb.setText(String.valueOf(p.getReg(1)));
        lc.setText(String.valueOf(p.getReg(2)));
        ld.setText(String.valueOf(p.getReg(3)));
    }
}
