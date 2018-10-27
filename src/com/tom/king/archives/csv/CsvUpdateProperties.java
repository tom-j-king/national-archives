package com.tom.king.archives.csv;

/**
 * Immutable Class that holds information on csv file to update and what to update
 */
public final class CsvUpdateProperties
{
	private String filePath;
    private String cellReplacementText;
    private String columnName;
    private int rowNumber;
    
    private CsvUpdateProperties(final CsvUpdateProperties prototype)
    {
        if (prototype != null)
        {
            filePath = prototype.filePath;
            cellReplacementText = prototype.cellReplacementText;
            columnName = prototype.columnName;
            rowNumber = prototype.rowNumber;            
        }
    }

    public static Builder builder()
    {
        return builder(null);
    }

    public static Builder builder(final CsvUpdateProperties prototype)
    {
        return new Builder(prototype);
    }
    
    public String getFilePath()
    {
        return filePath;
    }

    public String getCellReplacementText()
    {
        return cellReplacementText;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public int getRowNumber()
    {
        return rowNumber;
    }

    public static final class Builder
    {
        private final CsvUpdateProperties prototype;

        private Builder(final CsvUpdateProperties prototype)
        {
            this.prototype = new CsvUpdateProperties(prototype);
        }

        public CsvUpdateProperties build()
        {
            return new CsvUpdateProperties(prototype);
        }

        public Builder filePath(final String value)
        {
            prototype.filePath = value;
            return this;
        }

        public Builder columnName(final String value)
        {
            prototype.columnName = value;
            return this;
        }

        public Builder rowNumber(final String value)
        {
            prototype.rowNumber = Integer.parseInt(value);
            return this;
        }

        public Builder cellReplacementText(final String value)
        {
            prototype.cellReplacementText = value;
            return this;
        }
    }
}
