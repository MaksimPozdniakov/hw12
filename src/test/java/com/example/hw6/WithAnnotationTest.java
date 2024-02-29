package com.example.hw6;

import com.example.hw6.controller.NoteController;
import com.example.hw6.domain.Note;
import com.example.hw6.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WithAnnotationTest {
    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @Test
    public void testGetAllNotes() {

        // Arrange
        List<Note> notes = Arrays.asList(new Note(), new Note());
        Mockito.when(noteService.getAll()).thenReturn(notes);

        // Act
        ResponseEntity<List<Note>> responseEntity = noteController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(notes, responseEntity.getBody());
    }
}
