package com.example.hw6;


import com.example.hw6.domain.Note;
import com.example.hw6.repository.NoteRepository;
import com.example.hw6.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IntegrationTest {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void testCreateNote() {

        // Arrange
        Note note = new Note();
        note.setTitle("Test Title");
        note.setDescription("Test Description");

        // Act
        Note createdNote = noteService.createNote(note);

        // Assert
        assertNotNull(createdNote.getId());
        assertEquals(note.getTitle(), createdNote.getTitle());
        assertEquals(note.getDescription(), createdNote.getDescription());

        // Clean up
        noteRepository.deleteById(createdNote.getId());
    }
}
