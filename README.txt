Student name: Federico Cecchinato
Student number: 200143109

Please complete this README file for your level 3 mini-project submission.

If your level 1/2 submission did not get full marks you MUST complete the second part of this template describing how and where your have improved your code so that it now meets the level 1 and 2 requirements. If your code still does not meet level 1 and 2 requirements then you will get 0 for level 3.

LEVEL THREE

!IMPORTANT!
To login use this credential 
username: fede
password:fede
OR register as user

To login as ADMIN use this credential 
username: admin
password: admin
OR register using the following admin key: ADMIN

GUI

The following Controllers classes HomeController.java, LoginController.java, and SignupController.java react to user interactions. For instance, in LoginController.java when the registerButton is clicked the method goToRegister (change page) is called (lines 37-46), when loginButton is clicked the method login (trying to login) is called(lines 49-75). In RegisterController.java when the loginButton is clicked the method goToLogin (change page) is called (lines 98-107) when registerButton is clicked the method register (try to register user) is called(lines 51-95).

Exceptions

Market.java line 33/49
The program needs exception handling because when reading or writing a file IOException or ClassNotFoundException could be thrown. In this instance, a message is being outputted when the exception occurs if it occurs.

Collections

Market.java lines 8-9, used at lines 64-107
ArrayList is used because the investors and stocks in the Market class are variable and for this reason, the list needs to be of variable size. In market class other methods are being created to handle CRUD operations on the lists, making code cleaner and easier to understand.

File I/O

All objects' classes that need to be saved implement the Serializable interface (Market, Stock, Price, Account). In the Market class toFile and fromFile methods are used to save/retrieve the state to/from file. In this methods FileInputStream/FileOutputStream and ObjectInputStream/ObjectOutputStream are used to retrieve/create the file and write/read the object from/to file.

'Something impressive'

The program implements an APICaller class, used by the Market class to retrieve real-time data from an API and parsed it into Stock instances. The class APICaller creates the URL based on the endpoint and options passed to the constructor or set with the setUrl method. This URL string is used in the call method, where an HTTPClient and an HTTPRequest are initialised and sent. The response is parsed into an ArrayList containing elements of type T (generic type passed to the method). 


LEVELS 1 AND 2

(ONLY complete this if you did not get full marks for your level 1/2 submission.)

LEVEL ONE

[Briefly (three sentences) explain the changes you have made to your code so that it fully meets the level one requirements.]

My code demonstrates inheritance in the following way...

I have a superclass called [Insert name of superclass here]

This superclass is extended into at least two subclasses called [Insert names of the subclasses here]

For each of the named subclasses complete the following...

Subclass 1.

Subclass [Insert name of subclass] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [Insert names of properties here.]

These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and give the line numbers in the code of where the property is used.]

Subclass [Insert name of subclass] extends the superclass by overriding the following methods (there must be at least one): [Insert names of overridden methods and their line numbers in the code.]

These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden methods are called.]

Subclass 2.

Subclass [Insert name of subclass] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [Insert names of properties here.]

These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and give the line numbers in the code of where the property is used.]

Subclass [Insert name of subclass] extends the superclass by overriding the following methods (there must be at least one): [Insert names of overridden methods and their line numbers in the code.]

These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden methods are called.]


LEVEL TWO

[Briefly (three sentences) explain the changes you have made to your code so that it fully meets the level two requirements.]

Polymorphism consists of the use of the Substitution principle and Late Dynamic binding.

In my code, polymorphism is implemented in at least two places…

Example 1.

The substitution principle can be seen in use in [class name and line number where substitution is used]. The name of the superclass used this example is [name of superclass] and the subclasses used are [names of subclasses].

Late dynamic binding can be seen in [class name and line number].

[Explain briefly (no more than four sentences), why this example of polymorphism is necessary in your code.]

Example 2.

The substitution principle can be seen in use in [class name and line number where substitution is used]. The name of the superclass used this example is [name of superclass] and the subclasses used are [names of subclasses].

Late dynamic binding can be seen in [class name and line number].

[Explain briefly (no more than four sentences), why this example of polymorphism is necessary in your code.]

