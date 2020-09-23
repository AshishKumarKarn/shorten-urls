package karn.deloitte.shortenurls.repositories;

import karn.deloitte.shortenurls.pojo.UniqueLink;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UniqueLinkRepo implements IUniqueLinkRepo {


    private static final Logger LOGGER = LoggerFactory.getLogger(UniqueLinkRepo.class);
    @Autowired
    public MongoTemplate mongoTemplate;

    @Override
    public UniqueLink saveLinkToDb(UniqueLink uniqueLink) {
        return mongoTemplate.save(uniqueLink);
    }

    @Override
    public String findByLinkId(long id) {
        Query query = new Query(Criteria
                .where("_id").is(id));
        final UniqueLink uniqueLink = mongoTemplate.findOne(query, UniqueLink.class);
        if(uniqueLink == null)
            return null;
        LOGGER.info("short url::> {} long url::> {} id::> {} ", uniqueLink.getShortUrl(), uniqueLink.getLongUrl(), uniqueLink.getId());
        return uniqueLink.getLongUrl();
    }

    @Override
    public UniqueLink findByLongUrl(String longUrl) {
        Query query = new Query(Criteria
                .where("longUrl").is(longUrl));
        final UniqueLink uniqueLink = mongoTemplate.findOne(query, UniqueLink.class);
        if(uniqueLink == null)
            return null;
        LOGGER.info("short url::> {} long url::> {} id::> {} ", uniqueLink.getShortUrl(), uniqueLink.getLongUrl(), uniqueLink.getId());
        return uniqueLink;
    }


}
