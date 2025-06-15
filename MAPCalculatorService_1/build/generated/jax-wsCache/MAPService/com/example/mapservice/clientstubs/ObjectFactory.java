
package com.example.mapservice.clientstubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.mapservice.clientstubs package. 
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

    private final static QName _CalculateMAPAndRiskResponse_QNAME = new QName("http://mapservice.example.com/", "calculateMAPAndRiskResponse");
    private final static QName _CalculateMAPAndRisk_QNAME = new QName("http://mapservice.example.com/", "calculateMAPAndRisk");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.mapservice.clientstubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateMAPAndRisk }
     * 
     */
    public CalculateMAPAndRisk createCalculateMAPAndRisk() {
        return new CalculateMAPAndRisk();
    }

    /**
     * Create an instance of {@link CalculateMAPAndRiskResponse }
     * 
     */
    public CalculateMAPAndRiskResponse createCalculateMAPAndRiskResponse() {
        return new CalculateMAPAndRiskResponse();
    }

    /**
     * Create an instance of {@link MapResult }
     * 
     */
    public MapResult createMapResult() {
        return new MapResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateMAPAndRiskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapservice.example.com/", name = "calculateMAPAndRiskResponse")
    public JAXBElement<CalculateMAPAndRiskResponse> createCalculateMAPAndRiskResponse(CalculateMAPAndRiskResponse value) {
        return new JAXBElement<CalculateMAPAndRiskResponse>(_CalculateMAPAndRiskResponse_QNAME, CalculateMAPAndRiskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateMAPAndRisk }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mapservice.example.com/", name = "calculateMAPAndRisk")
    public JAXBElement<CalculateMAPAndRisk> createCalculateMAPAndRisk(CalculateMAPAndRisk value) {
        return new JAXBElement<CalculateMAPAndRisk>(_CalculateMAPAndRisk_QNAME, CalculateMAPAndRisk.class, null, value);
    }

}
