package com.xf.service.impl;

import com.xf.common.UserContext;
import com.xf.domain.Employee;
import com.xf.domain.Purchasebill;
import com.xf.repository.PurchasebillRepository;
import com.xf.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill,Long> implements IPurchasebillService {

    @Autowired
    private PurchasebillRepository purchasebillRepository;

    @Override
    public void save(Purchasebill purchasebill) {
        if(purchasebill.getId()==null){
            //添加的时候，录入人就是当前登录用户
            Employee loginUser = UserContext.getUser();
            purchasebill.setInputUser(loginUser);
        }
        super.save(purchasebill);
    }
}
