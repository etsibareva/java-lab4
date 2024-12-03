package com.example.j2lab4;

public class Main {
    public static void main(String[] args) {
        BCpu bcpu   = new BCpu();
        ICpu cpu    = bcpu.build();
        Executer e1 = new Executer(cpu);

        MProgram prog = new MProgram();
        /*
            prog.add(new Command("init", "10", "102"));
            prog.add(new Command("init", "11", "10"));
            prog.add(new Command("ld", "A", "10"));
            prog.add(new Command("ld", "B", "11"));
            prog.add(new Command("add"));
            //prog.add(new Command("add"));
            //prog.add(new Command("mult"));
            //prog.add(new Command("add"));
            //prog.add(new Command("mult"));
            //prog.add(new Command("add"));
            //prog.add(new Command("mult"));
            prog.add(new Command("print"));
        */
        System.out.println("Result: ");
        try {
            //e1.run(prog);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Statistic: ");
        prog.mostOftCommand();
        prog.addressRange();
        prog.commandList();
    }
}