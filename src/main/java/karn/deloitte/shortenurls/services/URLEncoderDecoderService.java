package karn.deloitte.shortenurls.services;

import org.springframework.stereotype.Service;

@Service
public class URLEncoderDecoderService {
    private static final char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    //Gets shorT url from link id using map
    public String decodeToUrl(long linkId)
    {
        StringBuffer shortUrl = new StringBuffer();
        while (linkId > 0)
        {
            shortUrl.append(map[(int) (linkId % 62)]);
            linkId = linkId / 62;
        }
        return shortUrl.reverse().toString();
    }

    //link Id back from a shortUrl
    public int encodeUrlToId(String URL)
    {
        int id = 0;
        for (int i = 0; i < URL.length(); i++)
        {
            if ('a' <= URL.charAt(i) && URL.charAt(i) <= 'z')
                id = id * 62 + URL.charAt(i) - 'a';
            else if ('A' <= URL.charAt(i) && URL.charAt(i) <= 'Z')
                id = id * 62 + URL.charAt(i) - 'A' + 26;
            else if ('0' <= URL.charAt(i) && URL.charAt(i) <= '9')
                id = id * 62 + URL.charAt(i) - '0' + 52;
        }
        return id;
    }
}
