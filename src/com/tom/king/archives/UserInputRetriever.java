package com.tom.king.archives;

/**
 * Interface defining a generic way of retrieving update information
 * 
 */
public interface UserInputRetriever<T> 
{
	public T retrieveUserInput();
}
