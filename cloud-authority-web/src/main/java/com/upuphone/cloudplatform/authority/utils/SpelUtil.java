package com.upuphone.cloudplatform.authority.utils;

import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

public class SpelUtil {

    /**
     * 用于SpEL表达式解析.
     */
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    public static Object generateKeyBySpEL(String spELString, JoinPoint joinPoint) {
        // 通过joinPoint获取被注解方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = NAME_DISCOVERER.getParameterNames(method);
        if (null == paramNames || paramNames.length == 0) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "注解参数不能为空");
        }
        // 解析过后的Spring表达式对象
        Expression expression = PARSER.parseExpression(spELString);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = joinPoint.getArgs();
        // 给上下文赋值
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        // 表达式从上下文中计算出实际参数值
        return expression.getValue(context);
    }
}