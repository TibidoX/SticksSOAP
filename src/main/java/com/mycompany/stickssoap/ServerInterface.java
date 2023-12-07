/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.stickssoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author vovab
 */
@WebService
public interface ServerInterface {
    @WebMethod
    public void move(int x, int y, int size, int id);
    @WebMethod
    public void addClient(int port, String name) throws Exception;
    @WebMethod
    public int getClientsCount();
    @WebMethod
    public int checkWinner();
    @WebMethod
    public int checkMove();
}
