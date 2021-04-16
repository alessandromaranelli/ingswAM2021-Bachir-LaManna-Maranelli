package it.polimi.ingsw.model;

import Exceptions.*;
import java.util.*;

public class Chest {
    private Map<Resource, Integer> resources;

    public Chest(){
        resources = new HashMap<>();
        resources.put(Resource.COIN, 0);
        resources.put(Resource.SERVANT, 0);
        resources.put(Resource.SHIELD, 0);
        resources.put(Resource.STONE, 0);
    }

    //return map of all the Resource types
    public Map<Resource, Integer> getResources(){
        return this.resources;
    }

    //return quantity of a Resource type
    public int getResource(Resource type){
        return resources.get(type);
    }

    //return a map of a Resource type whit its quantity
    public Map<Resource, Integer> getNumof(Resource type){
        Map<Resource, Integer> m = new HashMap<>();
        m.put(type, resources.get(type));
        return m;
    }

    public void addToChest(Resource type, int n){
        resources.put(type, resources.get(type) + n);
    }

    public void subFromChest(Resource type, int n) throws ModelException {
        if(resources.get(type) - n < 0) throw new ModelException("Not enough" + type);
        resources.put(type, resources.get(type) - n);
    }
}
