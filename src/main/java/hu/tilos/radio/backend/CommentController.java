package hu.tilos.radio.backend;

import hu.tilos.radio.backend.auth.Session;
import hu.tilos.radio.backend.comment.*;
import hu.tilos.radio.backend.jwt.JwtToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class CommentController {

    @Inject
    CommentService commentService;

    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    @RequestMapping(value = "/api/v1/comment/{id}/approve", method = RequestMethod.POST)
    public CommentData approve(@PathVariable String id) {
        return commentService.approve(id);
    }

    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    @RequestMapping(value = "/api/v1/comment/{id}/approve", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        commentService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/api/v1/{typeString}/{id}/comment", method = RequestMethod.POST)
    public CreateResponse newComment(@PathVariable String id, @PathVariable String typeString, @RequestBody CommentToSave commentToSave) {
        CommentType commentType = CommentType.valueOf(typeString);
        return commentService.create(commentType, id, commentToSave, getCurrentSession());
    }

    @RequestMapping("/api/v1/comment")
    public List<CommentData> listAll(@RequestParam(value = "status", defaultValue = "") String status) {
        return commentService.listAll(status);
    }

    @RequestMapping("/api/v1/{typeString}/{id}/comment")
    public List<CommentData> list(@PathVariable String typeString, @PathVariable String id) {
        CommentType commentType = CommentType.valueOf(typeString);
        Session session = getCurrentSession();
        return commentService.list(commentType, id, getCurrentSession());
    }



    public Session getCurrentSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtToken) {
            JwtToken authToken = (JwtToken) auth;
            return authToken.getSession();
        }
        return null;
    }

}
