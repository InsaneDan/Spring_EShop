package ru.isakov.market.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.isakov.market.soap.products.*;
import ru.isakov.market.soap.services.ProductWsService;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://ru.isakov.market/spring/market/ws/productsWs";
    private final ProductWsService productWsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductResponse getProductByTitle(@RequestPayload GetProductByTitleRequest req) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productWsService.getProductByTitle(req.getTitle()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductResponse getProductById(@RequestPayload GetProductByIdRequest req) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productWsService.getProductById(req.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsRequest req) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productWsService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}

/*
-------------------------------- getProductByIdRequest ----------------------------------

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:p="http://ru.isakov.market/spring/market/ws/productsWs">
    <soapenv:Header/>
    <soapenv:Body>
        <p:getProductByIdRequest>
            <p:id>1</p:id>
        </p:getProductByIdRequest>
    </soapenv:Body>
</soapenv:Envelope>

-------------------------------- Response for getProductByIdRequest ---------------------

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getProductResponse xmlns:ns2="http://ru.isakov.market/spring/market/ws/productsWs">
            <ns2:product>
                <ns2:id>1</ns2:id>
                <ns2:title>Bread</ns2:title>
                <ns2:price>25.50</ns2:price>
            </ns2:product>
        </ns2:getProductResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

-------------------------------- getProductByTitleRequest --------------------------------

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:p="http://ru.isakov.market/spring/market/ws/productsWs">
    <soapenv:Header/>
    <soapenv:Body>
        <p:getProductByTitleRequest>
            <p:title>Milk</p:title>
        </p:getProductByTitleRequest>
    </soapenv:Body>
</soapenv:Envelope>

-------------------------------- Response for getProductByTitleRequest ------------------

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getProductResponse xmlns:ns2="http://ru.isakov.market/spring/market/ws/productsWs">
            <ns2:product>
                <ns2:id>2</ns2:id>
                <ns2:title>Milk</ns2:title>
                <ns2:price>80.40</ns2:price>
            </ns2:product>
        </ns2:getProductResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

-------------------------------- getAllProductsRequest ----------------------------------

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:p="http://ru.isakov.market/spring/market/ws/productsWs">
    <soapenv:Header/>
    <soapenv:Body>
        <p:getAllProductsRequest/>
    </soapenv:Body>
</soapenv:Envelope>

-------------------------------- Response for getAllProductsRequest ---------------------

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getAllProductsResponse xmlns:ns2="http://ru.isakov.market/spring/market/ws/productsWs">
            <ns2:products>
                <ns2:id>1</ns2:id>
                <ns2:title>Bread</ns2:title>
                <ns2:price>25.50</ns2:price>
            </ns2:products>
            <ns2:products>
                <ns2:id>2</ns2:id>
                <ns2:title>Milk</ns2:title>
                <ns2:price>80.40</ns2:price>
            </ns2:products>
            <ns2:products>
                <ns2:id>3</ns2:id>
                <ns2:title>Cheese</ns2:title>
                <ns2:price>325.00</ns2:price>
            </ns2:products>
        </ns2:getAllProductsResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

 */