package it.polimi.ingsw.model;

import java.util.*;
import Exceptions.*;

public class WareHouse {
    private Chest chest;
    private ArrayList<Storage> storages;
    private Map<Resource, Integer> resourcesToAdd;
    private Map<Resource, Integer> resourcesToOrganize;
    private Map<Resource, Integer> backupResourcesToOrganize;

    public WareHouse() {
        chest = new Chest();
        storages = new ArrayList<>();
        storages.add(new Storage(1, Resource.COIN));            //inizializzati a caso
        storages.add(new Storage(2, Resource.SHIELD));
        storages.add(new Storage(3, Resource.SERVANT));
        resourcesToAdd = new HashMap<>();
        resourcesToOrganize = new HashMap<>();
    }

    public ArrayList<Storage> getStorages(){
        return storages;
    }

    //metodo che svuota gli storage. Poi le risorse andranno rimesse a posto
    public void getFromStorages() {
        for (Storage i : storages) {
            if (!i.isEmpty()) {
                resourcesToOrganize.put(i.getType(), i.getQuantity());
                i.clearStorage();
            }
        }
    }

    //metodo per tenere traccia delle risorse guadagnate dal mercato
    public void addResource(Resource type, int n) {
        if (resourcesToAdd.get(type) != null) {
            resourcesToAdd.put(type, resourcesToAdd.get(type) + n);
        }
        else resourcesToAdd.put(type, n);
    }

    //metodi per settare il tipo di Storage(sarà l'utente a sceglierlo)
    public void setTypeOfStorage(int i, Resource type) throws ModelException {
        if (i < 1 || i > 3) throw new ModelException("Not existing storage");
        storages.get(i - 1).setType(type);
    }

    //metodo per controllare che i 3 storage abbiano tipi diversi e che insieme tutti gli storage possano ospitare tutte le risorse da organizzare
    public boolean controlStoragesType(){
        if (storages.get(0).getType() == storages.get(1).getType() || storages.get(1) == storages.get(2) || storages.get(0) == storages.get(2))
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

    //mette le risorse da organizzare in uno storage i
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
    
    //se ci sono solo 3 magazzini, aggiunge automaticamente tutte le risorse da organizzare nel magazzino giusto
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

    //mette le risorse prese dal mercato in uno storage i
    public void addResourcestoAdd(Resource type, int i, int n) throws ModelException {
        if (resourcesToAdd.isEmpty() == true)
            throw new ModelException("All resources are already added in the storages");
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        if (resourcesToAdd.get(type) == null)
            throw new ModelException("All resources of this type are already added to the storages");
        if (n > resourcesToAdd.get(type)) throw new ModelException("Not enough" + type);
        storages.get(i - 1).addToStorage(type, n);
        resourcesToAdd.put(type, resourcesToAdd.get(type) - n);
        if (resourcesToAdd.get(type) == 0) resourcesToAdd.remove(type);
    }

    //se una o più risorse di un certo tipo vengono scartate, le tolgo dalle risorse da aggiungere agli storage
    public void discardResourcesToAdd(Resource type, int n) throws ModelException {
        if (resourcesToAdd.isEmpty() == true)
            throw new ModelException("All resources are already organized in the storages");
        if (resourcesToAdd.get(type) == null) throw new ModelException("No resources of this type");
        if (n > resourcesToAdd.get(type)) throw new ModelException("Not enough" + type);
        resourcesToAdd.put(type, resourcesToAdd.get(type) - n);
        if (resourcesToAdd.get(type) == 0) resourcesToAdd.remove(type);
    }

    public boolean resourcesToOrganizeIsEmpty() {
        if (resourcesToOrganize.isEmpty()) return true;
        else return false;
    }

    public boolean resourcesToAddIsEmpty() {
        if (resourcesToAdd.isEmpty()) return true;
        else return false;
    }

    public Map<Resource, Integer> getResourcesToAdd() {
        return resourcesToAdd;
    }

    public Map<Resource, Integer> getResourcesToOrganize() {
        return resourcesToOrganize;
    }


    //restituisce il numero di risorse totali
    public int totResources() throws ModelException {
        int numResources = 0;
        numResources += chest.getResources().values().stream().reduce(0, Integer::sum);
        for (int i = 0; i < 3; i++) {
            numResources += getFromStorage(i+1);
        }
        return numResources;
    }


    //restituisce il numero di risorse nella chest di un certo tipo
    public int getFromChest(Resource type) {
        return chest.getResource(type);
    }

    //restituisce il numero di risorse nello storage i
    public int getFromStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getQuantity();
    }

    //restituisce il tipo di risorsa nello storage i
    public Resource getTypeStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getType();
    }

    //restituisce una mappa per la quantità di un tipo di risorsa dalla chest
    public Map<Resource, Integer> getMapfromChest(Resource type) {
        return chest.getNumof(type);
    }

    //restituisce una mappa per tutti i tipi di risorsa dalla chest
    public Map<Resource, Integer> getMapfromChest() {
        return chest.getResources();
    }

    //restituisce una mappa per lo storage i
    public Map<Resource, Integer> getMapfromStorage(int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        return storages.get(i - 1).getStorage();
    }

    public Map<Resource, Integer> getMapfromAllStorages() {
        Map<Resource, Integer> m = new HashMap<>();
        for (int i = 0; i < storages.size(); i++) {
            if (m.get(storages.get(i).getType()) == null) {
                m.put(storages.get(i).getType(), storages.get(i).getQuantity());
            } else m.put(storages.get(i).getType(), storages.get(i).getQuantity() + m.get(storages.get(i).getType()));
        }
        return m;
    }

    //totale delle risorse con le relative quantità tra chest e storages
    public Map<Resource, Integer> totalResources() {
        Map<Resource, Integer> m = chest.getResources();

        for (Storage i : storages) {
            m.put(i.getType(), m.get(i.getType()) + i.getQuantity());
        }
        return m;
    }

    public void subFromStorage(Resource type, int n, int i) throws ModelException {
        if (i < 1 || i > storages.size()) throw new ModelException("Not existing storage");
        storages.get(i - 1).subFromStorage(type, n);
    }

    public void subFromChest(Resource type, int n) throws ModelException {
        chest.subFromChest(type, n);
    }

    public void addToChest(Resource type, int n) {
        chest.addToChest(type, n);
    }

    //controllo il requisito per comprare una carta sviluppo
    public boolean controlRequirements(DevelopmentCard card) {
        Map<Resource, Integer> m = totalResources();
        Map<Resource, Integer> c = card.getRequirements();
        Set<Resource> s = c.keySet();
        for (Resource i : s) {
            if (m.get(i) < c.get(i)) return false;
        }
        return true;
    }


    //controllo il requisito per la carta leader Storage
    public boolean controlForStorage(Resource type, int n) {
        Map<Resource, Integer> m = totalResources();
        if (m.get(type) >= n) return true;
        else return false;
    }


    public void addStorage(Storage storage) {
        storages.add(storage);
    }


    public void addInitResources(Resource type) throws ModelException{
        resourcesToOrganize.put(type, 1);
        defaultAddResourcesToOrganize();
        resourcesToOrganize.remove(type);
    }

    public void addInitResources(Resource type1, Resource type2) throws ModelException{
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