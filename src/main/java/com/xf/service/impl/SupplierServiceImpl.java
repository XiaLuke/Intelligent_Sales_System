package com.xf.service.impl;

import com.xf.domain.Supplier;
import com.xf.repository.SupplierRepository;
import com.xf.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long> implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

}
