package it.polimi.ingsw.model;

import java.util.*;
import Exceptions.*;

/**
 * The type Ware house. It has 3 or more storages (max 5) and a chest. There are two maps: resourcesToAdd used for the
 * resources gained in the market and resourcesToOrganize for the resources already put in the storages that the player
 * wants to move in another storage.
 */
public class WareHouse {
    private Chest chest;
    private ArrayList<Storage> storages;
    private Map<Resource, Integer> resourcesToAdd;
    private Map<Resource, Integer> resourcesToOrganize;

    /**
     * Instantiates a new Warehouse.
     */
    public WareHouse() {
        chest = new Chest();
        storages = new ArrayList<>();
        storages.add(new Storage(1, Resource.COIN));
        storages.add(new Storage(2, Resource.SHIELD));
        storages.add(new Storage(3, Resource.SERVANT));
        resourcesToAdd = new HashMap<>();
        resourcesToOrganize = new HashMap<>();
    }

    /**
     * Get storages.
     *
     * @return the array list of storage
     */
    public ArrayList<Storage> getStorages(){
        return storages;
    }

    /**
     * Get all the resources from storages
     */
    public void getFromStorages() {
        for (Storage i : storages) {
            if (!i.isEmpty()) {
                resourcesToOrganize.put(i.getType(), i.getQuantity());
                i.clearStorage();
            }
        }
    }

    /**
     * Add resources gained from market.
     *
     * @param type the resources type
     * @param n    the quantity
     */
    public void addResource(Resource type, int n) {
        if (resourcesToAdd.get(type) != null) {
            resourcesToAdd.put(type, resourcesToAdd.get(type) + n);
        }
        else resourcesToAdd.put(type, n);
    }

    /**
     * Set type of a storage.
     *
     * @param i    the storage
     * @param type the resource type
     * @throws ModelException if the storage does not exists
     */
    public void setTypeOfStorage(int i, Resource type) throws ModelException {
        if (i < 1 || i > 3) throw new ModelException("Not existing storage");
        storages.get(i - 1).setType(type);
    }

    /**
     * Control if storages have all different types and they can contain all the resources that the player wants to move.
     *
     * @return the boolean
     */
    public boolean controlStoragesType(){
        if (storages.get(0).getType() == storages.get(1).getType() || storages.get(1).getType() == storages.get(2).getType() || storages.get(0).getType() == storages.get(2).getType())
            return false;
        else {
            Map<Resource, Integer> m = new HashMap<>();
            for(int i = 0; i < 3; i++){
                m.put(storages.get(i).getType(), storages.get(i).getmaxNumber());
            }
            if(storages.size() == 4){
                if(m.get(storages.get(3).getType()) == null){
                    m.put(storages.get(3).getType(), storages.get(3).getmaxNumber());
                }
                else m.put(storages.get(3).getType(), m.get(storages.get(3).getType()) + storages.get(3).getmaxNumber());
            }
            if(storages.size() == 5){
                if(m.get(storages.get(3).getType()) == null){
                    m.put(storages.get(3).getType(), storages.get(3).getmaxNumber());
                }
                else m.put(storages.get(3).getType(), m.get(storages.get(3).getType()) + storages.get(3).getmaxNumber());

                if(m.get(storages.get(4).getType()) == null){
                    m.put(storages.get(4).getType(), storages.get(4).getmaxNumber());
                }
                else m.put(storages.get(4).getType(), m.get(storages.get(4).getType()) + storages.get(4).getmaxNumber());
            }

            if(resourcesToOrganize.isEmpty()) return true;
            Set<Resource> s = resourcesToOrganize.keySet();
            for(Resource i : s){
                if(m.get(i) == null) return false;
                if(m.get(i) < resourcesToOrganize.get(i)) return false;
            }
            return true;
        }
    }

    /**
     * Add resources to organize to a storage.
     *
     * @param type the type of the resources
     * @param i    the storage
     * @param n    the quantity of the resources
     * @throws ModelException t
     */
    public void addResourcestoOrganize(Resource type, int i, int n) throws ModelException {
        if (resourcesToOrganize.isEmpty() == true)
            throw new ModelException("All resources are already organized in the storages");
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        if (resourcesToOrganize.get(type) == null)
            throw new ModelException("All resources of this type are already organized in the storages");
        if (n > resourcesToOrganize.get(type)) throw new ModelException("Not enough" + type);
        storages.get(i - 1).addToStorage(type, n);
        resourcesToOrganize.put(type, resourcesToOrganize.get(type) - n);
        if (resourcesToOrganize.get(type) == 0) resourcesToOrganize.remove(type);
    }

    /**
     * If there are only 3 storages, it puts all the resources to organize automatically in the correct storage.
     *
     * @throws ModelException
     */
    public void defaultAddResourcesToOrganize() throws ModelException{
        if(resourcesToOrganize.isEmpty() == true) return;
        if(controlStoragesType() == false) throw new ModelException("Wrong types for storages");
        if(storages.size() > 3) throw new ModelException("Can't put resources by default");

        if(resourcesToOrganize.get(getTypeStorage(1))!= null) {
            storages.get(0).addToStorage(getTypeStorage(1), resourcesToOrganize.get(getTypeStorage(1)));
            resourcesToOrganize.remove(getTypeStorage(1));
        }
        if(resourcesToOrganize.get(getTypeStorage(2)) != null) {
            storages.get(1).addToStorage(getTypeStorage(2), resourcesToOrganize.get(getTypeStorage(2)));
            resourcesToOrganize.remove(getTypeStorage(2));
        }
        if(resourcesToOrganize.get(getTypeStorage(3)) != null) {
            storages.get(2).addToStorage(getTypeStorage(3), resourcesToOrganize.get(getTypeStorage(3)));
            resourcesToOrganize.remove(getTypeStorage(3));
        }
    }

    /**
     * Add resources from the market to a storage.
     *
     * @param type the type of the resources
     * @param i    the storage
     * @param n    the quantity of the resouces
     * @throws ModelException
     */
    public void addResourcestoAdd(Resource type, int i, int n) throws ModelException {
        if (resourcesToAdd.isEmpty() == true)
            throw new ModelException("All resources are already added in the storages");
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        if (resourcesToAdd.get(type) == null)
            throw new ModelException("All resources of this type are already added to the storages");
        if (n > resourcesToAdd.get(type)) throw new ModelException("Not enough " + type);
        storages.get(i - 1).addToStorage(type, n);
        resourcesToAdd.put(type, resourcesToAdd.get(type) - n);
        if (resourcesToAdd.get(type) == 0) resourcesToAdd.remove(type);
    }

    /**
     * Discard resources from the market.
     *
     * @param type the type of the resources
     * @param n    the quantity of the resources
     * @throws ModelException
     */
    public void discardResourcesToAdd(Resource type, int n) throws ModelException {
        if (resourcesToAdd.isEmpty() == true)
            throw new ModelException("All resources are already organized in the storages");
        if (resourcesToAdd.get(type) == null) throw new ModelException("No resources of this type");
        if (n > resourcesToAdd.get(type)) throw new ModelException("Not enough " + type);
        resourcesToAdd.put(type, resourcesToAdd.get(type) - n);
        if (resourcesToAdd.get(type) == 0) resourcesToAdd.remove(type);
    }

    /**
     * Resources to organize is empty.
     *
     * @return the boolean
     */
    public boolean resourcesToOrganizeIsEmpty() {
        if (resourcesToOrganize.isEmpty()) return true;
        else return false;
    }

    /**
     * Resources to add is empty.
     *
     * @return the boolean
     */
    public boolean resourcesToAddIsEmpty() {
        if (resourcesToAdd.isEmpty()) return true;
        else return false;
    }

    /**
     * Gets resources to add.
     *
     * @return the resources to add
     */
    public Map<Resource, Integer> getResourcesToAdd() {
        return resourcesToAdd;
    }

    /**
     * Gets resources to organize.
     *
     * @return the resources to organize
     */
    public Map<Resource, Integer> getResourcesToOrganize() {
        return resourcesToOrganize;
    }


    /**
     * Calculate the number of all the resources in the warehouse
     *
     * @return the quantity
     * @throws ModelException
     */
    public int totResources() throws ModelException {
        int numResources = 0;
        numResources += chest.getResources().values().stream().reduce(0, Integer::sum);
        for (int i = 0; i < storages.size(); i++) {
            numResources += getFromStorage(i+1);
        }
        return numResources;
    }


    /**
     * Get quantity of a resource from chest.
     *
     * @param type the type of the resources
     * @return the quantity from chest
     */
    public int getFromChest(Resource type) {
        return chest.getResource(type);
    }

    /**
     * Get quantity of a resource from storage.
     *
     * @param i the storage
     * @return the quantity from the storage
     * @throws ModelException if the storage does not exists
     */
    public int getFromStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getQuantity();
    }

    /**
     * Get the type of a storage.
     *
     * @param i the storage
     * @return the storage type
     * @throws ModelException if the storage does not exists
     */
    public Resource getTypeStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getType();
    }

    /**
     * Get quantity of a resource in chest.
     *
     * @param type the resource type
     * @return the map
     */
    public Map<Resource, Integer> getMapfromChest(Resource type) {
        return chest.getNumof(type);
    }

    /**
     * Get map of the chest.
     *
     * @return the map
     */
    public Map<Resource, Integer> getMapfromChest() {
        return chest.getResources();
    }

    /**
     * Get quantity and type of a storage.
     *
     * @param i the storage
     * @return the map
     * @throws ModelException if the storage does not exists
     */
    public Map<Resource, Integer> getMapfromStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getStorage();
    }

    /**
     * Get quantity and types of all the storages.
     *
     * @return the map
     */
    public Map<Resource, Integer> getMapfromAllStorages() {
        Map<Resource, Integer> m = new HashMap<>();
        for (int i = 0; i < storages.size(); i++) {
            if (m.get(storages.get(i).getType()) == null) {
                m.put(storages.get(i).getType(), storages.get(i).getQuantity());
            } else m.put(storages.get(i).getType(), storages.get(i).getQuantity() + m.get(storages.get(i).getType()));
        }
        return m;
    }

    /**
     * Get quantity and types of all the storages and chest.
     *
     * @return the map
     */
    public Map<Resource, Integer> totalResources() {
        Map<Resource, Integer> m = chest.getResources();

        for (Storage i : storages) {
            m.put(i.getType(), m.get(i.getType()) + i.getQuantity());
        }
        return m;
    }

    /**
     * Sub resources from a storage.
     *
     * @param type the type of the resources
     * @param n    the quantity
     * @param i    the storage
     * @throws ModelException if the storage does not exists
     */
    public void subFromStorage(Resource type, int n, int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        storages.get(i - 1).subFromStorage(type, n);
    }

    /**
     * Sub from chest.
     *
     * @param type the type of the resources
     * @param n    the quantity
     * @throws ModelException
     */
    public void subFromChest(Resource type, int n) throws ModelException {
        chest.subFromChest(type, n);
    }

    /**
     * Add to chest.
     *
     * @param type the type of the resources
     * @param n    the quantity
     */
    public void addToChest(Resource type, int n) {
        chest.addToChest(type, n);
    }

    /**
     * Control if there are enough resources to buy a DevelopmentCard
     *
     * @param card the card
     * @return the boolean
     */
    public boolean controlRequirements(DevelopmentCard card) {
        Map<Resource, Integer> m = totalResources();
        Map<Resource, Integer> c = card.getRequirements();
        Set<Resource> s = c.keySet();
        for (Resource i : s) {
            if (m.get(i) < c.get(i)) return false;
        }
        return true;
    }


    /**
     * Control requirements for a Leader Card that adds a storage
     *
     * @param type the type of the resource
     * @param n    the quantity
     * @return the boolean
     */
    public boolean controlForStorage(Resource type, int n) {
        Map<Resource, Integer> m = totalResources();
        if (m.get(type) >= n) return true;
        else return false;
    }


    /**
     * Add a storage.
     *
     * @param storage the storage to add
     */
    public void addStorage(Storage storage) {
        storages.add(storage);
    }


    /**
     * Add initial resources.
     *
     * @param type the type of the resources
     * @throws ModelException
     */
    public void addInitResources(Resource type) throws ModelException{
        resourcesToOrganize.clear();
        resourcesToOrganize.put(type, 1);
        defaultAddResourcesToOrganize();
        resourcesToOrganize.remove(type);
    }

    /**
     * Add initial resources.
     *
     * @param type1 the type of the first resource
     * @param type2 the type of the second resource
     * @throws ModelException
     */
    public void addInitResources(Resource type1, Resource type2) throws ModelException{
        resourcesToOrganize.clear();
        if(type1 == type2) resourcesToOrganize.put(type1, 2);
        else {
            resourcesToOrganize.put(type1, 1);
            resourcesToOrganize.put(type2, 1);
        }
        defaultAddResourcesToOrganize();
        resourcesToOrganize.remove(type1);
        if(type1 != type2) resourcesToOrganize.remove(type2);
    }

}