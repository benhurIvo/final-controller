
package final1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveGoal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saveGoal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveGoal", propOrder = {
    "gol"
})
public class SaveGoal {

    protected String gol;

    /**
     * Gets the value of the gol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGol() {
        return gol;
    }

    /**
     * Sets the value of the gol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGol(String value) {
        this.gol = value;
    }

}
