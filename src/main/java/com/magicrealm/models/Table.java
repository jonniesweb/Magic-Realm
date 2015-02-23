package com.magicrealm.models;

import com.magicrealm.utils.ProbabilityCalculator.Result;

public abstract class Table implements TableMethods {
	
	public void execute(Result result) {
		switch (result) {
		case ONE:
			this.one();
			break;
		case TWO:
			this.two();
			break;
		case THREE:
			this.three();
			break;
		case FOUR:
			this.four();
			break;
		case FIVE:
			this.five();
			break;
		case SIX:
			this.six();
			break;
		default:
			break;
		}
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
