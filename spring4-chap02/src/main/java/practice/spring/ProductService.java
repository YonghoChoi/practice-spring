package practice.spring;

import org.springframework.beans.factory.annotation.Autowired;
import practice.spring.search.SearchClientFactory;

public class ProductService {
    private SearchClientFactory searchClientFactory;

    @Autowired
    public void setSearchClientFactory(SearchClientFactory searchClientFactory) {
        this.searchClientFactory = searchClientFactory;
    }
}
