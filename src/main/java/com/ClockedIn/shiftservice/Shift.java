package com.ClockedIn.shiftservice;
import com.ClockedIn.Course;
import com.ClockedIn.Location;
import com.ClockedIn.User;
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
    private Long shiftId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Location location;
    private Enum<Course> courseEnum;
    private Map<Integer, User> labTechs;


}
