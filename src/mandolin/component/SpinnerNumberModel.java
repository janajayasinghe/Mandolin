package mandolin.component;

/**
 * Created by caresys on 7/19/2020.
 */
public class SpinnerNumberModel {

	private Number stepSize, value;
	private Comparable minimum, maximum;

	public SpinnerNumberModel(){
		this(0, 0, 0, 0);
	}

	public SpinnerNumberModel(Number value, Comparable minimum, Comparable maximum, Number stepSize) {
		this.stepSize = stepSize;
		this.value = value;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Number getStepSize() {
		return stepSize;
	}

	public void setStepSize(Number stepSize) {
		this.stepSize = stepSize;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public Comparable getMinimum() {
		return minimum;
	}

	public void setMinimum(Comparable minimum) {
		this.minimum = minimum;
	}

	public Comparable getMaximum() {
		return maximum;
	}

	public void setMaximum(Comparable maximum) {
		this.maximum = maximum;
	}
}
