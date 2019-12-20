package xmu.oomall.topic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.topic.dao.TopicDao;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;
import xmu.oomall.topic.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhy
 */
@Service
public class TopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private TopicDao topicDao;

    public Object getTopic(Integer page,Integer limit){
        if(page<0||limit<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        List<Topic> collectList =  topicDao.getTopic();
        int pagecount = collectList.size() / limit;
        int remain = collectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if(page>pagecount) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        List<Topic> subList = new ArrayList<Topic>();
        if (remain == 0) {
            subList = collectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = collectList.subList((page - 1) * limit, collectList.size());
            } else {
                subList = collectList.subList((page - 1) * limit, page * limit);
            }
        }
        if(subList.size()==0) {
            logger.debug("该话题是无效话题");
            return ResponseUtil.getFail();
        }
        return ResponseUtil.ok(subList);
    }

    public Object getTopicById(Integer id){
        return topicDao.getTopicById(id);
    }

    public Object addTopic(TopicPo topicPo){
        if(topicDao.addTopic(topicPo)==0) {
            logger.debug("话题添加失败");
            return ResponseUtil.addFail();
        }
        else {
            return ResponseUtil.ok(topicPo);
        }
    }

    public Object updateTopic(TopicPo topicPo){
        if(topicDao.updateTopic(topicPo)==0) {
            logger.debug("话题更新失败");
            return ResponseUtil.updateFail();
        }
        else {
            return ResponseUtil.ok(topicPo);
        }
    }

    public Object deleteTopic(Integer id){
        if(topicDao.deleteTopic(id)==0) {
            logger.debug("话题删除失败");
            return ResponseUtil.deleteFail();
        }
        else {
            return ResponseUtil.ok();
        }
    }
}
