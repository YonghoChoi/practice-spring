package practice.spring.search;

public class SearchClientFactoryBuilder {
    String server;
    int port;
    String contentyType;
    String encoding;

    public SearchClientFactoryBuilder server(String server) {
        this.server = server;
        return this;
    }

    public SearchClientFactoryBuilder port(int port) {
        this.port = port;
        return this;
    }

    public SearchClientFactoryBuilder contentyType(String contentyType) {
        this.contentyType = contentyType;
        return this;
    }

    public SearchClientFactoryBuilder encoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public SearchClientFactory build() {
        return new SearchClientFactory(server, port, contentyType, encoding);
    }
}
