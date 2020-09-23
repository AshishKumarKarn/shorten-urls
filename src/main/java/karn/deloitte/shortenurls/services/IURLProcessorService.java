package karn.deloitte.shortenurls.services;

import karn.deloitte.shortenurls.pojo.UniqueLink;

public interface IURLProcessorService {

    UniqueLink processURL(UniqueLink uniqueLink);

    String getOriginalUrl(String shortUrl);
}
