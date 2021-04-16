package it.polimi.ingsw.model;

import Exceptions.*;

import java.util.*;

public class Storage {
    private int quantity;
    private final int maxNumber;
    private Resource type;

    public Storage(int n, Resource type){
        quantity = 0;
        maxNumber = n;
        this.type = type;
    }

    public Resource getType(){
        return type;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getmaxNumber(){
        return maxNumber;
    }

    //return a Map for this storage
    public Map<Resource, Integer> getStorage(){
        Map<Resource, Integer> m = new HashMap<>();
        m.put(type, quantity);
        return m;
    }

    public boolean isEmpty(){
        if(quantity == 0) return true;
        else return false;
    }

    //not possible to change type if storage is not empty
    public void setType(Resource type) throws ModelException {
        if(!isEmpty()) throw new ModelException("Storage is not empty, can't change type");
        this.type = type;
    }

    public void addToStorage(Resource type, int n) throws ModelException {
        if(this.type != type) throw new ModelException("Storage contains" + type);
        if(quantity + n > maxNumber) throw new ModelException("Exceeding limit for storage");
        quantity = quantity + n;
    }

    public void subFromStorage(Resource type, int n) throws ModelException{
        if(this.type != type) throw new ModelException("Storage contains" + type);
        if(quantity - n < 0) throw new ModelException("Not enough" + type);
        quantity = quantity - n;
    }

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
