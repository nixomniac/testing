package com.awesomeproject.integration.location;

import com.awesomeproject.integration.CommonRestConsumer;
import com.awesomeproject.model.location.IPLocationItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class IPLocationConsumer extends CommonRestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPLocationConsumer.class);

    private final String url;

    public IPLocationConsumer(@Value("${rest-api.base-url.ip-location}") final String url) {
        this.url = url;
    }

    public IPLocationItem getIPLocation(String ipAddress) {
        LOGGER.info("Getting location for the IP={}", ipAddress);

        URI uri = fromHttpUrl(url)
                .path("/{ipAddress}/json")
                .build(ipAddress);

        return getRequest(uri, IPLocationItem.class);
    }
}
