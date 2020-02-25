/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insulinpump;

/**
 *
 *
 */
public class InsulinPumpSettings {
    
    double insulinToCarb;
    double insulinSensitivity;
    String preExistName;
    
    public void setInsulinToCarb (double newInsulinToCarb){
        insulinToCarb = newInsulinToCarb;
    }
    
    public double getInsulinToCarb(){
        return insulinToCarb;
    }
    
    public void setInsulinSensitivity (double newInsulinSensitivity){
        insulinSensitivity = newInsulinSensitivity;
    }
    public double getInsulinSensitivity(){
        return insulinSensitivity;
    }
    
    public void setPreExistName (String newPreExistName){
        preExistName = newPreExistName;
    }
    public String getPreExistName(){
        return preExistName;
    }
}
