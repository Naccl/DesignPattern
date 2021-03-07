import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class SecuritySystemFacade {
	private List<VCR> vcrs;
	private List<Light> lights;
	private List<Sensor> sensors;
	private List<Alarm> alarms;

	public SecuritySystemFacade() {
		this.vcrs = new ArrayList<>();
		this.lights = new ArrayList<>();
		this.sensors = new ArrayList<>();
		this.alarms = new ArrayList<>();

		this.vcrs.add(new VCR());
		this.vcrs.add(new VCR());

		this.lights.add(new Light());
		this.lights.add(new Light());
		this.lights.add(new Light());

		this.sensors.add(new Sensor());

		this.alarms.add(new Alarm());
	}

	public void turnOn() {
		for (VCR v : vcrs) {
			v.turnOn();
		}
		for (Light l : lights) {
			l.turnOn();
		}
		for (Sensor s : sensors) {
			s.turnOn();
		}
		for (Alarm a : alarms) {
			a.turnOn();
		}
	}

	public void turnOff() {
		for (VCR v : vcrs) {
			v.turnOff();
		}
		for (Light l : lights) {
			l.turnOff();
		}
		for (Sensor s : sensors) {
			s.turnOff();
		}
		for (Alarm a : alarms) {
			a.turnOff();
		}
	}
}
