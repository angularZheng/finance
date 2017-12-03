package com.zhengbing.service;

import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.repository.UserPayRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhengbing on 2017/12/4.
 */
public interface IUserPayRecordService {

    UserPayRecord save(UserPayRecord record);

    Page<UserPayRecord> findPageable(Pageable pageable);
}
