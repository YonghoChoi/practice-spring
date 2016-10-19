package practice.spring.search;

public class SearchClientFactory {
    String server;
    int port;
    String contentyType;
    String encoding;

    public SearchClientFactory(String server, int port, String contentyType, String encoding) {
        this.server = server;
        this.port = port;
        this.contentyType = contentyType;
        this.encoding = encoding;
    }

    public SearchClient getObject() {
        return new SearchClient();
    }

    public void init() {
    }
}
