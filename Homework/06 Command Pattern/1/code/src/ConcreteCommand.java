/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class ConcreteCommand implements Command{
	CompanyArmy army;

	public ConcreteCommand(CompanyArmy army) {
		this.army = army;
	}

	@Override
	public void execute() {
		army.sneakAttack();
	}
}
