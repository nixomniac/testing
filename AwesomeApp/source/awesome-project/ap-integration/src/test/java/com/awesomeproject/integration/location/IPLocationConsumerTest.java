package com.awesomeproject.integration.location;

import com.awesomeproject.integration.WebClientTestUtils;
import com.awesomeproject.model.location.IPLocationItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class IPLocationConsumerTest extends WebClientTestUtils {

    @InjectMocks
    private IPLocationConsumer ipLocationConsumer;

    @BeforeEach
    void init() {
        setField(ipLocationConsumer, "url", "http://localhost:8080/api");
    }

    @Test
    void getIPLocationTest() {
        String ipAddress = "11.22.33.44";
        String expectedURL = "http://localhost:8080/api/" + ipAddress + "/json";
        IPLocationItem response = IPLocationItem.builder().build();

        mockGet(response);

        var actualResponse = ipLocationConsumer.getIPLocation(ipAddress);

        verifyGetUri(expectedURL);
        assertThat(actualResponse).isSameAs(response);
    }
}
