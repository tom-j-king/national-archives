package com.tom.king.archives.csv;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tom.king.archives.csv.CsvUpdateProperties;

public class CsvUpdatePropertiesTest 
{
	@Before
	public void setUp()
	{
		
	}
	
	@Test
	public void testCsvUpdateProperties()
	{
		final CsvUpdateProperties defaultProperties = CsvUpdateProperties.builder()
				.cellReplacementText("cell replacement text")
				.columnName("column name")
				.filePath("file to update")
				.rowNumber("1")
				.build();
		
		assertEquals("cell replacement text", defaultProperties.getCellReplacementText());
		assertEquals("column name", defaultProperties.getColumnName());
		assertEquals("file to update", defaultProperties.getFilePath());
		assertEquals(1, defaultProperties.getRowNumber());
	}
	
	@Test
	public void testCsvUpdateProperties_defaultValues()
	{
		final CsvUpdateProperties defaultProperties = CsvUpdateProperties.builder()
				.build();
		
		assertEquals(null, defaultProperties.getCellReplacementText());
		assertEquals(null, defaultProperties.getColumnName());
		assertEquals(null, defaultProperties.getFilePath());
		assertEquals(0, defaultProperties.getRowNumber());
	}
	
	@Test
	public void testCsvUpdateProperties_nullPrototype()
	{
		final CsvUpdateProperties defaultProperties = CsvUpdateProperties
				.builder(null)
				.build();
		
		assertEquals(null, defaultProperties.getCellReplacementText());
		assertEquals(null, defaultProperties.getColumnName());
		assertEquals(null, defaultProperties.getFilePath());
		assertEquals(0, defaultProperties.getRowNumber());
	}
	
	@Test(expected = NumberFormatException.class)
	public void testCsvUpdateProperties_rowNumberNotInt()
	{
		CsvUpdateProperties.builder()				
				.rowNumber("z")
				.build();
	}
	
}
