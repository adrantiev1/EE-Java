
package dmit2015.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dmit2015.soap package. 
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

    private final static QName _LuckyLottoNumberResponse_QNAME = new QName("http://localhost/", "luckyLottoNumberResponse");
    private final static QName _LuckyLottoNumber_QNAME = new QName("http://localhost/", "luckyLottoNumber");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dmit2015.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LuckyLottoNumberResponse }
     * 
     */
    public LuckyLottoNumberResponse createLuckyLottoNumberResponse() {
        return new LuckyLottoNumberResponse();
    }

    /**
     * Create an instance of {@link LuckyLottoNumber }
     * 
     */
    public LuckyLottoNumber createLuckyLottoNumber() {
        return new LuckyLottoNumber();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LuckyLottoNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/", name = "luckyLottoNumberResponse")
    public JAXBElement<LuckyLottoNumberResponse> createLuckyLottoNumberResponse(LuckyLottoNumberResponse value) {
        return new JAXBElement<LuckyLottoNumberResponse>(_LuckyLottoNumberResponse_QNAME, LuckyLottoNumberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LuckyLottoNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/", name = "luckyLottoNumber")
    public JAXBElement<LuckyLottoNumber> createLuckyLottoNumber(LuckyLottoNumber value) {
        return new JAXBElement<LuckyLottoNumber>(_LuckyLottoNumber_QNAME, LuckyLottoNumber.class, null, value);
    }

}
