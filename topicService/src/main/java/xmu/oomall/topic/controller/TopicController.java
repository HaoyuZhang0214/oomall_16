package xmu.oomall.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.topic.domain.Topic;
import xmu.oomall.topic.domain.TopicPo;
import xmu.oomall.topic.service.TopicService;
import xmu.oomall.topic.util.ResponseUtil;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicService")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/admin/topics")
    public Object adGetTopic(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer limit){
        return ResponseUtil.ok(topicService.getTopic(page, limit));
    }

    @GetMapping("/admin/topics/{id}")
    public Object adGetTopicById(@PathVariable Integer id){
        return topicService.getTopicById(id);
    }

    @GetMapping("/topics")
    public Object GetTopic(@RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit){
        return ResponseUtil.ok(topicService.getTopic(page, limit));
    }
    @GetMapping("/topics/{id}")
    public Object getTopicById(@PathVariable Integer id){
        return topicService.getTopicById(id);
    }

    @PostMapping("/topics")
    public Object addTopic(@RequestBody TopicPo topicPo){
        LocalDateTime time=LocalDateTime.now();
        topicPo.setBeDeleted(false);
        topicPo.setGmtCreate(time);
        topicPo.setGmtModified(time);
        return topicService.addTopic(topicPo);
    }

    @PutMapping("/topics/{id}")
    public Object updateTopicById(@PathVariable Integer id,@RequestBody TopicPo topicPo ){
        topicPo.setId(id);
        LocalDateTime time=LocalDateTime.now();
        topicPo.setGmtModified(time);
        return topicService.updateTopic(topicPo);
    }

    @DeleteMapping("/topics/{id}")
    public Object deleteTopic(@PathVariable Integer id){
        return topicService.deleteTopic(id);
    }
}
