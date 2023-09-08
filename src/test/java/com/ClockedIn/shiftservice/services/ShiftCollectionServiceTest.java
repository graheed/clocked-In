package com.ClockedIn.shiftservice.services;

import com.ClockedIn.shiftservice.Shift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShiftCollectionServiceTest {

    @Mock
    List<Shift> shiftList;
    @InjectMocks
    ShiftCollectionService shiftCollectionService;

    @DisplayName("Test to Ensure Save Works")
    @Test
    void save() {
        //given
        Shift shiftToSave = new Shift();
        //when
        Shift savedShift = shiftCollectionService.save(shiftToSave);
        //then
        verify(shiftList).add(any(Shift.class));
        assertThat(savedShift).isNotNull();
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByFilter() {
    }

    @Test
    void delete() {
    }
}