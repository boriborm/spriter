package com.brashmonkey.spriter;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents all the data which necessary to animate a Spriter generated SCML file.
 * An instance of this class holds {@link Folder}s and {@link Entity} instances.
 * Specific {@link Folder} and {@link Entity} instances can be accessed via the corresponding methods, i.e. getEntity() and getFolder().
 * @author Trixt0r
 *
 */
public class Data {

	final List<Folder> folders;
    final List<Entity> entities;
    public final String scmlVersion, generator, generatorVersion;
    
    Data(String scmlVersion, String generator, String generatorVersion){
    	this(scmlVersion, generator, generatorVersion, new ArrayList<Folder>(), new ArrayList<Entity>());
    }
    
    Data(String scmlVersion, String generator, String generatorVersion, List<Folder> folders, List<Entity> entities){
    	this.scmlVersion = scmlVersion;
    	this.generator = generator;
    	this.generatorVersion = generatorVersion;
    	this.folders = folders;
    	this.entities = entities;
    }
    
    /**
     * Adds a folder to this data.
     * @param folder the folder to add
     */
    void addFolder(Folder folder){
    	this.folders.add(folder);
    }
    
    /**
     * Adds an entity to this data.
     * @param entity the entity to add
     */
    void addEntity(Entity entity){
    	this.entities.add(entity);
    }
    
    /**
     * Returns a {@link Folder} instance with the given name.
     * @param name the name of the folder
     * @return the folder with the given name or null if no folder with the given name exists
     */
    public Folder getFolder(String name){
    	int index = getFolderIndex(name);
    	if(index >= 0) return getFolder(index);
    	else return null;
    }
    
    /**
     * Returns a folder index with the given name.
     * @param name name of the folder
     * @return the folder index of the Folder with the given name or -1 if no folder with the given name exists
     */
    int getFolderIndex(String name){
    	for(Folder folder: this.folders)
    		if(folder.name.equals(name)) return folder.id;
    	return -1;
    }
    
    /**
     * Returns a {@link Folder} instance at the given index.
     * @param index the index of the folder
     * @return the {@link Folder} instance at the given index
     */
    Folder getFolder(int index){
    	return this.folders.get(index);
    }
    
    /**
     * Returns an {@link Entity} instance with the given index.
     * @param index index of the entity to return.
     * @return the entity with the given index
     * @throws {@link IndexOutOfBoundsException} if the index is out of range 
     */
    public Entity getEntity(int index){
    	return this.entities.get(index);
    }
    
    /**
     * Returns an {@link Entity} instance with the given name.
     * @param name the name of the entity
     * @return the entity with the given name or null if no entity with the given name exists
     */
    public Entity getEntity(String name){
    	int index = getEntityIndex(name);
    	if(index >= 0) return getEntity(index);
    	else return null;
    }
    
    /**
     * Returns an entity index with the given name.
     * @param name name of the entity
     * @return the entity index of the entity with the given name or -1 if no entity with the given name exists
     */
    int getEntityIndex(String name){
    	for(Entity entity: this.entities)
    		if(entity.name.equals(name)) return entity.id;
    	return -1;
    }
    
    /**
     * Returns a {@link File} instance in the given {@link Folder} instance at the given file index.
     * @param folder {@link Folder} instance to search in.
     * @param file index of the file
     * @return the {@link File} instance in the given folder at the given file index
     */
    public File getFile(Folder folder, int file){
    	return folder.getFile(file);
    }
    
    /**
     * Returns a {@link File} instance in the given folder at the given file index.
     * @param folder index of the folder
     * @param file index of the file
     * @return the {@link File} instance in the given folder at the given file index
     * @throws {@link IndexOutOfBoundsException} if the folder or file index are out of range 
     */
    public File getFile(int folder, int file){
    	return getFile(this.getFolder(folder), file);
    }
    
    /**
     * Returns a {@link File} instance for the given {@link FileReference} instance.
     * @param ref reference to the file
     * @return the {@link File} instance for the given reference
     */
    public File getFile(FileReference ref){
    	return this.getFile(ref.folder, ref.file);
    }
    
    public String toString(){
    	String toReturn = getClass().getSimpleName()+"|[Version: "+scmlVersion+", Generator: "+generator+" ("+generatorVersion+")]";
    	for(Folder folder: folders)
    		toReturn += "\n"+folder;
    	for(Entity entity: entities)
    		toReturn += "\n"+entity;
    	toReturn+="]";
    	return toReturn;
    }

}