package Bfp.java;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.time.LocalDate;

@WebService(serviceName = "BodyPercentageCalculator")
public class BodyPercentageCalculator {

    @WebMethod(operationName = "calculateBodyFat")
    public String calculateBodyFat(
            @WebParam(name = "icNumber") String icNumber,
            @WebParam(name = "weight") double weight,
            @WebParam(name = "height") double heightCm,
            @WebParam(name = "gender") String gender) {

        // Validate inputs
        if (weight <= 0 || heightCm <= 0) {
            return "Error: Weight and height must be positive values";
        }

        // Convert height from cm to meters
        double heightM = heightCm / 100.0;
        
        // Extract age from IC number
        int age = extractAgeFromIC(icNumber);
        if (age < 0) {
            return "Error: Invalid IC number format";
        }

        // Calculate BMI
        double bmi = calculateBMI(weight, heightM);
        
        // Calculate Body Fat Percentage
        double bfp = calculateBFP(bmi, age, gender);
        if (Double.isNaN(bfp)) {
            return "Error: Invalid gender specified";
        }

        // Classify the result
        String category = classifyBFP(bfp, gender);
        
        return String.format("Age: %d\nHeight: %.2fm\nWeight: %.1fkg\nBMI: %.2f\nBody Fat Percentage: %.2f%%\nCategory: %s", 
                          age, heightM, weight, bmi, bfp, category);
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    private double calculateBFP(double bmi, int age, String gender) {
        if ("male".equalsIgnoreCase(gender)) {
            return (1.20 * bmi) + (0.23 * age) - 16.2;
        } else if ("female".equalsIgnoreCase(gender)) {
            return (1.20 * bmi) + (0.23 * age) - 5.4;
        }
        return Double.NaN;
    }

    private int extractAgeFromIC(String icNumber) {
        try {
            String birthDatePart = icNumber.substring(0, 6); // Get YYMMDD part
            int birthYear = Integer.parseInt(birthDatePart.substring(0, 2));
            int birthMonth = Integer.parseInt(birthDatePart.substring(2, 4));
            int birthDay = Integer.parseInt(birthDatePart.substring(4, 6));

            // Determine century (00-21 is 2000-2021, 22-99 is 1922-1999)
            int currentYear = LocalDate.now().getYear();
            int fullBirthYear = (birthYear <= (currentYear % 100)) ? 
                              (2000 + birthYear) : (1900 + birthYear);

            // Calculate age
            LocalDate birthDate = LocalDate.of(fullBirthYear, birthMonth, birthDay);
            return (int) java.time.temporal.ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        } catch (Exception e) {
            return -1; // invalid IC format
        }
    }

    private String classifyBFP(double bfp, String gender) {
        if ("male".equalsIgnoreCase(gender)) {
            if (bfp < 6) return "Essential fat";
            if (bfp < 14) return "Athletes";
            if (bfp < 18) return "Fitness";
            if (bfp < 25) return "Average";
            return "Obese";
        } else {
            if (bfp < 14) return "Essential fat";
            if (bfp < 21) return "Athletes";
            if (bfp < 25) return "Fitness";
            if (bfp < 32) return "Average";
            return "Obese";
        }
    }
}