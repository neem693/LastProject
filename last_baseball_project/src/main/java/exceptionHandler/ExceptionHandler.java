package exceptionHandler;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class ExceptionHandler {
	
	public void process (JoinPoint thisJointPoint, Exception exception) throws Exception{
		
		Signature sign = thisJointPoint.getSignature();
		System.out.println("----예외 발생----" + sign.getDeclaringTypeName() + " : " + sign.getName() + "-------");
		
	}

}
