package com.ClockedIn.shiftservice.services;

import com.ClockedIn.Course;
import com.ClockedIn.shiftservice.Shift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShiftCollectionServiceTest {

    @Mock
    Map<Long, Shift> shiftHashMap;
    @InjectMocks
    ShiftCollectionService shiftCollectionService;
    Shift shift1;
    Shift shift2;
    Shift shiftUpdate;

    @BeforeEach
    void setUp() {
        shift1 = Shift.builder().shiftId(1L).courseEnum(Course.COMP1126).build();
        shift2 = Shift.builder().shiftId(2L).courseEnum(Course.COMP1126).build();
        shiftUpdate = Shift.builder().shiftId(2L).courseEnum(Course.COMP3161).build();
    }

    @DisplayName("Test to Ensure Save Works")
    @Test
    void save() {
        //when
        when(shiftHashMap.put(shift1.getShiftId(), shift1)).thenReturn(shift1);
        Shift savedShift = shiftCollectionService.save(shift1);
        //then
        verify(shiftHashMap).put(anyLong(), any(Shift.class));
        assertEquals(savedShift, shift1);
    }

    @DisplayName("Test to Ensure Updating via Save Functionality Works")
    @Test
    void update() {
        //when
//        when(shiftHashMap.put(shift2.getShiftId(), shift2)).thenReturn(shift2);
        Shift initialSaveShift = shiftCollectionService.save(shift2);
        Shift savedShift = shiftCollectionService.save(shiftUpdate);
        //then
        verify(shiftHashMap, times(2)).put(eq(2L), any(Shift.class));
        assertEquals(Course.COMP3161, savedShift.getCourseEnum());
    }

    @DisplayName("Find a Shift Based on ID")
    @Test
    void findById() {
        Shift shift = Shift.builder().build();
        when(shiftHashMap.get(anyLong())).thenReturn(shift);

        Optional<Shift> foundShift = shiftCollectionService.findById(1L);

        verify(shiftHashMap).get(1L);
        assertThat(foundShift).isNotNull();
    }

    @DisplayName("Find All the Shifts")
    @Test
    void findAll() {
        Shift shift = new Shift();
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shift);

        when(shiftHashMap.values()).thenReturn(shifts);

        Iterable<Shift> foundShifts = shiftCollectionService.findAll();

        assertThat(foundShifts).hasSize(1);
        verify(shiftHashMap).values();
    }

    @DisplayName("Find Shifts Filtered By Course")
    @Test
    void findShiftsByCourse() {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shift1);
        shifts.add(shift2);

        when(shiftHashMap.values()).thenReturn(shifts);

        Iterable<Shift> foundShifts = shiftCollectionService.findShiftsByCourse(Course.COMP1126);

        assertThat(foundShifts).hasSize(2);
        verify(shiftHashMap).values();
    }

    @DisplayName("Delete Shift Based on ID")
    @Test
    void delete() {
        shiftCollectionService.delete(anyLong());

        verify(shiftHashMap).remove(anyLong());
    }
}