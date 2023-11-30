package academy.prog.javapro16;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UrlRepository extends JpaRepository<UrlRecord, Long> {
    UrlRecord findByUrl(String url);
    UrlRecord deleteById(long id);

    @Query("SELECT c FROM UrlRecord c WHERE c.lastAccess < :expirationTime")
    List<UrlRecord> deleteByExpirationTimeBefore(@Param("expirationTime")Date expirationTime);
}
