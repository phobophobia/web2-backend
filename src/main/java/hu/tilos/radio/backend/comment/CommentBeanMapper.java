package hu.tilos.radio.backend.comment;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class CommentBeanMapper {

    @Inject
    StrictHTMLSanitizer sanitizer;

    public CommentData map(DBObject mongoObject, Class<CommentData> commentDataClass) {
        CommentData comment = new CommentData();
        comment.setComment(sanitizer.clean((String) mongoObject.get("comment")));
        comment.setCreated((Date) mongoObject.get("created"));
        comment.setStatus(CommentStatus.values()[(int) mongoObject.get("status")]);
        comment.setType(CommentType.EPISODE);
        comment.setId(((ObjectId) mongoObject.get("_id")).toHexString());
        BasicDBObject author = (BasicDBObject) mongoObject.get("author");
        if (author != null) {
            UserLink userLink = new UserLink();
            userLink.setUsername((String) author.get("username"));
            comment.setAuthor(userLink);
        }
        return comment;
    }

    public BasicDBObject map(CommentToSave data, Class<BasicDBObject> basicDBObjectClass) {
        BasicDBObject db = new BasicDBObject();
        db.put("comment", sanitizer.clean(data.getComment()));
        db.put("created", new Date());
        db.put("parentId", sanitizer.clean(data.getParentId()));
        return db;
    }
}
