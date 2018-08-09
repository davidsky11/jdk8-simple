package com.kvlt.reflect.proxy;

/**
 * @author KVLT
 * 2018-04-03 14:50
 */
public class HouseOwner implements RentHouse {

    public void rent() {
        System.out.println("I want to rent my house");
    }

    public void charge(String str) {
        System.out.println("You get : " + str + " RMB HouseCharge.");
    }

}
