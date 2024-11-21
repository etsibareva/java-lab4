package com.example.j2lab4;

public class BCpu {
    static MCpu mcpu = new MCpu();
    public static ICpu build() {
        mcpu.ram = new MRam(1024);
        return mcpu;
    }
}
