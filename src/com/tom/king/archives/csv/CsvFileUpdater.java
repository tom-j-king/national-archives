package com.tom.king.archives.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.tom.king.archives.FileUpdater;

/**
 * Class that performs update operation on csv file
 * 
 */
public class CsvFileUpdater implements FileUpdater
{   
    private CsvUpdateProperties updateProperties;    
    	
	public CsvFileUpdater(final CsvUpdateProperties updateProperties)
	{
		this.updateProperties = updateProperties;
	}
    
    public void runUpdate()
    {
    	try 
		{    		
    		updateFile();
			System.out.print("File " + updateProperties.getFilePath() + " updated");
		} 
		catch (IOException e) 
		{			
			System.out.print("File " + updateProperties.getFilePath() + " was not updated\n");
			e.printStackTrace();
		}		
	}   

	public void updateFile() throws IOException 
	{
		final String fileToUpdate = updateProperties.getFilePath();
		final FileReader reader = new FileReader(fileToUpdate);
								
		final List<String[]> csvBody = retrieveCsvBody(reader);
		final Writer writer = new FileWriter(fileToUpdate);
		
		updateCsvBody(csvBody, writer);
		reader.close();    		
	}
	
	public List<String[]> retrieveCsvBody(final Reader fileToUpdate) throws IOException
	{		
		final CSVReader csvReader = new CSVReader(fileToUpdate);		
		
		final List<String[]> csvBody = csvReader.readAll();
		csvReader.close();
		
		return csvBody;
	}
	
	public void updateCsvBody(
			final List<String[]> csvBody,			
			final Writer writer) throws IOException
	{
		final String colToUpdate = updateProperties.getColumnName();
		final String[] columnNames = csvBody.get(0);
		final int columnIndex = retrieveColumnIndexFromName(columnNames, colToUpdate);
		
		if (columnIndex == -1)
		{			
			System.out.print("Unable to update Csv file as column could not be found");			
			return;
		}
		
		final int rowToUpdate = updateProperties.getRowNumber();		
		final String replacementText = updateProperties.getCellReplacementText();
		
		csvBody.get(rowToUpdate)[columnIndex] = replacementText;
		
		final CSVWriter csvWriter = new CSVWriter(writer);
    	
    	csvWriter.writeAll(csvBody);
    	csvWriter.flush();
    	csvWriter.close();
	}

	private int retrieveColumnIndexFromName(final String[] columnNames, final String selectedColumnName)
	{
		final List<String> columns = Arrays.asList(columnNames);
		final int columnIndex = columns.indexOf(selectedColumnName);		
		
		return columnIndex;
	}
}
