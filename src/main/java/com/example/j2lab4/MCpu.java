package com.example.j2lab4;

import java.util.ArrayList;

public class MCpu implements ICpu {
    int[] registers = new int[4];
    MRam ram;

    ArrayList<ICpuObserver> allObserver = new ArrayList<>();

    @Override
    public void eventCall(){
        allObserver.forEach(action->action.event(this));
    }

    @Override
    public void addObserver(ICpuObserver obs){
        allObserver.add(obs);
    }

    @Override
    public int getReg(int ind) {
        return registers[ind];
    }

    @Override
    public int getRam(int address) {
        return ram.getMem(address);
    }

    @Override
    public void exec(Command command) throws Exception {
        if (command.str.length == 0) throw new Exception("No command");

        try {
            switch (command.str[0]) {
                case "init":
                    init(Integer.parseInt(command.str[1]), Integer.parseInt(command.str[2]));
                    break;
                case "ld":
                    ld(Integer.parseInt(command.str[1]), Integer.parseInt(command.str[2]));
                    break;
                case "st":
                    st(Integer.parseInt(command.str[1]), Integer.parseInt(command.str[2]));
                    break;
                case "print":
                    print();
                    break;
                case "add":
                    add();
                    break;
                case "sub":
                    sub();
                    break;
                case "mult":
                    mult();
                    break;
                case "div":
                    div();
                    break;
                case "mod":
                    mod();
                    break;
                default:
                    throw new Exception("Unknown function: " + command.str[0]);
            }
        }
        catch(NumberFormatException e) {
            throw new Exception(e.getMessage()); // parseInt(...)
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        eventCall();
    }

    public void print() {
        for (int register : registers) System.out.print(register + " ");
    }

    private void init(int address, int val) { ram.setMem(address, val);}

    private void ld(int num, int address)   { registers[num] = ram.getMem(address); }

    private void st(int num, int address)   { ram.setMem(address, registers[num]);}

    private void add()  { registers[2] = registers[0] + registers[1]; }

    private void sub()  { registers[2] = registers[0] - registers[1]; }

    private void mult() { registers[2] = registers[0] * registers[1]; }

    private void div() throws Exception {
        if (registers[1] == 0) throw new Exception("Div 0");
        registers[2] = registers[0] / registers[1];
    }

    private void mod() throws Exception {
        if (registers[1] == 0) throw new Exception("Mod 0");
        registers[2] = registers[0] % registers[1];
    }
}
