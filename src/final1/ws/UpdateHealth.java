
package final1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateHealth complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateHealth">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hprof" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateHealth", propOrder = {
    "hprof"
})
public class UpdateHealth {

    protected String hprof;

    /**
     * Gets the value of the hprof property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHprof() {
        return hprof;
    }

    /**
     * Sets the value of the hprof property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHprof(String value) {
        this.hprof = value;
    }

}
