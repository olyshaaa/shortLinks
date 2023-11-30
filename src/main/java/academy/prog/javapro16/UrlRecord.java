package academy.prog.javapro16;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UrlRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String url; //ong URl
    @Column(nullable = false)
    private Long count;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastAccess;

    public UrlRecord(){
        count = 0L;
        lastAccess =  new Date();
    }

    public UrlRecord(String url){
        this();
        this.url = url;
    }

    public static UrlRecord of(UrlDto urlDto){
        return new UrlRecord(urlDto.getUrl());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public UrlStatDTO toStatDTO() {
        var result = new UrlStatDTO();

        result.setUrl(url);
        result.setShortUrl(Long.toString(id));
        result.setRedirects(count);
        result.setLastAccess(lastAccess);

        return result;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getLastAccess() {
        return lastAccess;
    }
}
