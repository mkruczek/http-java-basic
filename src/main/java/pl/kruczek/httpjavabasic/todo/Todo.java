package pl.kruczek.httpjavabasic.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@Table(name = "Todo")
public class Todo {

    @Id
    private UUID id;
    private String content;
    private boolean done;

    //for Hibernate
    public Todo() {
    }

    void switchDone(){
        this.done = !this.done;
    }
}
