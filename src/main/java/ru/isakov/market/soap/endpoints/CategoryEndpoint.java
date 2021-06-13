package ru.isakov.market.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.isakov.market.soap.entitiesWs.categoriesWs.GetCategoryByTitleRequest;
import ru.isakov.market.soap.entitiesWs.categoriesWs.GetCategoryByTitleResponse;
import ru.isakov.market.soap.services.CategoryWsService;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://ru.isakov.market/spring/market/ws/categoriesWs";
    private final CategoryWsService categoryWsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getGroupByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        response.setCategory(categoryWsService.getByCategoryTitle(request.getTitle()));
        return response;
    }
}
