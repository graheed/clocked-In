package com.ClockedIn.shiftservice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shift {
    private int shiftId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Location location;
    private Enum<Course> courseEnum;
    private Map<Integer, User> labTechs;

}
