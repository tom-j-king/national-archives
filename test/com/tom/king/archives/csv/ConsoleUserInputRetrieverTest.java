package com.tom.king.archives.csv;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tom.king.archives.csv.ConsoleUserInputRetriever;

public class ConsoleUserInputRetrieverTest 
{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent;
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;
		
	@Before
	public void setUp() 
	{
		setUpStubbedUserInput();		
		System.setOut(new PrintStream(outContent));
	    System.setIn(inContent);	    
	}

	@After
	public void restore() 
	{
	    System.setOut(originalOut);
	    System.setIn(originalIn);	    
	}
	
	@Test
	public void testRetrieverUserInputFromConsole()
	{
		final ConsoleUserInputRetriever inputRetriever = new ConsoleUserInputRetriever();
		
		final String expectedOutput = "Enter file path of file to update: "
				+ "Enter row number to update: "
				+ "Enter column name to update: "
				+ "Enter replacement cell value: ";
		
		final CsvUpdateProperties properties = inputRetriever.retrieveUserInput();
		assertEquals(expectedOutput, outContent.toString());
		assertEquals("C:/file/path", properties.getFilePath());
		assertEquals("column", properties.getColumnName());
		assertEquals(1, properties.getRowNumber());
		assertEquals("replacement text", properties.getCellReplacementText());
	}
	
	private void setUpStubbedUserInput()
	{
		final String stubbedUserInput = "C:/file/path\n"
				+ "1\n"
				+ "column\n"
				+ "replacement text";
		
		inContent = new ByteArrayInputStream(stubbedUserInput.getBytes());
	}
}
