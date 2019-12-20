package xmu.oomall.collection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.collection.dao.CollectDao;
import xmu.oomall.collection.domain.CollectItem;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.util.ResponseUtil;

import java.util.List;


@Service
public class CollectService {

    private static final Logger logger = LoggerFactory.getLogger(CollectService.class);

    @Autowired
    private CollectDao collectDao;

    public CollectItemPo addCollect(CollectItemPo collectItem) {
        return collectDao.addCollect(collectItem);
    }

    public Integer deleteCollectById(Integer id) {
         return collectDao.deleteCollectById(id);
    }

    public Object listCollectByCodition(Integer userId)
    {
        return collectDao.listcollect(userId);
    }

    public Object listCollect(Integer id, Integer page, Integer limit) {

        List<CollectItem> CollectList = collectDao.listcollect(id);
        int pagecount = CollectList.size() / limit;
        int remain = CollectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if (page > pagecount) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        List<CollectItem> subList = null;
        if (remain == 0) {
            subList = CollectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = CollectList.subList((page - 1) * limit, CollectList.size());
            } else {
                subList = CollectList.subList((page - 1) * limit, page * limit);
            }
        }
        if(subList.size()==0) {
            logger.debug("收藏不存在");
            return ResponseUtil.getFail();
        }
        return ResponseUtil.ok(subList);
    }
}
