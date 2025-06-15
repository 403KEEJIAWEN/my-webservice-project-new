package com.example.mapclient;

import com.example.mapservice.clientstubs.MapResult;
import com.example.mapservice.clientstubs.MAPService_Service;
import com.example.mapservice.clientstubs.MAPService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MAPClientServlet", urlPatterns = {"/api/calculate-map"})
public class MAPClientServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        PrintWriter out = response.getWriter();
        
        try {
            // Debug: Print all parameters
            System.out.println("=== Debug: All Parameters ===");
            java.util.Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                System.out.println(paramName + " = " + paramValue);
            }
            
            String systolicStr = request.getParameter("systolicPressure");
            String diastolicStr = request.getParameter("diastolicPressure");
            
            System.out.println("Systolic: '" + systolicStr + "'");
            System.out.println("Diastolic: '" + diastolicStr + "'");
            
            // Check for null or empty values
            if (systolicStr == null || diastolicStr == null) {
                System.out.println("One or both parameters are null");
                out.print("{\"success\":false,\"error\":\"Missing parameters: systolic=" + 
                         systolicStr + ", diastolic=" + diastolicStr + "\"}");
                return;
            }
            
            // Trim whitespace
            systolicStr = systolicStr.trim();
            diastolicStr = diastolicStr.trim();
            
            if (systolicStr.isEmpty() || diastolicStr.isEmpty()) {
                System.out.println("One or both parameters are empty after trim");
                out.print("{\"success\":false,\"error\":\"Empty parameters after trim\"}");
                return;
            }
            
            int systolic = Integer.parseInt(systolicStr);
            int diastolic = Integer.parseInt(diastolicStr);
            
            if (systolic <= 0 || diastolic <= 0) {
                out.print("{\"success\":false,\"error\":\"Pressure values must be positive numbers\"}");
                return;
            }
            
            if (systolic <= diastolic) {
                out.print("{\"success\":false,\"error\":\"Systolic pressure must be greater than diastolic pressure\"}");
                return;
            }
            
            // Call the web service
            MAPService_Service serviceClient = new MAPService_Service();
            MAPService port = serviceClient.getMAPServicePort();
            MapResult mapResult = port.calculateMAPAndRisk(systolic, diastolic);
            
            // Create JSON response
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("{");
            jsonResponse.append("\"success\":true,");
            jsonResponse.append("\"systolicPressure\":").append(systolic).append(",");
            jsonResponse.append("\"diastolicPressure\":").append(diastolic).append(",");
            jsonResponse.append("\"calculatedMAP\":").append(mapResult.getCalculatedMAP()).append(",");
            jsonResponse.append("\"hypertensionRisk\":\"").append(escapeJson(mapResult.getHypertensionRisk())).append("\",");
            jsonResponse.append("\"riskCategory\":\"").append(getRiskCategory(mapResult.getCalculatedMAP())).append("\"");
            jsonResponse.append("}");
            
            out.print(jsonResponse.toString());
            
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            out.print("{\"success\":false,\"error\":\"Please enter valid numeric values\"}");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            out.print("{\"success\":false,\"error\":\"Server error: " + escapeJson(e.getMessage()) + "\"}");
        } finally {
            out.close();
        }
    }
    
    private String getRiskCategory(double map) {
        if (map < 90) {
            return "Normal";
        } else if (map < 92) {
            return "Elevated";
        } else if (map < 96) {
            return "Stage 1 Hypertension";
        } else {
            return "Stage 2 Hypertension";
        }
    }
    
    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\b", "\\b")
                   .replace("\f", "\\f")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"GET method not allowed. Use POST.\"}");
    }
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}