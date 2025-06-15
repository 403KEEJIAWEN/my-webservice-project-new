package com.example.mapservice;



    import javax.xml.bind.annotation.XmlAccessType;
    import javax.xml.bind.annotation.XmlAccessorType;
    import javax.xml.bind.annotation.XmlElement;
    import javax.xml.bind.annotation.XmlType;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "mapResult", propOrder = {
        "calculatedMAP", "hypertensionRisk", "systolicPressure", "diastolicPressure"
    })
    public class MAPResult {
        @XmlElement(required = true) protected double calculatedMAP;
        @XmlElement(required = true) protected String hypertensionRisk;
        protected int systolicPressure;
        protected int diastolicPressure;

        public double getCalculatedMAP() { return calculatedMAP; }
        public void setCalculatedMAP(double value) { this.calculatedMAP = value; }
        public String getHypertensionRisk() { return hypertensionRisk; }
        public void setHypertensionRisk(String value) { this.hypertensionRisk = value; }
        public int getSystolicPressure() { return systolicPressure; }
        public void setSystolicPressure(int value) { this.systolicPressure = value; }
        public int getDiastolicPressure() { return diastolicPressure; }
        public void setDiastolicPressure(int value) { this.diastolicPressure = value; }
    }