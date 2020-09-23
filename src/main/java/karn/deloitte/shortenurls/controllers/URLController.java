package karn.deloitte.shortenurls.controllers;

import karn.deloitte.shortenurls.pojo.UniqueLink;
import karn.deloitte.shortenurls.services.IURLProcessorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.net.URI;


//http://localhost:8080/addURL

/**
 * Controller class to handle requests
 *
 * @author Ashish Karn
 */
@Controller
@AllArgsConstructor
public class URLController {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private static final String RESULT = "result";
    private static final String ADD_AND_PROCESS_RESULT = "addAndProcessURL";
    @Autowired
    @Qualifier("URLProcessorService_v1")
    private IURLProcessorService iurlProcessorService;

    /**
     * Mapping to redirect the request to welcome page.
     */
    @GetMapping("/addURL")
    public String sendForm(UniqueLink uniqueLink) {
        return ADD_AND_PROCESS_RESULT;
    }

    /**
     * Method map incoming POST requests with URL to our processor
     * where we can process the request and get the short url version.
     */
    @PostMapping("/addURL")
    public String processForm(UniqueLink uniqueLink) {
        final String longUrl = uniqueLink.getLongUrl();
        LOGGER.info("processing url : {}", longUrl);
        iurlProcessorService.processURL(uniqueLink);
        return RESULT;
    }

    @GetMapping("/getBackLongURL")
    public ResponseEntity<String> getBackLongURL(@RequestParam("shortUrl") String shortUrl) {
        LOGGER.info("shortUrl:>>{}",shortUrl);
        String url = iurlProcessorService.getOriginalUrl(shortUrl);
        if (url == null)
            ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.FOUND).body(url);
    }


}
