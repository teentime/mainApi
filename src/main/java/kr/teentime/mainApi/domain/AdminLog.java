package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminLog extends BasicEntity {

    @Id @Column(name = "admin_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id",
            nullable = false)
    private Club club;

    @Builder
    public AdminLog(String log, Club club) {
        this.log = log;
        this.club = club;
    }

    public static AdminLog addLog(String func, Club club) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
        ZoneId zone = ZoneId.of("Asia/Seoul");
        String now = LocalDateTime.now().atZone(zone).format(format);
        String logMsg = now + " - ["+ func +"]";

        return AdminLog.builder()
                .club(club)
                .log(logMsg)
                .build();
    }
}
