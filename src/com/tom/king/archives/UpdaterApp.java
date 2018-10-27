package com.tom.king.archives;

import java.util.Arrays;
import java.util.List;

import com.tom.king.archives.csv.ConsoleUserInputRetriever;
import com.tom.king.archives.csv.CsvFileUpdater;
import com.tom.king.archives.csv.CsvUpdateProperties;

/**
 * Entry point Class for running file update tasks
 */
public class UpdaterApp 
{	
	public static void updateRunner(final List<FileUpdater> updaterTasks)
	{
		if (updaterTasks == null)
		{
			return;
		}
		
		for (final FileUpdater task : updaterTasks)
		{
			task.runUpdate();
		}
	}
	
	public static void main(String[] args) 
	{
		final ConsoleUserInputRetriever inputRetriever = new ConsoleUserInputRetriever();
		final CsvUpdateProperties updateProperties = inputRetriever.retrieveUserInput();
		
		final FileUpdater fileUpdater = new CsvFileUpdater(updateProperties);
		
		final List<FileUpdater> updaterTasks = Arrays.asList(fileUpdater);		
		
		updateRunner(updaterTasks);
	}	
}
