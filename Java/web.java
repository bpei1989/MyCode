1. SAOP web service
普通SOAP方式
a. import java.xml.soap.*； java.xml.ws.Service; java.xml.ws.Dispatch
b. 指定url和wsdl
	String ns = "http://axisversion.sample";  
	String wsdlUrl = "http://127.0.0.1:8080/axis2/services/Version?wsdl";  
c. 创建Service
            URL url=new URL(wsdlUrl); 
            QName qname=new QName(ns,"MyServiceImplService"); 
            Service service=Service.create(url,qname); 
d. 创建Dispatch（用于inovke SOAPMessage）
Dispatch<SOAPMessage> dispatch=service.createDispatch(new QName(ns,"MyServiceImplPort"), 
                       SOAPMessage.class, Service.Mode.MESSAGE)；
e. 创建SOAP消息
	i. 用MessageFactory新建SOAPMessage
	ii. 从SOAPMessage中get到Body
	iii. 往body中addChildElement
f. Dispatch inovke消息，返回response
g. 处理response

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.saop.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import org.w3c.dom.Document;

public class Receive {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
            URL url=new URL(wsdlUrl); 
            QName qname=new QName(ns,"MyServiceImplService"); 
            Service service=Service.create(url,qname); 
              
//          2，创建Dispicathc 
              
            Dispatch<SOAPMessage> dispatch=service.createDispatch(new QName(ns,"MyServiceImplPort"), 
                    SOAPMessage.class, Service.Mode.MESSAGE); 
//          3，创建消息 
            SOAPMessage message=MessageFactory.newInstance().createMessage(); 
            SOAPBody body =message.getSOAPPart().getEnvelope().getBody(); 
            SOAPElement ele=body.addChildElement(new QName(ns,"add","nn")); 
            ele.addChildElement("number1").setValue("12"); 
            ele.addChildElement("number2").setValue("13"); 
              
            SOAPMessage response=dispatch.invoke(message); 
            //response.writeTo(System.out); 
            NodeList nodeList=response.getSOAPBody().getElementsByTagName("result"); 
            Node node=nodeList.item(0); 
            System.out.println(node.getTextContent()); 
	}
}

QName:
<wsdl:definitions name="Helloworld" targetNamespace="http://server.com/" xmlns:ns1="http://schemas.xmlsoap.org/soap/http" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://server.com/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
.....
<wsdl:portTypename="IHelloWorldService">
<wsdl:operation name="sayHello">
<wsdl:inputmessage="tns:sayHello" name="sayHello" />
<wsdl:outputmessage="tns:sayHelloResponse" name="sayHelloResponse" />
</wsdl:operation>
</wsdl:portType>
</wsdl:definitions>
以wsdl:portType为例：wsdl是名字空间前缀，portTpye是元素名称，wsdl:portType就是一个qname，其namespace是：http://server.com/