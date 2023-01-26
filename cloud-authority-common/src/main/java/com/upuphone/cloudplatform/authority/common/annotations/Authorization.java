package com.upuphone.cloudplatform.authority.common.annotations;

import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    /*
    *
    * 使用例子：
    *
    *   1. 单独使用@Authorization: 仅要求登录态（即需要携带token），对接口权限和资源（系统）权限不做任何限制，如登出、获取权限接口
    *   2. 仅使用permissionCode: 仅要求有对应【接口】的访问权限，对资源（系统）不做验证，如仅超管可访问的接口
    *   3. 仅使用resourceId： 仅要求有对应资源级别的访问权限，对接口权限不做验证, 如能访问该资源的任何角色都能访问的接口
    *   4. 使用permissionCode+resourceId： 要求对接口及资源做验证，如仅限某系统的Owner/developer可以访问的接口（增加开发者等）
    *   5. 使用relationTypes 必须配合resourceId使用，限制仅有该类型资源访问权限的用户可以访问。
    *                       （如某用户拥有开发者和owner角色，但是必须限定该资源的owner才能访问的接口，此时资源类型的抽象为owner）
    *
    *   注意：超管不是系统owner，仅超管可访问的接口只指定permissionCode即可
    *
    * */

    /**
     * 资源类型 默认系统owner+developer
     * 注：若为仅超管接口无需改动该默认值
     */
    SysRelationEnum[] relationTypes() default {SysRelationEnum.SYSTEM_OWNER, SysRelationEnum.SYSTEM_DEVELOPER};

    /**
     * 用于获取要查询的资源ID 使用el表达式 默认表示不需要特定资源
     * 注：若为仅超管接口无需改动该默认值
     */
    String resourceId() default StringUtils.EMPTY;

    /**
     * 访问该接口需要的权限Code 默认不需要权限
     */
    String permissionCode() default StringUtils.EMPTY;
    /**
     * 访问该接口系统id
     */
    String systemId() default StringUtils.EMPTY;

}
