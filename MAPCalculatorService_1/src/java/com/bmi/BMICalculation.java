package com.bmi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "BMICalculation")
public class BMICalculation {

    @WebMethod
    public String calculateBMI(
            @WebParam(name = "icNumber") String icNumber, 
            @WebParam(name = "name") String name, 
            @WebParam(name = "gender") String gender, 
            @WebParam(name = "weight") double weight, 
            @WebParam(name = "heightCm") double heightCm) {
        
        // Add debug logging to see what's being received
      
        System.out.println("IC Number received: '" + icNumber + "'");
        System.out.println("Name received: '" + name + "'");
        System.out.println("Gender received: '" + gender + "'");
        System.out.println("Weight received: " + weight);
        System.out.println("Height received: " + heightCm);
        System.out.println("============================");
        
        try {
            // Check if icNumber is null or empty
            if (icNumber == null || icNumber.trim().isEmpty()) {
                return "Error: IC number is required.";
            }
            
            // Remove any whitespace
            icNumber = icNumber.trim();
            
            // Check IC format
            if (icNumber.length() != 12) {
                return "Error: IC number must be exactly 12 digits. Received: " + icNumber.length() + " digits.";
            }
            
            if (!icNumber.matches("\\d{12}")) {
                return "Error: IC number must contain only digits.";
            }
            
            // Calculate age from IC
            int age = calculateAge(icNumber);
            if (age == -1) {
                return "Error: Invalid IC number format - unable to parse date.";
            }

            // Validate other inputs
            if (weight <= 0 || heightCm <= 0) {
                return "Error: Invalid weight or height values.";
            }

            if (name == null || name.trim().isEmpty()) {
                return "Error: Name is required.";
            }

            if (gender == null || gender.trim().isEmpty()) {
                return "Error: Gender is required.";
            }

            // Calculate BMI
            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);

            // Determine category
            String category;
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi < 25) {
                category = "Normal weight";
            } else if (bmi < 30) {
                category = "Overweight";
            } else {
                category = "Obese";
            }

            // Return formatted result
          // In BMICalculation.java, change the return statement to:
return String.format("SUCCESS|%s|%s|%d|%.2f|%s|%.1f|%.1f", 
    name.trim(), gender.trim(), age, bmi, category, weight, heightCm);

        } catch (Exception e) {
            System.err.println("Exception in calculateBMI: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public int calculateAge(String icNumber) {
        try {
            if (icNumber == null || icNumber.length() != 12) {
                System.out.println("Invalid IC length: " + (icNumber == null ? "null" : icNumber.length()));
                return -1;
            }

            // Extract birth date from IC (first 6 digits: YYMMDD)
            String birthDateStr = icNumber.substring(0, 6);
            System.out.println("Extracted birth date string: " + birthDateStr);
            
            // Parse year, month, day
            int year = Integer.parseInt(birthDateStr.substring(0, 2));
            int month = Integer.parseInt(birthDateStr.substring(2, 4));
            int day = Integer.parseInt(birthDateStr.substring(4, 6));
            
            System.out.println("Parsed - Year: " + year + ", Month: " + month + ", Day: " + day);
            
            // Determine century
            int currentYear = LocalDate.now().getYear();
            int fullYear = (year <= (currentYear % 100)) ? 2000 + year : 1900 + year;
            
            System.out.println("Full year determined: " + fullYear);
            
            // Validate date
            if (month < 1 || month > 12 || day < 1 || day > 31) {
                System.out.println("Invalid date components");
                return -1;
            }
            
            try {
                LocalDate birthDate = LocalDate.of(fullYear, month, day);
                int age = LocalDate.now().getYear() - birthDate.getYear();
                System.out.println("Calculated age: " + age);
                return age;
            } catch (Exception dateException) {
                System.out.println("Invalid date: " + dateException.getMessage());
                return -1;
            }
            
        } catch (Exception e) {
            System.out.println("Exception in calculateAge: " + e.getMessage());
            return -1;
        }
    }
}