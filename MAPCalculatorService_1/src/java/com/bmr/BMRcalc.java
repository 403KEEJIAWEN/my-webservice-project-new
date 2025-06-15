package com.bmr;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "BMRcalc")
public class BMRcalc {

    @WebMethod(operationName = "calculateBMR")
    public String calculateBMR(
            @WebParam(name = "icNumber") String icNumber,
            @WebParam(name = "weight") double weight,
            @WebParam(name = "height") double height,
            @WebParam(name = "gender") String gender) {

        try {
            // Validate IC number
            if (icNumber == null || icNumber.length() != 12 || !icNumber.matches("\\d+")) {
                return "Invalid IC number. Must be 12 digits.";
            }

            // Extract birth year from first two digits
            int yearPrefix = Integer.parseInt(icNumber.substring(0, 2));
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            int fullYear;

            // Handle 19xx or 20xx logic
            if (yearPrefix < 25) {
                fullYear = 2000 + yearPrefix; // e.g., 05 → 2005
            } else {
                fullYear = 1900 + yearPrefix; // e.g., 90 → 1990
            }

            int age = currentYear - fullYear;

            // Calculate BMR
            double bmr;
            if ("male".equalsIgnoreCase(gender)) {
                bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
            } else if ("female".equalsIgnoreCase(gender)) {
                bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
            } else {
                return "Invalid gender. Use 'male' or 'female'.";
            }

            return String.format("Age: %d, BMR: %.2f kcal/day", age, bmr);

        } catch (NumberFormatException ex) {
            return "Error parsing input values.";
        } catch (Exception ex) {
            return "An error occurred: " + ex.getMessage();
        }
    }
}