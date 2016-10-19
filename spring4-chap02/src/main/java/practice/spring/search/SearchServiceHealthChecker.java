package practice.spring.search;

import java.util.List;

public class SearchServiceHealthChecker {
    List<SearchClientFactory> factories;

    public SearchServiceHealthChecker() {
    }

    public void setFactories(List<SearchClientFactory> factories) {
        this.factories = factories;
    }

    public List<SearchClientFactory> getFactories() {
        return factories;
    }
}
