package karn.deloitte.shortenurls.repositories;


import karn.deloitte.shortenurls.pojo.UniqueLink;

public interface IUniqueLinkRepo {

    UniqueLink saveLinkToDb(UniqueLink uniqueLink);

    String findByLinkId(long id);

    UniqueLink findByLongUrl(String longLink);


}
