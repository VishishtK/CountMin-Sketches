Explanation of Files

Counter.java
This is an abstract class that is extended by CountMin and CounterSketch class. It contains
all the common functions and some abstract ones

CountMin.java
Extending Counter.java and while querying only picking the min value from all the CounterSketch

CountSketch.java
Extending Counter.java and while recording and querying we check the MSB of the hash and 
decide +- sign of the count

ActiveCounter.java
Using a int for number and exponential part which is 32 bit and taking an and with 0xffff to bring it down to 16 bits

HashFunctions.java
This is the class that implements the hashing functionality with the FNV hashing algorightm.
Each HashTable has an object of HashFunctions class which it uses to Hash the flowID.

App.java
This file is the main driver file which initiates the running of the programmed and tests
the algorithms with the dummy values mentioned in the assignment.