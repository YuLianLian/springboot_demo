package com.yll.springboot.demo.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Spring 事物配置
 * 
 * @author  Administrator
 * @version  [版本号, 2017年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Aspect
@Configuration
public class TxAdviceInterceptorConfig {
  private static final String AOP_POINTCUT_EXPRESSION = 
          "execution (* com.jzg.springboot.demo.service.*.*Service(..))";
  
  /**
   * 可传播事务配置
   */
  private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES  = {
      "add*","save*","insert*","delete*","update*","edit*","batch*","create*","remove*" 
  };
  
  /**
   * 默认的只读事务
   */
  private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
      "get*" ,"count*" ,"find*" ,"query*" ,"select*" ,"list*" ,"*" 
  };
  
  @Autowired
  private PlatformTransactionManager transactionManager;
  
  @Bean
  public TransactionInterceptor txAdvice() {
    NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
     /*只读事务，不做更新操作*/
    RuleBasedTransactionAttribute readOnlyTx = readOnlyTransactionRule();
    // 默认的只读事务配置
    for ( String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
        transactionAttributeSource.addTransactionalMethod(methodName , readOnlyTx);
    }
    /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
    RuleBasedTransactionAttribute requiredTx = requiredTransactionRule();
    // 默认的传播事务配置
    for ( String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
        transactionAttributeSource.addTransactionalMethod(methodName , requiredTx );
    }
    return new TransactionInterceptor( transactionManager , transactionAttributeSource );
  }
  /**
   * 支持当前事务;如果不存在创建一个新的
   * {@link org.springframework.transaction.annotation.Propagation#REQUIRED}
   */
  private RuleBasedTransactionAttribute requiredTransactionRule() {
      RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
      required.setRollbackRules( Collections.singletonList( new RollbackRuleAttribute( Exception.class ) ) );
      required.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED );
      required.setTimeout( TransactionDefinition.TIMEOUT_DEFAULT );
      return required;
  }

  /**
   * 只读事务
   * {@link org.springframework.transaction.annotation.Propagation#NOT_SUPPORTED}
   */
  private RuleBasedTransactionAttribute readOnlyTransactionRule() {
      RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
      readOnly.setReadOnly( true );
      readOnly.setPropagationBehavior( TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
      return readOnly;
  }
  @Bean
  public Advisor txAdviceAdvisor() {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
    return new DefaultPointcutAdvisor(pointcut, txAdvice());
  }
  
}
