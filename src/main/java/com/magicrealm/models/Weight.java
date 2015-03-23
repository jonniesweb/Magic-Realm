package com.magicrealm.models;

public enum Weight {
	NEGLIGIBLE,
	LIGHT,
	MEDIUM,
	HEAVY,
	TREMENDOUS;
	
    public Weight increment(int i) {
        return values()[Math.min(ordinal() + i, TREMENDOUS.ordinal())];
    }
    
    public Weight decrement(int i) {
    	return values()[Math.max(ordinal() - i, NEGLIGIBLE.ordinal())];
    }
}
