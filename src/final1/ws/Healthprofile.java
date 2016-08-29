
package final1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for healthprofile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="healthprofile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datecreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pid" type="{http://ws.final1/}person" minOccurs="0"/>
 *         &lt;element name="tid" type="{http://ws.final1/}type" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "healthprofile", propOrder = {
    "datecreated",
    "hid",
    "pid",
    "tid",
    "value"
})
public class Healthprofile {

    protected String datecreated;
    protected Integer hid;
    protected Person pid;
    protected Type tid;
    protected String value;

    /**
     * Gets the value of the datecreated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatecreated() {
        return datecreated;
    }

    /**
     * Sets the value of the datecreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatecreated(String value) {
        this.datecreated = value;
    }

    /**
     * Gets the value of the hid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHid() {
        return hid;
    }

    /**
     * Sets the value of the hid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHid(Integer value) {
        this.hid = value;
    }

    /**
     * Gets the value of the pid property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setPid(Person value) {
        this.pid = value;
    }

    /**
     * Gets the value of the tid property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getTid() {
        return tid;
    }

    /**
     * Sets the value of the tid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setTid(Type value) {
        this.tid = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
