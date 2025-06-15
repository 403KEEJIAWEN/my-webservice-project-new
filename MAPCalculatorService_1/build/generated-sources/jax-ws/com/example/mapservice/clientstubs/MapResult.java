
package com.example.mapservice.clientstubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mapResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mapResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calculatedMAP" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="hypertensionRisk" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "mapResult", propOrder = {
    "calculatedMAP",
    "hypertensionRisk",
    "systolicPressure",
    "diastolicPressure"
})
public class MapResult {

    protected double calculatedMAP;
    @XmlElement(required = true)
    protected String hypertensionRisk;
    protected int systolicPressure;
    protected int diastolicPressure;

    /**
     * Gets the value of the calculatedMAP property.
     * 
     */
    public double getCalculatedMAP() {
        return calculatedMAP;
    }

    /**
     * Sets the value of the calculatedMAP property.
     * 
     */
    public void setCalculatedMAP(double value) {
        this.calculatedMAP = value;
    }

    /**
     * Gets the value of the hypertensionRisk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHypertensionRisk() {
        return hypertensionRisk;
    }

    /**
     * Sets the value of the hypertensionRisk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHypertensionRisk(String value) {
        this.hypertensionRisk = value;
    }

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
