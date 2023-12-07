/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stickssoap;

import java.net.URL;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Service;

/**
 *
 * @author vovab
 */
@WebService
public class Server implements ServerInterface{
    List<MyLine> lines = new ArrayList();
    List<Integer> X = new ArrayList();
    List<Integer> O = new ArrayList();
    List<Integer> squares = new ArrayList();
    List<ClientInterface> clients = new ArrayList();
    int move = -1;
    
    @WebMethod
    @Override
    public void move(int x, int y, int size, int id) {
        if (move != id) return;
        Positions pos;
        int index_x, index_y;
        if (x % size < 10) {
            pos = Positions.VERTICAL;
            index_x = x/size;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        } else if (x % size > 35) {
            pos = Positions.VERTICAL;
            index_x = x/size + 1;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        } else {
            pos = Positions.HORIZONTAL;
            index_x = x/size;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        }
//        int index = checkSquare(size);
//        if (index != -1) {
//            if (id == 8081) X.add(index);
//            else O.add(index);
//        } else {
//            switchMove();
//        }

        List<Integer> indexes = checkSquare(size);
        if (!indexes.isEmpty()) {
            if (id == 8081) {
                for (Integer i: indexes) {
                    X.add(i);
                }
            } else {
                for (Integer i: indexes) {
                    O.add(i);
                }
            }
        } else {
            switchMove();
        }
        updateClients();
        if (checkWinner() != -1) reset();
    }
    
    @WebMethod
    @Override
    public int checkMove() {
        return move;
    }
    
    private void switchMove() {
        if (move == 8081) move = 1099;
        else move = 8081;
    }
    
    @WebMethod
    @Override
    public int checkWinner() {
        if (X.size() >= 3) {
            return 8081;
        } else if (O.size() >= 3) {
            return 1099;
        }
        return -1;
    }
    
    @WebMethod
    @Override
    public void addClient(int port, String name) throws Exception{
        if (clients.size() < 2) {
            String host = null;
            String url = "http://localhost:%d/%s?wsdl";
            String serviceName = "FieldService";
            String portName = "FieldPort";
            
            Service service = Service.create(new URL(String.format(url, port, name)), 
                    new QName("http://stickssoap.mycompany.com/", serviceName));
            ClientInterface proxy = service.getPort(new QName("http://stickssoap.mycompany.com/", 
                    portName), ClientInterface.class);
            
            clients.add(proxy);
            
            if (clients.size() == 2) switchMove();
            updateClients();
        }
    }
    
    @WebMethod
    @Override
    public int getClientsCount() {
        return clients.size();
    }
    
    //private int checkSquare(int size) {
    private List<Integer> checkSquare(int size) {
        //int size = this.getSize().height/6;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) 
            for (int j = 0; j < 7; j++) {
                List<MyLine> tmp = new ArrayList();
                tmp.add(new MyLine(j*size, i*size, Positions.HORIZONTAL));
                tmp.add(new MyLine(j*size+size, i*size, Positions.VERTICAL));
                tmp.add(new MyLine(j*size, i*size+size, Positions.HORIZONTAL));
                tmp.add(new MyLine(j*size, i*size, Positions.VERTICAL));

                if (lines.containsAll(tmp) ) {
                    if (!squares.contains(i*6+j)) {
                        squares.add(i*6+j);
                        //return i*6+j;
                        res.add(i*6+j);
                    }
                }
            }
        //return -1;
        return res;
    }
    
    private void reset() {
        lines.clear();
        X.clear();
        O.clear();
        squares.clear();
        move = 8081;
        updateClients();
    }
    
    private void updateClients() {
        for (ClientInterface client: clients) {
            client.update(lines, X, O);
            client.showWinner(checkWinner());
        }
    }
}
