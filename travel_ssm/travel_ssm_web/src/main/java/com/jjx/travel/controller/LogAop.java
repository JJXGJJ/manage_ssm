package com.jjx.travel.controller;

import com.jjx.travel.SysLog;
import com.jjx.travel.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author JJ
 * @Date 2020/11/3 14:58
 * @Version 1.0
 * @Describe 该类是通过aop的方式来进行日志记录，比如xxx访问了xx方法，访问时间，url，访问的ip
 *
 *  访问的url：
 *      可以通过HttpServletRequest获取到
 *  访问的ip:
 *      可以通过HttpServletRequest获取到
 *  访问的方法：
 *      通过aop中joinPoint对象，利用反射的方式获取到目标类和目标方法等
 */
@Component
@Aspect
public class LogAop {

    private Date visitTime;//访问的开始时间
    private Class clazz;//当前访问类的class对象
    private Method method;//当前访问的方法

    @Autowired
    private HttpServletRequest request;//在web.xml配置了RequestContextListener

    @Autowired
    private SysLogService sysLogService;


    @Before("execution(* com.jjx.travel.controller.*.*(..))")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        //获取用户访问的开始时间
        visitTime = new Date();
        //根据jp获取当前访问类的class对象
        clazz = joinPoint.getTarget().getClass();
        //通过jp中的Signature获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        //通过jp获取当前访问访问的参数，
        Object[] args = joinPoint.getArgs();
        //根据参数获取具体访问的method对象
        if (clazz!=null && args!=null){
            if (args.length==0){
                //方法为空参方法
                method = clazz.getMethod(methodName);
            }else {
                Class[] argsClass = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argsClass[i] = args[i].getClass();
                }
                //方法为有参数的方法
                method = clazz.getMethod(methodName,argsClass);
            }
        }
    }

    @After("execution(* com.jjx.travel.controller.*.*(..))")
    public void after(){
        if (method!=null && method.getName().contains("findAllLog")){
            return;
        }
        //访问方法的总时长
        Long executionTime = new Date().getTime() - visitTime.getTime();
        //获取访问的url
        //访问的路径
        String url = "";
        if (clazz!=null && method!= null && clazz!=LogAop.class){
            //创建一个日志对象 SysLog
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(executionTime);
            //通过反射中目标类注解上的值获取
            RequestMapping clazzAnno = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            //获取到类注解中的值，因为只有一个值，所有只需要获取数组中的第一个值就可
            String[] clazzValue = clazzAnno.value();
            //通过反射中目标方法注解上的值获取
            RequestMapping methodAnno = method.getAnnotation(RequestMapping.class);
            //获取到方法注解中的值
            String[] methodValue = methodAnno.value();
            url = clazzValue[0]+"/"+methodValue[0];
            sysLog.setUrl(url);

            //根据request获取请求的ip
            String ip = request.getRemoteAddr();
            sysLog.setIp(ip);

            //获取当前用户的用户名
            //获取当前操作的用户
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            //获取到当前用户对象
            User principal = (User) authentication.getPrincipal();
            //获取当前用户的用户名
            String username = principal.getUsername();
            sysLog.setUsername(username);

            sysLog.setMethod("【类名】："+clazz.getName()+" 【方法名】："+method.getName());
            sysLog.setVisitTime(visitTime);
            //将日志记录保存在
            sysLogService.save(sysLog);
        }

    }
}
