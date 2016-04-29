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
	iii. 往body中addChildElement，即