/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.mavenproject2;

import java.util.TimerTask;

/**
 *
 * @author arnoa
 */
public class TimerPeddel extends TimerTask {
    
    private int t;

    public TimerPeddel() {
        
    }

    @Override
    public void run() {
        t++;
    }

    /**
     * @return the t
     */
    public int getT() {
        return t;
    }
    
    
    
}
    

