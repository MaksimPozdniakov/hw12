package com.example.hw6.controller;

import com.example.hw6.domain.Note;
import com.example.hw6.domain.NoteFactory;
import com.example.hw6.service.FileGateway;
import com.example.hw6.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final FileGateway fileGateway;
    private final NoteFactory noteFactory;


    /**
     * Метод получения всех заметок
     * @return возвращаем список заметок
     */

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }

    /**
     * Метод создания новой заметки
     * @return возвращаем объект заметки
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        note = noteFactory.createNote(note.getTitle(), note.getDescription()); // Используем фабричный метод
        fileGateway.writeToFile(note.getTitle() + ".txt", note + " - Заметка создана!");
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.OK);
    }

    /**
     * Метод поиска заметки по id
     * @param id нужной нам заметки
     * @return возвращаем найденную заметку, или постой объект, если заметка не найдена
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        fileGateway.writeToFile(noteById.getTitle() + ".txt", noteById + " - Заметка найдена!");
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * Метод изменяет заметку
     * @param id нужной нам заметки
     * @param note объект заметки
     * @return возвращаем измененную заметку
     */
    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        fileGateway.writeToFile(note.getTitle() + ".txt", note + " - Заметка изменена!");
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    /**
     * Метод удаляет заметку по id
     * @param id заметки
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        Note noteToDelete = noteService.getNoteById(id);
        noteService.deleteProduct(id);
        if (noteToDelete != null) {
            fileGateway.writeToFile(noteToDelete.getTitle() + ".txt", noteToDelete + " - Заметка удалена!"
                    + LocalDateTime.now());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
