package com.tom.king.archives.csv;

import java.io.PrintStream;
import java.util.Scanner;

import com.tom.king.archives.UserInputRetriever;

/**
 * Class that uses console to retrieve details of csv file to update from user
 * 
 */
public class ConsoleUserInputRetriever implements UserInputRetriever<CsvUpdateProperties>
{	
	public CsvUpdateProperties retrieveUserInput()
    {		
		final CsvUpdateProperties properties = updateProperties();
			
		return properties;		
    }
	
	private CsvUpdateProperties updateProperties()
	{
		final CsvUpdateProperties.Builder updatePropertiesBuilder = CsvUpdateProperties.builder();
		final PrintStream output = System.out;
		final Scanner scanner = new Scanner(System.in);
		
		output.print("Enter file path of file to update: ");
		updatePropertiesBuilder.filePath(scanner.nextLine());
			
		output.print("Enter row number to update: ");
		updatePropertiesBuilder.rowNumber(scanner.nextLine());
				
		output.print("Enter column name to update: ");
		updatePropertiesBuilder.columnName(scanner.nextLine());
				
		output.print("Enter replacement cell value: ");
		updatePropertiesBuilder.cellReplacementText(scanner.nextLine());
					
		scanner.close();
				
		return updatePropertiesBuilder.build();
	}
}
