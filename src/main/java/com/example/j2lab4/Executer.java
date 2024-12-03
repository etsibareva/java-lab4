package com.example.j2lab4;
import java.util.Iterator;

public class Executer {
    private final ICpu cpu;

    public Executer(ICpu _cpu) {
        cpu = _cpu;
    }

    public void run(Command c) throws Exception {
        try {
            cpu.exec(c);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}