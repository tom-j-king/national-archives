# national-archives
Technical test for National Archives

Test requirements
(1) Write a piece of Java code following TDD principles that allows an update to a csv file, see example structure below;
(2) Code should allow the update of a specific element, identified by column title and row index, with a new value;
(3) It should enable row three, column 'origin',  value Londom to be replaced with London in the example structure below
(4) Code should be available on GitHub

Example CSV file structure

filename, origin, metadata, hash
file1, London, "a file about London", e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6
file2, Surrey, "a file about The National Archives", a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564967
file55, Londom, "London was initially incorrectly spelled as Londom", e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6
file4, Penrith, "Lake District National Park info", a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564968
