package no.kristiania.pgr200.http;

public enum HttpRequestMethod {
    POST,
    PUT,
    GET,
    UNKNOWN;

    public static HttpRequestMethod fromString(String requestMethodString) {
        for (HttpRequestMethod httpRequestMethod : HttpRequestMethod.values()) {
            if (httpRequestMethod.name().equalsIgnoreCase(requestMethodString)) {
                return httpRequestMethod;
            }
        }
        return UNKNOWN;
    }
}
