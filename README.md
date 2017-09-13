# Parkinglot
Steps for Testing the Application.


Softwares required
 - jdk 1.8
 - Maven 3.3.9
 
Test enviornment - linux


1. place the Parkinglot.tar bundle in some linux enviornment.
2. untar the bundle :  tar -xvf Parkinglot.tar
3. cd Parkinglot 
4. Testing via file input : ./parking_lot.sh file_inputs.txt 

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
	

7. Testing via command argment : ./parking_lot.sh 
  
	I/p:   
	create_parking_lot 6
	op:
	Created a parking lot with 6 slots
	ip:
	park KA-01-HH-1234 White
	op:
	Allocated slot number: 1
	ip:
	park KA-01-HH-9999 White
	op:
	Allocated slot number: 2
	ip:
	park KA-01-BB-0001 Black
	op:
	Allocated slot number: 2
	ip:
	park KA-01-HH-7777 Red
	op:
	Allocated slot number: 4
	
	ip:
	park KA-01-HH-2701 Blue
	op:
	Allocated slot number: 5
	ip:
	park KA-01-HH-3141 Black
	op:
	Allocated slot number: 6
	ip:
	leave 4
	op:
	Slot number 4 is free
	ip:
	status
	op:
	Slot No Registration No   Colour
	 1       KA-01-HH-1234    White
	 2       KA-01-HH-9999    White
	 3       KA-01-BB-0001    Black
	 5       KA-01-HH-2701     Blue
	 6       KA-01-HH-3141    Black
	 
	ip:
	park KA-01-P-333 White
	op:
	Allocated slot number: 4
	ip:
	park DL-12-AA-9999 White
	op:
	Sorry, parking lot is full
	ip:
	registration_numbers_for_cars_with_colour White
	op:
	KA-01-HH-1234,KA-01-HH-9999,KA-01-P-333
	ip:
	slot_numbers_for_cars_with_colour White
	op:
	1,2,4
	ip:
	slot_number_for_registration_number KA-01-HH-3141
	op: 
	6
	ip:
	slot_number_for_registration_number MH-04-AY-1111
	op:
	Not found
	
Note : Taken assumption that each parking level will have 20 slots as max limit , more than which vehicle will be parked in the next level. This is configurable property and this can be changed in future as per need.

For ex : if parking manager ask for 25 slots then 20 slots will be created in Level0 and 5 slots will be created in level1, since default max slots per level is 20. Hope this is clear.

