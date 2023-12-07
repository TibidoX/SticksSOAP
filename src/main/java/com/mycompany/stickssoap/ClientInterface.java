/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.stickssoap;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author vovab
 */
@WebService
public interface ClientInterface {
    @WebMethod
    void update(List<MyLine> lines, List<Integer> X, List<Integer> O);
    @WebMethod
    void showWinner(int id);
}
