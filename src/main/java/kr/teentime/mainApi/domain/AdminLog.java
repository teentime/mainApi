package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminLog extends BasicEntity {

    @Id @Column(name = "admin_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String log;

    public static AdminLog addLog(String func) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy년 mm월 dd일");
        ZoneId zone = ZoneId.of("Asia/Seoul");
        String now = LocalDateTime.now().atZone(zone).format(format);
        String logMsg = now + " - ["+ func +"]";

        return AdminLog.builder()
                .log(logMsg)
                .build();
    }
}
