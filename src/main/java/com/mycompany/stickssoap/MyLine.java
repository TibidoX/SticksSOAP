/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stickssoap;

/**
 *
 * @author vovab
 */
public class MyLine {
    public int x, y;
    public Positions pos;
    
    MyLine(){
        this.x = 0;
        this.y = 0;
        this.pos = Positions.HORIZONTAL;
    }
    
    MyLine(int x, int y, Positions pos) {
        this.x = x;
        this.y = y;
        this.pos = pos;
    }
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
    
    Positions getPos() {
        return pos;
    }
    
    @Override
    public boolean equals(Object o) {
        MyLine line = (MyLine) o;
        return (x == line.getX() && y == line.getY() && pos == line.getPos());
    }
}
