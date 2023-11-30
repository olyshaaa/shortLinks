package academy.prog.javapro16;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("shorten")
    public UrlResultDto shorten(@RequestBody UrlDto urlDto){
        long id = urlService.saveUrl(urlDto);

        var result = new UrlResultDto();
        result.setUrl(urlDto.getUrl());
        result.setShortUrl(Long.toString(id));

        return result;
    }

    @GetMapping("my/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") Long id){
        var url = urlService.getUrl(id);

        var headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        headers.setCacheControl("no-cache, no-store, must-revalidate");

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    @GetMapping("my/{id}/delete")
    public void deleteLink(@PathVariable("id") Long id){
        urlService.deleteUrl(id);
    }
    @GetMapping("stat")
     public List<UrlStatDTO> stat(){
        return urlService.getStatistics();
    }



}
