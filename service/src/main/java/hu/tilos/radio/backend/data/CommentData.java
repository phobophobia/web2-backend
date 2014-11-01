package hu.tilos.radio.backend.data;

import hu.radio.tilos.model.type.CommentStatus;
import hu.radio.tilos.model.type.CommentType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentData {

    private int id;

    private List<CommentData> children = new ArrayList<>();

    private CommentType type;

    private String comment;

    private Date moment;

    private UserLink author;

    private Date created;

    private CommentStatus status;

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CommentData> getChildren() {
        return children;
    }

    public void setChildren(List<CommentData> children) {
        this.children = children;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public UserLink getAuthor() {
        return author;
    }

    public void setAuthor(UserLink author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
