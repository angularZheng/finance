package com.zhengbing.service.impl;

import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.repository.UserPayRecordRepository;
import com.zhengbing.service.IUserPayRecord;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/11/29.
 */
@Service("userPayRecordService")
public class UserPayRecordImpl implements IUserPayRecord {

    private UserPayRecordRepository userPayRecordReponsitory;

    @Override
    public UserPayRecord save(UserPayRecord record) {
        return userPayRecordReponsitory.save(record);
    }
}
