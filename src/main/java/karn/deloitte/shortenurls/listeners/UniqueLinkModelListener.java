package karn.deloitte.shortenurls.listeners;

import karn.deloitte.shortenurls.pojo.UniqueLink;
import karn.deloitte.shortenurls.services.CustomSequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

@AllArgsConstructor
public class UniqueLinkModelListener extends AbstractMongoEventListener<UniqueLink> {
    @Autowired
    private CustomSequenceGenerator sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UniqueLink> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(UniqueLink.SEQUENCE_NAME));
        }
    }
}
