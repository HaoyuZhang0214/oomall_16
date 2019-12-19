package xmu.oomall.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.collection.dao.CollectDao;
import xmu.oomall.collection.domain.CollectItem;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.util.ResponseUtil;

import java.util.List;

@Service
public class CollectService {
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
        if (id == null) {
            return ResponseUtil.unlogin();
        }
        if (remain > 0) {
            pagecount++;
        }
        if (page > pagecount) {
            return ResponseUtil.fail(402, "page值超过界限");
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
        return ResponseUtil.ok(subList);
    }
}
