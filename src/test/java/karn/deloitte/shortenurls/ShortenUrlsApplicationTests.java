package karn.deloitte.shortenurls;

import karn.deloitte.shortenurls.pojo.UniqueLink;
import karn.deloitte.shortenurls.repositories.IUniqueLinkRepo;
import karn.deloitte.shortenurls.services.IURLProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public abstract class ShortenUrlsApplicationTests {


	private IURLProcessorService iurlProcessorService = Mockito.mock(IURLProcessorService.class);
	private IUniqueLinkRepo iUniqueLinkRepo = Mockito.mock(IUniqueLinkRepo.class);
	@BeforeEach
	public void init(){
		UniqueLink uniqueLink = new UniqueLink();
		uniqueLink.setId(1);
		uniqueLink.setLongUrl("longUrl");
		uniqueLink.setShortUrl("shortUrl");
		Mockito.when(iUniqueLinkRepo.findByLongUrl(Mockito.any())).thenReturn(uniqueLink);

	}
	@Test
	void contextLoads() {
	}

}
