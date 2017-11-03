package com.zhengbing.entity;

import com.zhengbing.common.BaseEntity;
import lombok.Builder;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zhengbing on 2017-11-03.
 */
@Data
@Entity
@Table( name = "fc_news" )
public class News extends BaseEntity {

    @Column( name = "title", length = 50, nullable = false )
    private String title;

    @Column( name = "content", length = 5000 )
    private String content;

    @Column(name = "hit_num",length = 11)
    private Integer hitNum;

}
