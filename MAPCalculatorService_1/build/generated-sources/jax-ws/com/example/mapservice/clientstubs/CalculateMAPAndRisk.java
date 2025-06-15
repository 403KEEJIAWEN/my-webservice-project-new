
package com.example.mapservice.clientstubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calculateMAPAndRisk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calculateMAPAndRisk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="systolicPressure" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="diastolicPressure" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateMAPAndRisk", propOrder = {
    "systolicPressure",
    "diastolicPressure"
})
public class CalculateMAPAndRisk {

    protected int systolicPressure;
    protected int diastolicPressure;

    /**
     * Gets the value of the systolicPressure property.
     * 
     */
    public int getSystolicPressure() {
        return systolicPressure;
    }

    /**
     * Sets the value of the systolicPressure property.
     * 
     */
    public void setSystolicPressure(int value) {
        this.systolicPressure = value;
    }

    /**
     * Gets the value of the diastolicPressure property.
     * 
     */
    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    /**
     * Sets the value of the diastolicPressure property.
     * 
     */
    public void setDiastolicPressure(int value) {
        this.diastolicPressure = value;
    }

}
