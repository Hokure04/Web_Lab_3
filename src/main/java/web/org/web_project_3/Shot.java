package web.org.web_project_3;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor @Getter @ToString
public class Shot implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) @Setter
    private @NotNull Double x;
    @Column(nullable = false) @Setter
    private @NotNull Double y;
    @Column(nullable = false) @Setter
    private @NotNull Double r;
    @Column(nullable = false)
    private boolean inArea;
    @Column(nullable = false)
    private String shotTime;
    @Column(nullable = false)
    private long executionTime;

    public Shot(Shot shot) {
        this.x = shot.x;
        this.y = shot.y;
        this.r = shot.r;
    }

    @PrePersist
    protected void prePersist() {
        long start = System.nanoTime();
        this.shotTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.inArea = checkHit();
        this.executionTime = System.nanoTime()-start ;
    }

    private boolean checkHit() {
        boolean area1 = x <= 0 && y >= 0 && x >= -r/2 && y <= r;
        boolean area2 = x >= 0 && y <= 0 && x*x + y*y  <= r*r;
        boolean area3 = x <= 0 && y <= 0 && y >= -x - r;
        return area1 || area2 || area3;
    }
}
