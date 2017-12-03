package com.zhengbing.service.impl;

import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.repository.UserPayRecordRepository;
import com.zhengbing.service.IUserPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/12/4.
 */
@Service("userPayRecordService")
public class UserPayRecordServiceImpl implements IUserPayRecordService{

    @Autowired
    private UserPayRecordRepository userPayRecordRepository;

    @Override
    public UserPayRecord save(UserPayRecord record) {
        return userPayRecordRepository.save(record);
    }

    @Override
    public Page<UserPayRecord> findPageable(Pageable pageable) {
        return userPayRecordRepository.findAll(pageable);
    }
}
