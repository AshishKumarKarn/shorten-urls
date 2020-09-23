package karn.deloitte.shortenurls.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class UniqueLink {

    @Transient
    public static final String SEQUENCE_NAME = "uniqueLink_sequence";

    @Id
    private long id;
    private String longUrl;
    private String shortUrl;
}
