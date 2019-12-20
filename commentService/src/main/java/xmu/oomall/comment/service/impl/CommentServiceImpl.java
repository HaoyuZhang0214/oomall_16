package xmu.oomall.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.comment.dao.CommentDao;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.domain.CommentPo;
import xmu.oomall.comment.service.CommentService;

import java.util.List;

/**
 * @author zhy
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public CommentPo addProductComment(Integer id, CommentPo commentPo) {
        return commentDao.addProductComment(id,commentPo);
    }

    @Override
    public List<Comment> getProductComment(Integer id) {
        return commentDao.getProductComment(id);
    }

    @Override
    public List<Comment> getUserComment(Integer userId, Integer productId) {
        return commentDao.getUserComment(userId, productId);
    }

    @Override
    public CommentPo updateComment(Integer id, CommentPo commentPo) {
        return commentDao.updateComment(id,commentPo);
    }

    @Override
    public CommentPo getCommentById(Integer id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public Integer deleteComment(Integer id) {
        return commentDao.deleteComment(id);
    }

}
