package com.xf.service.impl;

import com.xf.domain.Purchasebillitem;
import com.xf.domain.vo.ItemChartVo;
import com.xf.domain.vo.PurchaseBillItemVo;
import com.xf.query.PurchasebillitemQuery;
import com.xf.repository.PurchasebillitemRepository;
import com.xf.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 */
@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService {

    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;

    @Override
    public List<PurchaseBillItemVo> findItems(PurchasebillitemQuery query) {
        //获取到所有数据
        List<Purchasebillitem> items = purchasebillitemRepository.findByQuery(query);
        //准备vo集合，用到装数据
        List<PurchaseBillItemVo> vos = new ArrayList<>();
        for (Purchasebillitem item : items) {
            //把item变成vo，放到vos里面
            PurchaseBillItemVo vo = new PurchaseBillItemVo(item,query);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * List<Map>
     *     [{name:成都供应商,y:455},{name:哈哈供应商,y:255},...]
     * @param query
     * @return
     */
    @Override
    public List<ItemChartVo> findCharts(PurchasebillitemQuery query) {

       //1.获取到分组的条件
        String groupByStr = query.getGroupByStr();
        //2.获取到相应的JPQL
        String whereJpql = query.getWhereJpql();
        //3.获取到JPQL中?对应的值
        List<Object> params = query.getParams();
        //注意;这里必需使用全限定名
        String jpql = "select new cn.itsource.domain.vo.ItemChartVo("+groupByStr
                +",sum(amount)) from Purchasebillitem o "+whereJpql+" group by "+groupByStr;
        //如果我们获取单独的一些值，那么就会变成一个Object数组
        List<ItemChartVo> vos = super.findByJpql(jpql,params.toArray());
        return vos;
    }
}
