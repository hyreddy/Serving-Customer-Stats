Assumptions Used in Creating ServingCustomers
1) The earliest a customer can start waiting in line is at 7:00:00 AM
2) The latest a customer can start waiting in line is at 6:59:00 PM
3) Any time spent in line before 9:00:00 AM is considered as time spent waiting in line
4) If a queried customer id that is not in the customersfile.txt is inputed, the resulting waiting time is 0

Steps for Running ServingCustomers
Using Eclipse
1) Go to Eclipse and create an empty java project
2) Use the import file feature and import the folder "ServingCustomers" that is within the folder Yeluru_HW3 into the java project
3) Import the customer and query files (i.e customersfile.txt queriesfile.txt) into the java project
4) Open the java file "RunServingCustomers"
5) Go to file's "Run Configurations" within eclipse and ensure the arguments are set to "customersfile.txt queriesfile.txt" or whatever the inputed files are called
6) Press Run

Using Command Line
1) Unzip Yeluru_HW3 and store all the .java files within the "src" folder that is within the folder "ServingCustomers" in the same folder
2) Ensure that the customersfile.txt and queriesfile.txt are in that same folder with the .java files
3) Open command line and get to the directory containin the .java and .txt files
4) Compile RunServingCustomers.java (javac "RunServingCustomers.java")
5) Run RunServingCustomers.java with the inputed .txt files as arguments (java RunServingCustomers customersfile.txt queriesfile.txt)
