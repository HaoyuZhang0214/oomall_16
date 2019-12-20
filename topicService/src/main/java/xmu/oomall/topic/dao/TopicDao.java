package xmu.oomall.topic.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;
import xmu.oomall.topic.mapper.TopicMapper;
import xmu.oomall.topic.util.ResponseUtil;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhy
 */
@Repository
public class TopicDao {

    private static final Logger logger = LoggerFactory.getLogger(TopicDao.class);

    @Autowired
    private TopicMapper topicMapper;

    public List<Topic> getTopic(){
        List<TopicPo> listTopic=topicMapper.getTopic();
        List<Topic> listTopic2=new ArrayList<Topic>();
        for(TopicPo topicPo:listTopic){
            Topic a=getTopic(topicPo);
            listTopic2.add(a);
        }
        return listTopic2;
    }


    public Object getTopicById(Integer id){
        TopicPo topicPo=topicMapper.getTopicById(id);
        if(topicPo==null)  {
            logger.debug("该话题是无效话题");
            return ResponseUtil.getFail();
        }
        return ResponseUtil.ok(getTopic(topicPo));
    }

    public int addTopic(TopicPo topicPo){
        return topicMapper.addTopic(topicPo);
    }

    public int updateTopic(TopicPo topicPo){

        return topicMapper.updateTopic(topicPo);
    }

    public int deleteTopic(Integer id){
        return topicMapper.deleteTopic(id);
    }

    private Topic getTopic(TopicPo topicPo){
        String url=topicPo.getPicUrlList();
        String url1=url.substring(13,url.length()-2);
        String[] str =url1.split(",");
        List<String> urllist2=new ArrayList<String>();
        for(int i=0;i<str.length;i++)  {
            urllist2.add(str[i]);
        }
        Topic a=new Topic();
        a.setPictures(urllist2);
        a.setBeDeleted(topicPo.getBeDeleted());
        a.setContent(topicPo.getContent());
        a.setGmtCreate(topicPo.getGmtCreate());
        a.setGmtModified(topicPo.getGmtModified());
        a.setId(topicPo.getId());
        a.setPicUrlList(topicPo.getPicUrlList());
        return a;
    }

}
