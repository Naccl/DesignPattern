/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class Test {
	public static void main(String[] args) {
		CompanyArmy companyArmy = new CompanyArmy();
		Command concreteCommand = new ConcreteCommand(companyArmy);
		ArmySupervisor armySupervisor = new ArmySupervisor();
		armySupervisor.setCommand(concreteCommand);
		armySupervisor.startExecuteCommand();
	}
}
