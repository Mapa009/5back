package kr.co.yooooon.base.to;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name="NEW_BOARD3")
public class BoardTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String boardSeq;
    private String author;
    private String title;
    private String content;
    private String today;
    private String mm;
    private String dd;
    private String refSeq;
}