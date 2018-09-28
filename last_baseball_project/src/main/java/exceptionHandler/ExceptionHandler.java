package exceptionHandler;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class ExceptionHandler {
	
	public void process (JoinPoint thisJointPoint, Exception exception) throws Exception{
		
		Signature sign = thisJointPoint.getSignature();
		System.out.println("----���� �߻�----" + sign.getDeclaringTypeName() + " : " + sign.getName() + "-------");
		
	}

}
