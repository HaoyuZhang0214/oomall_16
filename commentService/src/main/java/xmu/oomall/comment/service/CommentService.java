package xmu.oomall.comment.service;

import org.springframework.stereotype.Service;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.domain.CommentPo;

import java.util.List;

@Service
public interface CommentService {

    public CommentPo addProductComment(Integer id, CommentPo commentPo);

    public List<Comment> getProductComment(Integer id);

    public List<Comment> getUserComment(Integer userId, Integer productId);

    public CommentPo getCommentById(Integer id);

    public CommentPo updateComment(Integer id, CommentPo commentPo);

    public Integer deleteComment(Integer id);

}
