package karn.deloitte.shortenurls;

import karn.deloitte.shortenurls.controllers.URLController;
import karn.deloitte.shortenurls.pojo.UniqueLink;
import karn.deloitte.shortenurls.repositories.IUniqueLinkRepo;
import karn.deloitte.shortenurls.repositories.UniqueLinkRepo;
import karn.deloitte.shortenurls.services.CustomSequenceGenerator;
import karn.deloitte.shortenurls.services.URLEncoderDecoderService;
import karn.deloitte.shortenurls.services.URLProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public abstract class ShortenUrlsApplicationTests {

    private MongoOperations mongoOperations = Mockito.mock(MongoOperations.class);
    protected IUniqueLinkRepo iUniqueLinkRepo = Mockito.mock(UniqueLinkRepo.class);
    protected CustomSequenceGenerator customSequenceGenerator = new CustomSequenceGenerator(mongoOperations);
    protected URLEncoderDecoderService urlEncoderDecoderService = new URLEncoderDecoderService();
    protected URLProcessorService urlProcessorService = new URLProcessorService(customSequenceGenerator, urlEncoderDecoderService, iUniqueLinkRepo);
    protected URLController urlController = new URLController(urlProcessorService);

    @BeforeEach
    public void init() {
        UniqueLink uniqueLink = new UniqueLink();
        uniqueLink.setId(1);
        uniqueLink.setLongUrl("longUrl");
        uniqueLink.setShortUrl("shortUrl");
        Mockito.when(iUniqueLinkRepo.findByLongUrl(Mockito.any(String.class))).thenReturn(uniqueLink);
        Mockito.when(iUniqueLinkRepo.saveLinkToDb(Mockito.any(UniqueLink.class))).thenReturn(uniqueLink);
        Mockito.when(iUniqueLinkRepo.findByLinkId(Mockito.any(Long.class))).thenReturn(uniqueLink.getLongUrl());
        Mockito.when(mongoOperations.findAndModify(Mockito.any(Query.class),
                Mockito.any(Update.class),Mockito.any(FindAndModifyOptions.class),
                Mockito.any(Class.class))).thenReturn(uniqueLink.getId()+1);
    }

    @Test
    void contextLoads() {
    }

}
