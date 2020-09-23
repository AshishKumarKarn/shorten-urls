package karn.deloitte.shortenurls.services;


import karn.deloitte.shortenurls.pojo.UniqueLink;
import karn.deloitte.shortenurls.repositories.IUniqueLinkRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("URLProcessorService_v1")
@AllArgsConstructor
public class URLProcessorService implements IURLProcessorService {


    @Autowired
    private CustomSequenceGenerator customSequenceGenerator;

    @Autowired
    private URLEncoderDecoderService urlEncoderDecoderService;
    @Autowired
    private IUniqueLinkRepo iUniqueLinkRepo;
    @Override
    public UniqueLink processURL(UniqueLink uniqueLink) {
        final UniqueLink backupObject = uniqueLink;
        uniqueLink = alreadyExists(uniqueLink.getLongUrl());
        System.out.println(uniqueLink.getLongUrl()+" "+uniqueLink.getShortUrl());
        if(uniqueLink != null){
            backupObject.setShortUrl(uniqueLink.getShortUrl());
            return backupObject;
        }
        final long id = customSequenceGenerator.generateSequence(UniqueLink.SEQUENCE_NAME);
        backupObject.setId(id);
        backupObject.setShortUrl(urlEncoderDecoderService.decodeToUrl(id));
        return iUniqueLinkRepo.saveLinkToDb(backupObject);
    }

    private UniqueLink alreadyExists(String longUrl) {
      return  iUniqueLinkRepo.findByLongUrl(longUrl);
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        final long id = urlEncoderDecoderService.encodeUrlToId(shortUrl);
        System.out.println(id);
        return iUniqueLinkRepo.findByLinkId(id);
    }


}
