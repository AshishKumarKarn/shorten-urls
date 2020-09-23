package karn.deloitte.shortenurls.controllers;

import karn.deloitte.shortenurls.ShortenUrlsApplicationTests;
import karn.deloitte.shortenurls.pojo.UniqueLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class URLControllerTest extends ShortenUrlsApplicationTests {

    /**
     * Asserts user gets the form page html to enter url to be shortened.
     * */
    @Test
    public void testSendForm() {
        final UniqueLink uniqueLink = new UniqueLink();
        String result= urlController.sendForm(uniqueLink);
        assertThat(result).isEqualTo("addAndProcessURL");
    }

    /**
     * Asserts user gets the result page html with url shortened
     * */
    @Test
    public void testProcessForm() {
        final UniqueLink uniqueLink = new UniqueLink();
        uniqueLink.setLongUrl("someLongUrl");
        String result= urlController.processForm(uniqueLink);
        assertThat(uniqueLink).isNotNull();
        assertThat(result).isEqualTo("result");
        assertThat(uniqueLink.getShortUrl()).isEqualTo("shortUrl");
    }

    /**
     * Asserts user gets back the long URL by providing the shortened URL
     * */
    @Test
    public void testGetBackLongURL() {
        final UniqueLink uniqueLink = new UniqueLink();
        uniqueLink.setShortUrl("someLongUrl");
        ResponseEntity<String> result= urlController.getBackLongURL(uniqueLink.getShortUrl());
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(result.getBody()).isEqualTo("longUrl");
    }


}
