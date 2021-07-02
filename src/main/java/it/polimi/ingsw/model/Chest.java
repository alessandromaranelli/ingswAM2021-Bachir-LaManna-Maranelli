package it.polimi.ingsw.model;

import Exceptions.*;
import java.util.*;

/**
 * The type Chest. It is a map for the 4 resources
 */
public class Chest {
    private Map<Resource, Integer> resources;

    /**
     * Instantiates a new Chest.
     */
    public Chest(){
        resources = new HashMap<>();
        resources.put(Resource.COIN, 50);
        resources.put(Resource.SERVANT, 50);
        resources.put(Resource.SHIELD, 50);
        resources.put(Resource.STONE, 50);
    }

    /**
     * Get the map.
     *
     * @return the map
     */
    public Map<Resource, Integer> getResources(){
        Map<Resource,Integer> map=new HashMap<>();
        for(Resource r:resources.keySet()){
            map.put(r,resources.get(r));
        }
        return map;
    }

    /**
     * Get the quantity of a resource.
     *
     * @param type of the resource
     * @return the quantity
     */
    public int getResource(Resource type){
        return resources.get(type);
    }

    /**
     * Get the map of a single resource
     *
     * @param type the type of the resource
     * @return the map
     */
    public Map<Resource, Integer> getNumof(Resource type){
        Map<Resource, Integer> m = new HashMap<>();
        m.put(type, resources.get(type));
        return m;
    }

    /**
     * Add resources to chest.
     *
     * @param type the type of the resource
     * @param n quantity of the resource
     */
    public void addToChest(Resource type, int n){
        resources.put(type, resources.get(type) + n);
    }

    /**
     * Sub resources from chest.
     *
     * @param type the type of the resource
     * @param n quantity of the resource
     * @throws ModelException if there aren't enough resources in the chest
     */
    public void subFromChest(Resource type, int n) throws ModelException {
        if(resources.get(type) - n < 0) throw new ModelException("Not enough " + type);
        resources.put(type, resources.get(type) - n);
    }
}
