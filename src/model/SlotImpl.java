package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot
{
	private int position;
	private Color color;
	private int number;

	public SlotImpl(int position, Color color, int number)
	{   this.position = position;
		this.color = color;
		this.number = number;
	}
	
	
	@Override
	public int getPosition() {return position;}

	@Override
	public int getNumber() {return number;}

	@Override
	public Color getColor() {return color;}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		result = prime * result + position;
		return result;
	}


	@Override
	public boolean equals(Slot slot) {
		if (this == slot)
			return true;
		if (slot == null)
			return false;
		if (getClass() != slot.getClass())
			return false;
		SlotImpl other = (SlotImpl) slot;
		if (color != other.color)
			return false;
		if (number != other.number)
			return false;
		if (position != other.position)
			return false;
		return true;		
	}
	public String toString() {
		return String.format("Position: %s, Color: %s, Number: %s",
				 this.position, this.color, this.number);
	}

}
