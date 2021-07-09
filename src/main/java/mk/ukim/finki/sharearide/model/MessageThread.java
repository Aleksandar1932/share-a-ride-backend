package mk.ukim.finki.sharearide.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageThread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // TOOD: Implement in phase 2
}
