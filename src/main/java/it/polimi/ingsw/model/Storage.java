package it.polimi.ingsw.model;

import Exceptions.*;

import java.util.*;

/**
 * The type Storage. Every player has three storages, he can put only one type of resource per storage.
 * The storages have an attribute maxNumber that defines the max number of resources that they can contain.
 */
public class Storage {
    private int quantity;
    private final int maxNumber;
    private Resource type;

    /**
     * Instantiates a new Storage.
     *
     * @param n    the max quantity of resources the storage can contain
     * @param type the resource type
     */
    public Storage(int n, Resource type){
        quantity = 0;
        maxNumber = n;
        this.type = type;
    }

    /**
     * Get type resource.
     *
     * @return the resource
     */
    public Resource getType(){
        return type;
    }

    /**
     * Get quantity int.
     *
     * @return the number of resources the storage contain
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Getmax number int.
     *
     * @return the the max quantity of resources the storage can contain
     */
    public int getmaxNumber(){
        return maxNumber;
    }

    /**
     * Get storage map.
     *
     * @return the map <Resource,int> associated to this storage
     */
//return a Map for this storage
    public Map<Resource, Integer> getStorage(){
        Map<Resource, Integer> m = new HashMap<>();
        m.put(type, quantity);
        return m;
    }

    /**
     * Is empty boolean.
     *
     * @return boolean true if storage is empty, false otherwise
     */
    public boolean isEmpty(){
        if(quantity == 0) return true;
        else return false;
    }

    /**
     * Sets Resource type.
     *
     * @param type the Resource type
     * @throws ModelException the model exception if the storage is not empty
     */
//not possible to change type if storage is not empty
    public void setType(Resource type) throws ModelException {
        if(!isEmpty()) throw new ModelException("Storage is not empty, can't change type");
        this.type = type;
    }

    /**
     * Add to storage.
     *
     * @param type the Resource type
     * @param n    the number of resources to add
     * @throws ModelException the model exception
     */
    public void addToStorage(Resource type, int n) throws ModelException {
        if(this.type != type) throw new ModelException("Storage contains" + type);
        if(quantity + n > maxNumber) throw new ModelException("Exceeding limit for storage");
        quantity = quantity + n;
    }

    /**
     * Sub from storage.
     *
     * @param type the Resource type
     * @param n    the number of resources to subtract
     * @throws ModelException the model exception
     */
    public void subFromStorage(Resource type, int n) throws ModelException{
        if(this.type != type) throw new ModelException("Storage contains" + type);
        if(quantity - n < 0) throw new ModelException("Not enough" + type);
        quantity = quantity - n;
    }

    /**
     * Clear storage.
     * Empties the storage from all the resources.
     */
    public void clearStorage(){
        quantity = 0;
    }

    /*
    //metodo in più per evenienza
    public void addToStorageandSetType(Resource type, int n){
        if(n > maxNumber) throw new OutofLimitException();
        setType(type);
        clearStorage();
        addToStorage(type, n);
    }

    //metodo in più per evenienza
    public int getFromStorageandClear(){
        int q = quantity;
        clearStorage();
        return q;
    }
     */
}
