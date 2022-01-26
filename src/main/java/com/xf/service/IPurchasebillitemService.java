package com.xf.service;

import com.xf.domain.Purchasebillitem;
import com.xf.domain.vo.ItemChartVo;
import com.xf.domain.vo.PurchaseBillItemVo;
import com.xf.query.PurchasebillitemQuery;

import java.util.List;

/**
 */
public interface IPurchasebillitemService extends IBaseService<Purchasebillitem,Long>{
    //获取表格数据
    List<PurchaseBillItemVo> findItems(PurchasebillitemQuery query);

    //获取图表数据
    List<ItemChartVo> findCharts(PurchasebillitemQuery query);
}
