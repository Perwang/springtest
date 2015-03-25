package com.spring.expression.test;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

/**
 * 测试spring对表达式的支持
 * @author wangcanpei
 *
 */
public class SpELTest {
	
	public static void main(String[] args) {
		helloWorld();
	}
	
	 public static void helloWorld() {
	        ExpressionParser parser = new SpelExpressionParser();
	        Expression expression = 
	parser.parseExpression("('Hello' + ' World').concat(#end)");
	        EvaluationContext context = new StandardEvaluationContext();
	        context.setVariable("end", "!");
	        Assert.isTrue("Hello World!".equals(expression.getValue(context)));
	    }

}
