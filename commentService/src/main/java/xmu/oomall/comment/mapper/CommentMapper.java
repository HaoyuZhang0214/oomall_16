package xmu.oomall.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.domain.CommentPo;
import xmu.oomall.comment.domain.ProductPo;
import xmu.oomall.comment.domain.User;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    public Integer addProductComment(CommentPo commentPo);

    public List<CommentPo> getProductComment(Integer id);

    public User getUserById(Integer id);

    public ProductPo getProductById(Integer id);

    public CommentPo getCommentById(Integer id);

    public List<CommentPo> getUserComment(Integer userId, Integer productId);

    public Integer updateComment(CommentPo commentPo);

    public Integer deleteComment(Integer id);

}
