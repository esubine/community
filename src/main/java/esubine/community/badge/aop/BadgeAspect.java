package esubine.community.badge.aop;

import esubine.community.badge.BadgeService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class BadgeAspect {
    private final BadgeService badgeService;

    @AfterReturning(
            pointcut = "@annotation(esubine.community.badge.aop.CheckBadge)"
    )
    public void refreshBadge(JoinPoint joinPoint) {

        Long userId = null;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        for (int i = 0; i < method.getParameterCount(); i++) {
            if(method.getParameters()[i].getName().equals("userId")){
                userId = (Long) joinPoint.getArgs()[i];
            }
        }

        if(userId == null){
            System.out.println("[ERROR] userId 가 필요합니다.");
            return;
        }

        badgeService.refreshBadge(userId); // 어드바이스
    }

}
