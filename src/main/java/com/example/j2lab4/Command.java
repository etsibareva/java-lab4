package com.example.j2lab4;

public class Command {
    String[] str;
    boolean isRun = false;
    int id = -1;

    public boolean isRun(){return isRun;}

    public int  getID() {return id;}
    public void setID(int _id) { id = _id;}

    Command(String a) {
        str = new String[1];
        str[0] = a;
    }

    public Command(String a, String b, String c) {
        str = new String[3];
        str[0] = a;

        if (a.equals("ld") || a.equals("st")) {
            switch(b) {
                case "A":
                    str[1] = "0";
                    break;
                case "B":
                    str[1] = "1";
                    break;
                case "C":
                    str[1] = "2";
                    break;
                case "D":
                    str[1] = "3";
                    break;
                default:
                    str[1] = b;
            }
        }
        else {
            str[1] = b;
        }

        str[2] = c;
    }

    public String getInstr() {return str[0];}

    public String getArg1() {
        if (str[0].equals("ld") || str[0].equals("st")) {
            switch (str[1]) {
                case "0":
                    return "A";
                case "1":
                    return "B";
                case "2":
                    return "C";
                case "3":
                    return "D";
            };
        }
        return str[1];
    }

    public String getArg2() {return str[2];}

    public void setRun() {isRun = true;}

    public void clrRun() {isRun = false;}
}
