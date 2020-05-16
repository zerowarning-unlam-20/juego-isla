package states;

public abstract class State {
	protected boolean canChangeLocation = true;
	protected boolean canOpen = true;
	protected boolean canCut = true;
	protected boolean canLook = true;
	protected boolean canDrink = true;
	protected boolean canGrab = true;
	protected boolean canEat = true;
	protected boolean canDoStrength = true;

	@Override
	public String toString() {
		return "State [canChangeLocation=" + canChangeLocation + ", canOpen=" + canOpen + ", canLook=" + canLook
				+ ", canDrink=" + canDrink + ", canGrab=" + canGrab + ", canEat=" + canEat + ", canDoStrength="
				+ canDoStrength + "]";
	}

}
