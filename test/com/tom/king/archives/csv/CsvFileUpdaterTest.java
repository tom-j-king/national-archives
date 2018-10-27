package com.tom.king.archives.csv;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tom.king.archives.csv.CsvUpdateProperties;
import com.tom.king.archives.csv.CsvFileUpdater;

public final class CsvFileUpdaterTest
{	
	private CsvFileUpdater fileUpdater;	
	private List<String[]> stubbedCsvBody;
	private CsvUpdateProperties stubbedUpdateProperties;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUp() throws IOException
	{		
		System.setOut(new PrintStream(outContent));		
		createStubbedCsvBody();
		createStubbedCvsUpdateProperties();
		
		fileUpdater = new CsvFileUpdater(stubbedUpdateProperties);
	}
	
	@After
	public void restore() 
	{
	    System.setOut(originalOut);	    
	}
	
	@Test
	public void testRetrieveCsvBody() throws IOException
	{
		final Reader reader = new StringReader("filename,origin,metadata,hash\n");
				
		final List<String[]> csvBody = fileUpdater.retrieveCsvBody(reader);
		assertEquals(1, csvBody.size());
		
		final String[] row = csvBody.get(0);
		final String firstColumn = row[0];
		final String secondColumn = row[1];
		final String thirdColumn = row[2];
		final String fourthColumn = row[3];
		
		assertEquals("filename", firstColumn);
		assertEquals("origin", secondColumn);
		assertEquals("metadata", thirdColumn);
		assertEquals("hash", fourthColumn);
	}
	
	@Test
	public void testRetrieveCsvBody_emptyReader() throws IOException
	{
		final Reader reader = new StringReader("");
		
		final List<String[]> csvBody = fileUpdater.retrieveCsvBody(reader);
		assertEquals(0, csvBody.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testRetrieveCsvBody_nullReader() throws IOException
	{
		fileUpdater.retrieveCsvBody(null);
	}
	
	@Test
	public void testUpdateCsvBody() throws IOException
	{				
		final StringWriter writer = new StringWriter();
		
		fileUpdater.updateCsvBody(stubbedCsvBody, writer);
		final String updatedCell = stubbedCsvBody.get(2)[1];
		
		assertEquals("London", updatedCell);		
	}
	
	@Test
	public void testUpdateCsvBody_unknownHeaderName() throws IOException
	{		
		stubbedUpdateProperties = CsvUpdateProperties.builder()
				.cellReplacementText("replacement text")
				.columnName("XXXX")
				.filePath("filePath")
				.rowNumber("2")
				.build();
		
		final CsvFileUpdater fileUpdaterUnknownHeader = new CsvFileUpdater(stubbedUpdateProperties);
		
		final StringWriter writer = new StringWriter();
		
		fileUpdaterUnknownHeader.updateCsvBody(stubbedCsvBody, writer);
		final String updatedCell = stubbedCsvBody.get(2)[1];
		
		assertEquals("Londom", updatedCell);
		assertEquals("Unable to update Csv file as column could not be found", outContent.toString());
	}
	
	private void createStubbedCsvBody() throws IOException
	{
		final String[] header = {"filename", "origin", "metadata", "hash"};		
		final String[] row1 = {"file2", "Surrey", "\"a file about The National Archives\"", "hash1"};
		final String[] row2 = {"file55", "Londom", "\"London was initially incorrectly spelled as Londom\"", "hash2"};
		
		stubbedCsvBody = new ArrayList<String[]>();
		stubbedCsvBody.add(header);
		stubbedCsvBody.add(row1);
		stubbedCsvBody.add(row2);
	}
	
	private void createStubbedCvsUpdateProperties()
	{
		stubbedUpdateProperties = CsvUpdateProperties.builder()
				.cellReplacementText("London")
				.columnName("origin")
				.filePath("filePath")
				.rowNumber("2")
				.build();
	}
}
