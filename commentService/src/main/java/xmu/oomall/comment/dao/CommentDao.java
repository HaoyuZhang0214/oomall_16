package xmu.oomall.comment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.domain.CommentPo;
import xmu.oomall.comment.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private CommentMapper commentMapper;

    public CommentPo addProductComment(Integer id, CommentPo commentPo) {
        commentPo.setProductId(id);
        commentPo.setBeDeleted(false);
        commentPo.setGmtCreate(LocalDateTime.now());
        commentPo.setGmtModified(LocalDateTime.now());
        if(commentMapper.addProductComment(commentPo)>0)
            return commentPo;
        else
            return null;
    }

    public List<Comment> getProductComment(Integer id) {
        List<Comment> commentList = null;
        List<CommentPo> commentPoList = commentMapper.getProductComment(id);
        for(CommentPo commentPo: commentPoList) {
            Comment comment = new Comment();
            comment.setId(commentPo.getId());
            comment.setBeDeleted(commentPo.getBeDeleted());
            comment.setContent(commentPo.getContent());
            comment.setGmtCreate(commentPo.getGmtCreate());
            comment.setGmtModified(commentPo.getGmtModified());
            comment.setProductId(commentPo.getProductId());
            comment.setStar(commentPo.getStar());
            comment.setStatusCode(commentPo.getStatusCode());
            comment.setUserId(commentPo.getUserId());

            comment.setUser(commentMapper.getUserById(commentPo.getUserId()));
            comment.setProductPo(commentMapper.getProductById(commentPo.getProductId()));
            commentList.add(comment);
        }
        return commentList;
    }

    public List<Comment> getUserComment(Integer userId, Integer productId) {
        List<Comment> commentList = null;
        List<CommentPo> commentPoList = commentMapper.getUserComment(userId,productId);
        for(CommentPo commentPo: commentPoList) {
            Comment comment = new Comment();
            comment.setId(commentPo.getId());
            comment.setBeDeleted(commentPo.getBeDeleted());
            comment.setContent(commentPo.getContent());
            comment.setGmtCreate(commentPo.getGmtCreate());
            comment.setGmtModified(commentPo.getGmtModified());
            comment.setProductId(commentPo.getProductId());
            comment.setStar(commentPo.getStar());
            comment.setStatusCode(commentPo.getStatusCode());
            comment.setUserId(commentPo.getUserId());
            comment.setUser(commentMapper.getUserById(commentPo.getUserId()));
            comment.setProductPo(commentMapper.getProductById(commentPo.getProductId()));
            commentList.add(comment);
        }
        return commentList;
    }

    public CommentPo getCommentById(Integer id) {
        return commentMapper.getCommentById(id);
    }


    public CommentPo updateComment(Integer id, CommentPo commentPo) {
        commentPo.setId(id);
        commentPo.setGmtModified(LocalDateTime.now());
        if(commentMapper.updateComment(commentPo)>0)
            return commentPo;
        else return null;
    }

    public Integer deleteComment(Integer id) {
        return commentMapper.deleteComment(id);
    }

}
