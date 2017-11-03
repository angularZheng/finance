package com.zhengbing.repository;

import com.zhengbing.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhengbing on 2017-11-03.
 */
public interface NewsRepository extends JpaRepository< News, Integer > {

}
