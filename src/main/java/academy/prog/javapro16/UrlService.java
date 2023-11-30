package academy.prog.javapro16;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@EnableScheduling
public class UrlService {
    private final UrlRepository urlRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlService.class);

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @Transactional
    public long saveUrl(UrlDto urlDto){
        var urlRecord = urlRepository.findByUrl(urlDto.getUrl());
        if (urlRecord==null ){
            urlRecord = urlRecord.of(urlDto);
            urlRepository.save(urlRecord);
        }

        return urlRecord.getId();
    }
    @Transactional
    public String getUrl(long id){
        var urlOpt = urlRepository.findById(id);
        if(urlOpt.isEmpty())
            return null;

        var urlRecord = urlOpt.get();

        urlRecord.setCount(urlRecord.getCount() + 1);
        urlRecord.setLastAccess((new Date()));

        return urlRecord.getUrl();
    }
    @Transactional(readOnly = true)
    public List<UrlStatDTO> getStatistics(){
        var records = urlRepository.findAll();
        var result = new ArrayList<UrlStatDTO>();

        records.forEach(x -> result.add(x.toStatDTO()));

        return result;
    }

    public void deleteUrl(Long id){
        urlRepository.deleteById(id);
    }
    @Scheduled(fixedDelay = 10000)
    public void deleteExpiredLinks() {
        LOGGER.info("Deleting expired links");
        List<UrlRecord> urlList = urlRepository.deleteByExpirationTimeBefore(new Date());
        urlList.forEach(x -> deleteUrl(x.getId()));
        LOGGER.info("Deletion complete");
    }
}
