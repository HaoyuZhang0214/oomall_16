package xmu.oomall.comment.domain;



import lombok.EqualsAndHashCode;

import lombok.Getter;

import lombok.Setter;

import lombok.ToString;



/**

 * @Author: 数据库与对象模型标准组

 * @Description:评论对象

 * @Data:Created in 14:50 2019/12/11

 **/

@Getter

@Setter

@ToString

@EqualsAndHashCode(callSuper = true)

public class Comment extends CommentPo {



    private User user;



    private ProductPo productPo;

}