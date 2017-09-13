# Parkinglot
Steps for Testing the Application.


Softwares required
 - jdk 1.8
 - Maven 3.3.9
 
Test enviornment - linux


1. place the Parkinglot.tar bundle in some linux enviornment.
2. untar the bundle -  tar -xvf Parkinglot.tar
3. cd Parkinglot 
4. Maven Build - mvn clean and mvn build
5. move to target folder : cd target


Note : Taken assumption that each parking level will have 20 slots as max limit , more than which vehicle will be parked in the next level. This is configurable property and this can be changed in future as per need.

For ex : if parking manager ask for 25 slots then 20 slots will be created in Level0 and 5 slots will be created in level1, since default max slots per level is 20. Hope this is clear.



6. Testing via file input : java -jar Parkinglot-0.0.1-SNAPSHOT.jar ../file_inputs.txt 

	Created a parking lot with 6 slots
	Allocated slot number: 1
	Allocated slot number: 2
	Allocated slot number: 3
	Allocated slot number: 4
	Allocated slot number: 5
	Allocated slot number: 6
	Slot number 4 is free
	Slot No Registration No   Colour
	 1       KA-01-HH-1234    White
	 2       KA-01-HH-9999    White
	 3       KA-01-BB-0001    Black
	 5       KA-01-HH-2701     Blue
	 6       KA-01-HH-3141    Black
	Allocated slot number: 4
	Sorry, parking lot is full
	KA-01-HH-1234,KA-01-HH-9999,KA-01-P-333
	1,2,4
	6
	Not found
	

7. Testing via command argment :  java -jar Parkinglot-0.0.1-SNAPSHOT.jar 
  
	Input :   
	create_parking_lot 6
	park KA-01-HH-1234 White
	park KA-01-HH-9999 White
	park KA-01-BB-0001 Black
	park KA-01-HH-7777 Red
	park KA-01-HH-2701 Blue
	park KA-01-HH-3141 Black
	leave 4
	status
	park KA-01-P-333 White
	park DL-12-AA-9999 White
	registration_numbers_for_cars_with_colour White
	slot_numbers_for_cars_with_colour White
	slot_number_for_registration_number KA-01-HH-3141
	slot_number_for_registration_number MH-04-AY-1111
	
	Output:
	
	Created a parking lot with 6 slots
	Allocated slot number: 1
	Allocated slot number: 2
	Allocated slot number: 3
	Allocated slot number: 4
	Allocated slot number: 5
	Allocated slot number: 6
	Slot number 4 is free
	Slot No Registration No   Colour
	 1       KA-01-HH-1234    White
	 2       KA-01-HH-9999    White
	 3       KA-01-BB-0001    Black
	 5       KA-01-HH-2701     Blue
	 6       KA-01-HH-3141    Black
	Allocated slot number: 4
	Sorry, parking lot is full
	KA-01-HH-1234,KA-01-HH-9999,KA-01-P-333
	1,2,4
	6
	
	Not found


