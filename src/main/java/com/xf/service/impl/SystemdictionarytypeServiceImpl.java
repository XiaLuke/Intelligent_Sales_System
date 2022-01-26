package com.xf.service.impl;

import com.xf.domain.Systemdictionarytype;
import com.xf.repository.SystemdictionarytypeRepository;
import com.xf.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 */
@Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype,Long> implements ISystemdictionarytypeService {

    @Autowired
    private SystemdictionarytypeRepository systemdictionarytypeRepository;

}
