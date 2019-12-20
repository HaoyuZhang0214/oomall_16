package xmu.oomall.topic.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;
import xmu.oomall.topic.mapper.TopicMapper;
import xmu.oomall.topic.util.ResponseUtil;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TopicDao {
    @Autowired
    private TopicMapper topicMapper;

    public List<Topic> getTopic(){
        List<TopicPo> listTopic=topicMapper.getTopic();
        List<Topic> listTopic2=new ArrayList<Topic>();
        for(TopicPo topicPo:listTopic){
            Topic A=getTopic(topicPo);
            listTopic2.add(A);
        }
        return listTopic2;
    }


    public Object getTopicById(Integer id){
        TopicPo topicPo=topicMapper.getTopicById(id);
        if(topicPo==null)  return ResponseUtil.fail(650,"该话题是无效话题");
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
        String a[]=url1.split(",");
        List<String> urllist2=new ArrayList<String>();
        for(int i=0;i<a.length;i++) urllist2.add(a[i]);
        Topic A=new Topic();
        A.setPictures(urllist2);
        A.setBeDeleted(topicPo.getBeDeleted());
        A.setContent(topicPo.getContent());
        A.setGmtCreate(topicPo.getGmtCreate());
        A.setGmtModified(topicPo.getGmtModified());
        A.setId(topicPo.getId());
        A.setPicUrlList(topicPo.getPicUrlList());
        return A;
    }

}
