  
package com.TDEE;
import com.TDEE.TDEECalculatorService;
import com.TDEE.TDEECalculatorService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TDEEClient extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        showForm(request, response, null, null);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     
        String resultHtml = processCalculation(request);
        showForm(request, response, resultHtml, request);
    }
    
    private void showForm(HttpServletRequest request, HttpServletResponse response, 
                         String resultHtml, HttpServletRequest formData) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />");
            out.println("<title>TDEE Calculator</title>");
            out.println("<style>");
            
         
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: Arial, sans-serif; background-color: #001f3f; color: #333; line-height: 1.6; padding: 20px; min-height: 100vh; }");
            out.println(".container { max-width: 1000px; margin: 0 auto; }");
            out.println("h1 { color: #ffffff; text-align: center; margin-bottom: 30px; font-size: 2.5em; text-shadow: 2px 2px 4px rgba(0,0,0,0.3); }");
            out.println(".main-content { display: flex; flex-direction: column; gap: 30px; margin-bottom: 30px; }");
            out.println(".form-container { background: #ffffff; padding: 30px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3); height: fit-content; max-width: 500px; margin: 0 auto; }");
            out.println(".form-title { color: #001f3f; text-align: center; margin-bottom: 25px; font-size: 1.5em; }");
            out.println("label { display: block; margin-top: 15px; font-weight: bold; color: #001f3f; margin-bottom: 5px; }");
            out.println("input, select { width: 100%; padding: 12px; border: 2px solid #e0e0e0; border-radius: 8px; font-size: 16px; transition: border-color 0.3s ease; }");
            out.println("input:focus, select:focus { outline: none; border-color: #001f3f; box-shadow: 0 0 5px rgba(0, 31, 63, 0.3); }");
            out.println("input[type=\"submit\"] { margin-top: 25px; background-color: #001f3f; color: white; border: none; cursor: pointer; font-size: 18px; font-weight: bold; padding: 15px; border-radius: 8px; transition: all 0.3s ease; }");
            out.println("input[type=\"submit\"]:hover { background-color: #003366; transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0, 31, 63, 0.4); }");
            out.println(".activity-info { background-color: #fff; padding: 25px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3); }");
            out.println(".activity-info h3 { color: #001f3f; text-align: center; margin-bottom: 20px; font-size: 1.4em; }");
            out.println(".formula-info { background-color: #fff; padding: 25px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }");
            out.println(".formula-info h3 { color: #001f3f; text-align: center; margin-bottom: 20px; font-size: 1.4em; }");
            out.println(".formula-box { background-color: #f8f9fa; padding: 15px; border-radius: 8px; margin-bottom: 15px; border-left: 4px solid #001f3f; }");
            out.println(".formula-box h4 { color: #001f3f; margin-bottom: 10px; }");
            out.println(".formula-text { font-family: 'Courier New', monospace; background-color: #e9ecef; padding: 10px; border-radius: 5px; font-size: 14px; }");
            out.println("table { width: 100%; border-collapse: collapse; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }");
            out.println("th { background-color: #001f3f; color: white; padding: 15px 12px; text-align: left; font-weight: bold; font-size: 14px; }");
            out.println("td { padding: 12px; border-bottom: 1px solid #e0e0e0; }");
            out.println("tr:nth-child(even) { background-color: #f8f9fa; }");
            out.println("tr:hover { background-color: #e3f2fd; transition: background-color 0.2s ease; }");
            out.println(".level-column { font-weight: bold; color: #001f3f; }");
            out.println(".value-column { text-align: center; font-weight: bold; color: #2e7d32; }");
            out.println(".description-column { color: #555; font-size: 14px; }");
            out.println(".calories-column { text-align: center; font-weight: bold; color: #d32f2f; }");
            out.println("#result { margin-top: 20px; background: #fff; padding: 25px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3); max-width: 900px; margin: 20px auto 0; }");
            out.println(".result-success { background: linear-gradient(135deg, #4CAF50, #45a049); color: white; padding: 20px; border-radius: 10px; text-align: center; }");
            out.println(".result-error { background: linear-gradient(135deg, #f44336, #da190b); color: white; padding: 20px; border-radius: 10px; text-align: center; }");
            out.println(".result-details { background: #f8f9fa; padding: 15px; border-radius: 8px; margin-top: 15px; }");
            out.println(".result-details ul { list-style: none; }");
            out.println(".result-details li { padding: 5px 0; border-bottom: 1px solid #dee2e6; }");
            out.println(".result-details li:last-child { border-bottom: none; }");
            out.println(".tdee-result { font-size: 2em; font-weight: bold; margin: 15px 0; }");
            out.println(".weekly-result { font-size: 1.5em; font-weight: bold; margin: 10px 0; color: #2e7d32; }");
            out.println(".calculations-table { margin-top: 20px; }");
            out.println(".calculations-table h4 { color: #001f3f; margin-bottom: 15px; text-align: center; }");
            
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h1>Calculate Your TDEE</h1>");
            
            // TDEE Formula Information
            out.println("<div class=\"formula-info\">");
            out.println("<h3>TDEE Calculation Formulas</h3>");
            out.println("<div class=\"formula-box\">");
            out.println("<h4>Step 1: Calculate BMR (Basal Metabolic Rate)</h4>");
            out.println("<p><strong>For Men:</strong></p>");
            out.println("<div class=\"formula-text\"><strong>BMR = 88.362 + (13.397 × weight in kg) + (4.799 × height in cm) - (5.677 × age in years)</strong></div>");
            out.println("<p style=\"margin-top: 10px;\"><strong>For Women:</strong></p>");
            out.println("<div class=\"formula-text\"><strong>BMR = 447.593 + (9.247 × weight in kg) + (3.098 × height in cm) - (4.330 × age in years)</strong></div>");
            out.println("</div>");
            out.println("<div class=\"formula-box\">");
            out.println("<h4>Step 2: Calculate TDEE</h4>");
            out.println("<div class=\"formula-text\"><strong>TDEE = BMR × Activity Level Multiplier</strong></div>");
            out.println("<p style=\"margin-top: 10px; font-size: 14px; color: #666;\">Activity level multipliers are shown in the table below.</p>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("<div class=\"main-content\">");
            out.println("<div class=\"form-container\">");
            out.println("<h2 class=\"form-title\">Personal Information</h2>");
            out.println("<form method=\"post\">");
            
         
            String icNumber = formData != null ? formData.getParameter("icNumber") : "";
            String personName = formData != null ? formData.getParameter("personName") : "";
            String personAge = formData != null ? formData.getParameter("personAge") : "";
            String gender = formData != null ? formData.getParameter("gender") : "";
            String height = formData != null ? formData.getParameter("height") : "";
            String weight = formData != null ? formData.getParameter("weight") : "";
            String activityLevel = formData != null ? formData.getParameter("activityLevel") : "";
            
            out.println("<label for=\"icNumber\">IC Number:</label>");
            out.println("<input type=\"text\" id=\"icNumber\" name=\"icNumber\" value=\"" + (icNumber != null ? icNumber : "") + "\" required />");
            
            out.println("<label for=\"personName\">Name:</label>");
            out.println("<input type=\"text\" id=\"personName\" name=\"personName\" value=\"" + (personName != null ? personName : "") + "\" required />");
            
            out.println("<label for=\"gender\">Gender:</label>");
            out.println("<select id=\"gender\" name=\"gender\" required>");
            out.println("<option value=\"\">-- Select Gender --</option>");
            out.println("<option value=\"male\"" + ("male".equals(gender) ? " selected" : "") + ">Male</option>");
            out.println("<option value=\"female\"" + ("female".equals(gender) ? " selected" : "") + ">Female</option>");
            out.println("</select>");
            
            out.println("<label for=\"height\">Height (cm):</label>");
            out.println("<input type=\"number\" id=\"height\" name=\"height\" step=\"0.1\" value=\"" + (height != null ? height : "") + "\" required />");
            
            out.println("<label for=\"weight\">Weight (kg):</label>");
            out.println("<input type=\"number\" id=\"weight\" name=\"weight\" step=\"0.1\" value=\"" + (weight != null ? weight : "") + "\" required />");
            
            out.println("<label for=\"activityLevel\">Activity Level:</label>");
            out.println("<select id=\"activityLevel\" name=\"activityLevel\" required>");
            out.println("<option value=\"\">-- Select Activity Level --</option>");
            out.println("<option value=\"sedentary\"" + ("sedentary".equals(activityLevel) ? " selected" : "") + ">Sedentary</option>");
            out.println("<option value=\"light\"" + ("light".equals(activityLevel) ? " selected" : "") + ">Light</option>");
            out.println("<option value=\"moderate\"" + ("moderate".equals(activityLevel) ? " selected" : "") + ">Moderate</option>");
            out.println("<option value=\"active\"" + ("active".equals(activityLevel) ? " selected" : "") + ">Active</option>");
            out.println("<option value=\"very active\"" + ("very active".equals(activityLevel) ? " selected" : "") + ">Very Active</option>");
            out.println("</select>");
            
            out.println("<input type=\"submit\" value=\"Calculate TDEE\" />");
            out.println("</form>");
            out.println("</div>");
            
            // Activity Level Reference Table
            out.println("<div class=\"activity-info\">");
            out.println("<h3>Activity Level Reference</h3>");
            out.println("<table>");
            out.println("<thead><tr><th>Activity Level</th><th>Multiplier</th><th>Description</th></tr></thead>");
            out.println("<tbody>");
            out.println("<tr><td class=\"level-column\">Sedentary</td><td class=\"value-column\">1.2</td><td class=\"description-column\">Little or no exercise, desk job</td></tr>");
            out.println("<tr><td class=\"level-column\">Light</td><td class=\"value-column\">1.375</td><td class=\"description-column\">Light exercise/sports 1-3 days/week</td></tr>");
            out.println("<tr><td class=\"level-column\">Moderate</td><td class=\"value-column\">1.55</td><td class=\"description-column\">Moderate exercise/sports 3-5 days/week</td></tr>");
            out.println("<tr><td class=\"level-column\">Active</td><td class=\"value-column\">1.725</td><td class=\"description-column\">Hard exercise/sports 6-7 days/week</td></tr>");
            out.println("<tr><td class=\"level-column\">Very Active</td><td class=\"value-column\">1.9</td><td class=\"description-column\">Very hard exercise, physical job, or training twice a day</td></tr>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            
            // Show result if available
            if (resultHtml != null) {
                out.println(resultHtml);
            }
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private String processCalculation(HttpServletRequest request) {
        try {
           
            String icNumber = request.getParameter("icNumber");
            String personAge = request.getParameter("personAge");
            String personName = request.getParameter("personName");
            String gender = request.getParameter("gender");
            String heightStr = request.getParameter("height");
            String weightStr = request.getParameter("weight");
            String activityLevel = request.getParameter("activityLevel");
            
          
            double height = 0;
            double weight = 0;
            try {
                height = Double.parseDouble(heightStr);
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException e) {
                return "<div id=\"result\"><div class=\"result-error\"><h3>Error</h3><p>Invalid height or weight format.</p></div></div>";
            }
            
           
           
            TDEECalculatorService_Service serviceFactory = new TDEECalculatorService_Service();
            TDEECalculatorService service = serviceFactory.getTDEECalculatorServicePort();
            
            
            double selectedTdee = service.calculateTDEEFromIC(icNumber, personName, gender, height, weight, activityLevel);
            int age = service.calculateAgeFromIC(icNumber);
            double weeklyCalories = selectedTdee * 7;
            
           
            String[] activityLevels = {"sedentary", "light", "moderate", "active", "very active"};
            double[] tdeeValues = new double[5];
            
            for (int i = 0; i < activityLevels.length; i++) {
                tdeeValues[i] = service.calculateTDEEFromIC(icNumber, personName, gender, height, weight, activityLevels[i]);
            }
            
            
            StringBuilder result = new StringBuilder();
            result.append("<div id=\"result\">");
            result.append("<div class=\"result-success\">");
            result.append("<h3>Calculation Complete!</h3>");
            result.append("<div class=\"tdee-result\">Your TDEE: ").append(String.format("%.2f", selectedTdee)).append(" calories/per day</div>");
            result.append("<div class=\"weekly-result\">Weekly Calories: ").append(String.format("%.2f", weeklyCalories)).append(" calories/per week</div>");
            result.append("</div>");
            
            result.append("<div class=\"result-details\">");
            result.append("<h4>Your Information:</h4>");
            result.append("<ul>");
            result.append("<li><strong>Age:</strong> ").append(age).append("</li>");
            result.append("<li><strong>Name:</strong> ").append(personName).append("</li>");
            result.append("<li><strong>Gender:</strong> ").append(gender).append("</li>");
            result.append("<li><strong>Height:</strong> ").append(height).append(" cm</li>");
            result.append("<li><strong>Weight:</strong> ").append(weight).append(" kg</li>");
            result.append("<li><strong>Selected Activity Level:</strong> ").append(activityLevel).append("</li>");
            result.append("</ul>");
            result.append("</div>");
            
            // Add table showing all activity level calculations
            result.append("<div class=\"calculations-table\">");
            result.append("<h4>TDEE Calculations for All Activity Levels</h4>");
            result.append("<table>");
            result.append("<thead><tr><th>Activity Level</th><th>Multiplier</th><th>Daily Calories</th><th>Weekly Calories</th><th>Description</th></tr></thead>");
            result.append("<tbody>");
            
            String[] descriptions = {
                "Little or no exercise, desk job",
                "Light exercise/sports 1-3 days/week", 
                "Moderate exercise/sports 3-5 days/week",
                "Hard exercise/sports 6-7 days/week",
                "Very hard exercise, physical job, or training twice a day"
            };
            
            double[] multipliers = {1.2, 1.375, 1.55, 1.725, 1.9};
            
            for (int i = 0; i < activityLevels.length; i++) {
                String levelDisplay = activityLevels[i].substring(0, 1).toUpperCase() + activityLevels[i].substring(1);
                String rowClass = activityLevels[i].equals(activityLevel) ? " style=\"background-color: #e8f5e8;\"" : "";
                
                result.append("<tr").append(rowClass).append(">");
                result.append("<td class=\"level-column\">").append(levelDisplay).append("</td>");
                result.append("<td class=\"value-column\">").append(multipliers[i]).append("</td>");
                result.append("<td class=\"calories-column\">").append(String.format("%.2f", tdeeValues[i])).append("</td>");
                result.append("<td class=\"calories-column\">").append(String.format("%.2f", tdeeValues[i] * 7)).append("</td>");
                result.append("<td class=\"description-column\">").append(descriptions[i]).append("</td>");
                result.append("</tr>");
            }
            
            result.append("</tbody>");
            result.append("</table>");
            result.append("<p style=\"margin-top: 10px; font-size: 12px; color: #666; text-align: center;\">");
            result.append("Your selected activity level is highlighted in green.</p>");
            result.append("</div>");
            
            result.append("</div>");
            
            return result.toString();
            
        } catch (Exception e) {
            return "<div id=\"result\"><div class=\"result-error\"><h3>Error</h3><p>Error calling web service: " + e.getMessage() + "</p></div></div>";
        }
    }


}