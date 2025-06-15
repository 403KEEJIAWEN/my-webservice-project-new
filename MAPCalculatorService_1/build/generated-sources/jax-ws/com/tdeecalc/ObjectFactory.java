
package com.tdeecalc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tdeecalc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalculateCalories_QNAME = new QName("http://TDEECalc.com/", "calculateCalories");
    private final static QName _CalculateTDEEFromIC_QNAME = new QName("http://TDEECalc.com/", "calculateTDEEFromIC");
    private final static QName _CalculateTDEEFromICResponse_QNAME = new QName("http://TDEECalc.com/", "calculateTDEEFromICResponse");
    private final static QName _CalculateCaloriesResponse_QNAME = new QName("http://TDEECalc.com/", "calculateCaloriesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tdeecalc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateCaloriesResponse }
     * 
     */
    public CalculateCaloriesResponse createCalculateCaloriesResponse() {
        return new CalculateCaloriesResponse();
    }

    /**
     * Create an instance of {@link CalculateCalories }
     * 
     */
    public CalculateCalories createCalculateCalories() {
        return new CalculateCalories();
    }

    /**
     * Create an instance of {@link CalculateTDEEFromICResponse }
     * 
     */
    public CalculateTDEEFromICResponse createCalculateTDEEFromICResponse() {
        return new CalculateTDEEFromICResponse();
    }

    /**
     * Create an instance of {@link CalculateTDEEFromIC }
     * 
     */
    public CalculateTDEEFromIC createCalculateTDEEFromIC() {
        return new CalculateTDEEFromIC();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateCalories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TDEECalc.com/", name = "calculateCalories")
    public JAXBElement<CalculateCalories> createCalculateCalories(CalculateCalories value) {
        return new JAXBElement<CalculateCalories>(_CalculateCalories_QNAME, CalculateCalories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateTDEEFromIC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TDEECalc.com/", name = "calculateTDEEFromIC")
    public JAXBElement<CalculateTDEEFromIC> createCalculateTDEEFromIC(CalculateTDEEFromIC value) {
        return new JAXBElement<CalculateTDEEFromIC>(_CalculateTDEEFromIC_QNAME, CalculateTDEEFromIC.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateTDEEFromICResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TDEECalc.com/", name = "calculateTDEEFromICResponse")
    public JAXBElement<CalculateTDEEFromICResponse> createCalculateTDEEFromICResponse(CalculateTDEEFromICResponse value) {
        return new JAXBElement<CalculateTDEEFromICResponse>(_CalculateTDEEFromICResponse_QNAME, CalculateTDEEFromICResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateCaloriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TDEECalc.com/", name = "calculateCaloriesResponse")
    public JAXBElement<CalculateCaloriesResponse> createCalculateCaloriesResponse(CalculateCaloriesResponse value) {
        return new JAXBElement<CalculateCaloriesResponse>(_CalculateCaloriesResponse_QNAME, CalculateCaloriesResponse.class, null, value);
    }

}
