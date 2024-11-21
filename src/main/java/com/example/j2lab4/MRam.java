package com.example.j2lab4;

public class MRam {
    private int[] mem;

    public MRam(int val) {
        mem = new int[val];
        for (int i = 0; i < val; i++) { mem[i] = 0;}
    }
    public int getMem(int ind) {
        return mem[ind];
    }

    public void setMem(int ind, int val) {
        mem[ind] = val;
    }
}

/*
public class Ram {
    private MAddress[] mem;

    public Ram(int val) {
        mem = new MAddress[val];
        for (int i = 0; i < val; i++) {mem[i] = new MAddress(); mem[i].setValue(0);}//i.setValue(0);
    }
    public MAddress getMem(int ind) {
        return mem[ind];
    }

    public void setMem(int ind, int val) {
        mem[ind].setValue(val);//.setValue(val);
    }

    public void ramNull() {
        for (MAddress a: mem) {
            a.setValue(0);
            a.setState(false);
        }
    }
}
*/