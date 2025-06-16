/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TDEECalc;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jeremiah
 */



@WebService(serviceName = "TDEECalculatorService")
public class TDEECalculatorService {
    
   

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "calculateTDEEFromIC")
    public double calculateTDEEFromIC(
            @WebParam(name = "icNumber") String icNumber,
            @WebParam(name = "personName") String personName,
            @WebParam(name = "gender") String gender,
            @WebParam(name = "height") double height,
            @WebParam(name = "weight") double weight,
            @WebParam(name = "activityLevel") String activityLevel) {
        
        
        int personAge = calculateAgeFromIC(icNumber);
        if(personAge <= 0) {
            throw new IllegalArgumentException("Invalid IC number or number");
                    }
        return calculateCalories(personAge, gender, height, weight, activityLevel);
    }
          @WebMethod(operationName = "calculateAgeFromIC")
          public int calculateAgeFromIC(@WebParam(name = "icNumber") String icNumber) {

             try{
                 
                 if(icNumber == null || !icNumber.matches("^\\d{12}$")) {
                      throw new IllegalArgumentException("Invalid IC number format.Must be exactly 12 digits only");
                 }
                   String dateBirth = icNumber.substring(0,6);
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
                    LocalDate birthPerson;

                  try{
                      birthPerson = LocalDate.parse(dateBirth, formatter);
                  } catch (Exception e){
                       throw new IllegalArgumentException("Invalid date of birth in IC Number");
                  }
                  
                  if(birthPerson.isAfter(LocalDate.now())){
                      birthPerson = birthPerson.minusYears(100);
                  }
                    
                  
                     return Period.between(birthPerson, LocalDate.now()).getYears();
                              } catch(Exception e) {
                                  e.printStackTrace();
                         throw new IllegalArgumentException("Failed to parse birthdate from IC Number.");
                           
}


}
    
    
    public double calculateCalories(int personAge, String gender, double height, double weight, String activityLevel) {
        
    double bmrCalculation;
    
    if("male" .equalsIgnoreCase(gender)){
        bmrCalculation = 10 * weight + 6.25 * height - 5 * personAge + 5;
    } else{
        bmrCalculation= 10 * weight + 6.25 * height - 5 * personAge - 161;
    }
    
    
    
    double activityFactor;
    switch(activityLevel.toLowerCase()){
        case "light":
            activityFactor = 1.375;
            break;
        case "moderate":
            activityFactor = 1.55;
            break;
        case "active":
            activityFactor = 1.725;
            break;
        case "very active":
            activityFactor = 1.9;
            break;
        default:
            activityFactor = 1.2;
            break;
    }
    double tdee = bmrCalculation * activityFactor; 
    return Math.round(tdee * 100.0) / 100.0;
}
    
    
}
