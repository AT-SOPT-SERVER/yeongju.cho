package org.sopt.post.core.domain;

import jakarta.persistence.*;
import org.sopt.common.constants.PostTableConstants;

@Entity
@Table(
        name = PostTableConstants.TABLE_POST,
        indexes = {
                @Index(name = "uk1", columnList = "title", unique = true)
        }
)
public class PostEntity {

    @Id
    @Column(name = PostTableConstants.COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name=PostTableConstants.COLUMN_TITLE)
    private String title;

    protected PostEntity(){

    }

    public PostEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public PostEntity(String title) {
        this.title = title;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}