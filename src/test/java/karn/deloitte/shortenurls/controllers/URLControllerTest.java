package karn.deloitte.shortenurls.controllers;

import karn.deloitte.shortenurls.ShortenUrlsApplicationTests;
import karn.deloitte.shortenurls.pojo.UniqueLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class URLControllerTest extends ShortenUrlsApplicationTests {

@Autowired
private URLController urlController;
    @Test
    public void testProcessForm() {
        final UniqueLink uniqueLink = new UniqueLink();
        String result= urlController.processForm(uniqueLink);
        assertThat(result).isEqualTo("result");
        assertThat(uniqueLink).isNotNull();
    }

}
