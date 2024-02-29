package com.example.hw12.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

/*
    Структура заметки:
    - ID (автоинкрементное)(тип - Long)
    - Заголовок (не может быть пустым)(тип - String)
    - Содержимое (не может быть пустым)(тип - String)
    - Дата создания (автоматически устанавливается при создании заметки)(тип - LocalDateTime)
 */

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdDate = LocalDateTime.now();

}
