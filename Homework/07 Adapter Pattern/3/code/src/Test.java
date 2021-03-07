/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class Test {
	public static void main(String[] args) {
		SecuritySystemFacade securitySystemFacade = new SecuritySystemFacade();
		securitySystemFacade.turnOn();
		System.out.println("-------------------------------");
		securitySystemFacade.turnOff();
	}
}
