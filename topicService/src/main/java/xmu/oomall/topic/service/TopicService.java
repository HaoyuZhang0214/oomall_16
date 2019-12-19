package xmu.oomall.topic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.topic.dao.TopicDao;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;
import xmu.oomall.topic.util.ResponseUtil;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicDao topicDao;

    public Object getTopic(Integer page,Integer limit){
        List<Topic> CollectList =  topicDao.getTopic();
        int pagecount = CollectList.size() / limit;
        int remain = CollectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if(page>pagecount) return null;
        List<Topic> subList = null;
        if (remain == 0) {
            subList = CollectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = CollectList.subList((page - 1) * limit, CollectList.size());
            } else {
                subList = CollectList.subList((page - 1) * limit, page * limit);
            }
        }
        return ResponseUtil.ok(subList);
    }

    public Object getTopicById(Integer id){
        return topicDao.getTopicById(id);
    }

    public Object addTopic(TopicPo topicPo){
        if(topicDao.addTopic(topicPo)==0) return ResponseUtil.fail(652,"话题添加失败");
        else return ResponseUtil.ok(topicPo);
    }

    public Object updateTopic(TopicPo topicPo){
        if(topicDao.updateTopic(topicPo)==0) return ResponseUtil.fail(651,"话题更新失败");
        else return ResponseUtil.ok(topicPo);
    }

    public Object deleteTopic(Integer id){
        if(topicDao.deleteTopic(id)==0) return ResponseUtil.fail(653,"话题删除失败");
        else return ResponseUtil.ok();
    }
}
