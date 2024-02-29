package com.example.hw12.domain;

import org.springframework.stereotype.Component;

@Component
public class NoteFactoryClass implements NoteFactory {
    @Override
    public Note createNote(String title, String description) {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        return note;
    }
}
