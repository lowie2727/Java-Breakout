/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Steen;

/**
 *
 * @author lowie
 */
public class Spel {

    private Ballen ballen;
    private Stenen stenen;
    private Paneel paneel;
    private PowerUp powerUp;
    private Peddel peddel;

    public Spel(Paneel paneel) {
        this.paneel = paneel;
        ballen = new Ballen(paneel, 1);
        peddel = new Peddel(10, paneel);
        stenen = new Stenen(paneel, 500);
        powerUp = new PowerUp(20);
    }
    
    public void update(){
        
    }
    
    public void botsingBalStenen(){
        for(Bal bal : ballen.getBallen()){
            for(Steen[] steen : stenen.getStenen()){
                System.out.println(steen);
            }
            
            
        }
    }
    
    

}
