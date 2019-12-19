package xmu.oomall.topic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;

import java.util.List;

@Mapper
@Repository
public interface TopicMapper {

    List<TopicPo> getTopic();

    TopicPo getTopicById(Integer id);

    int addTopic(TopicPo topicPo);

    int updateTopic(TopicPo topicPo);

    int deleteTopic(Integer id);
}
