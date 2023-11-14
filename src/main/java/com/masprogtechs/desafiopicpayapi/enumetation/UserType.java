package com.masprogtechs.desafiopicpayapi.enumetation;

public enum UserType {
    COMUN("COMUN"),
    LOJISTA("LOJISTA");

    private final String name;

    UserType(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
