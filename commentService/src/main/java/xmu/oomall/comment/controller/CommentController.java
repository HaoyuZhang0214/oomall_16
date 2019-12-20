package xmu.oomall.comment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.domain.CommentPo;
import xmu.oomall.comment.service.CommentService;
import xmu.oomall.comment.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/commentService")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/product/{id}/comments")
    public Object addProductComment(@PathVariable Integer id, @RequestBody CommentPo commentPo) {
        if(id<0)
            return ResponseUtil.badArgumentValue();
        CommentPo commentPo1 = commentService.addProductComment(id,commentPo);
        if(commentPo1==null)
            return ResponseUtil.fail(903,"创建评论失败");
        else
            return ResponseUtil.ok(commentPo1);
    }

    @GetMapping("/product/{id}/comments")
    public Object getProductComment(@PathVariable Integer id,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) {

        if(id<0)
            return ResponseUtil.badArgumentValue();
        List<Comment> commentList= commentService.getProductComment(id);
        int page_count=commentList.size()/limit;
        int remain=commentList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count)
            return ResponseUtil.fail(402,"page值超限");
        if(remain==0) {
            commentList=commentList.subList((page-1)*limit,page*limit);
        }
        else{
            if (page==page_count){
                commentList=commentList.subList((page-1)*limit,commentList.size());
            }else{
                commentList=commentList.subList((page-1)*limit,page*limit);
            }
        }
        if(commentList==null)
            return ResponseUtil.fail(902,"获取评论失败");
        else
            return ResponseUtil.ok(commentList);

    }


    @GetMapping("/admin/comments")
    public Object getUserComment(@RequestParam Integer userId,
                                 @RequestParam Integer productId,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit) {

        if(userId<0||productId<0)
            return ResponseUtil.badArgumentValue();
        List<Comment> commentList= commentService.getUserComment(userId,productId);
        int page_count=commentList.size()/limit;
        int remain=commentList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count)
            return ResponseUtil.fail(402,"page值超限");
        if(remain==0) {
            commentList=commentList.subList((page-1)*limit,page*limit);
        }
        else{
            if (page==page_count){
                commentList=commentList.subList((page-1)*limit,commentList.size());
            }else{
                commentList=commentList.subList((page-1)*limit,page*limit);
            }
        }
        if(commentList==null)
            return ResponseUtil.fail(902,"获取评论失败");
        else
            return ResponseUtil.ok(commentList);

    }


    @PutMapping("/admin/comments/{id}")
    public Object updateComment(@PathVariable Integer id, @RequestBody CommentPo commentPo) {

        if(id<0)
            return ResponseUtil.badArgumentValue();
        CommentPo commentPo1 = commentService.updateComment(id,commentPo);
        if(commentPo1!=null) {
            return ResponseUtil.ok(commentPo1);
        }
            return ResponseUtil.fail(904,"修改评论失败");

    }


    @DeleteMapping("/comments/{id}")
    public Object deleteComment(@PathVariable Integer id) {

        if(id<0)
            return ResponseUtil.badArgumentValue();
        if(commentService.deleteComment(id)>0)
            return ResponseUtil.ok();
        else
            return ResponseUtil.fail(905,"删除评论失败");
    }


}
