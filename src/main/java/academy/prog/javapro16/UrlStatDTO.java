package academy.prog.javapro16;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UrlStatDTO extends UrlResultDto{
    private long redirects;

    // TODO: set date format to Kyiv
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Kyiv")
    private Date lastAccess;

    public long getRedirects() {
        return redirects;
    }

    public void setRedirects(long redirects) {
        this.redirects = redirects;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
}
