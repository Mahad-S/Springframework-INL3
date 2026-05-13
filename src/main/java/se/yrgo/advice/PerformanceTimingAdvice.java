package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceTimingAdvice {

    @Around("execution(* se.yrgo.services..*(..)) || execution(* se.yrgo.dataaccess..*(..))")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.nanoTime();

        Object result = pjp.proceed();

        long end = System.nanoTime();

        System.out.println(
                "Time taken for the method "
                        + pjp.getSignature().getName()
                        + " took "
                        + (end - start) / 1_000_000
                        + "ms");

        return result;
    }
}