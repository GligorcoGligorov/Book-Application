package com.example.emt_lab.events;

import com.example.emt_lab.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class BookCreatedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public BookCreatedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public BookCreatedEvent(Object source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

    public BookCreatedEvent(Object source, Clock clock, LocalDateTime when) {
        super(source, clock);
        this.when = when;
    }
}
