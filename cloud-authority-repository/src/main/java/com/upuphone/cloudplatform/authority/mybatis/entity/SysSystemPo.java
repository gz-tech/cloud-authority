package com.upuphone.cloudplatform.authority.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统表（内部）
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_system")
public class SysSystemPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统唯一值
     */
    private String code;

    /**
     * 系统描述
     */
    private String description;

    /**
     * 系统owner(自动授予其该系统管理员权限)
     */
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY, exist = false)
    private String ownerUserId;

    /**
     * 系统owner(自动授予其该系统管理员权限)name
     */
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY, exist = false)
    private String ownerName;

    /**
     * 系统密钥
     */
    private String secret;

    /**
     * 0 valid 1 deleted
     */
    private Integer delFlag;

    /**
     * create_time
     */
    private LocalDateTime createTime;

    /**
     * update_time
     */
    private LocalDateTime updateTime;


}
