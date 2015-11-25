# Matrix-Calculator
A program that applies common functions to matrices and vectors.

Created by: Lily Lau, Bruck Woldeyes, Samuel Isang

*******************************************************************************************************
Using Our Program
*******************************************************************************************************

Compile the source files in command line.	
Run StartHere on the command line.

javac *.java
java StartHere

######################################################################################################
IMPORTANT NOTE ABOUT .DAT FILE INPUT
You can enter a matrix from a .dat file ONLY if the entries are separated by spaces OR commas.
It will not do both.

GOOD:
1 1 1 1
1 2 3 4
1 3 6 10
1 4 10 20

GOOD:
1,1,1,1
1,2,3,4
1,3,6,10
1,4,10,20

BAD:
1, 1, 1, 1
1, 2, 3, 4
1, 3, 6, 10
1, 4, 10, 20
######################################################################################################

Start provides 8 options:
(0) Exit
(1) LU Factorization
(2) QR House Holder
(3) QR Givens
(4) Jacobi Iteration
(5) Gauss Seidel Iteration
(6) Power Method
(7) Run LU/QR on ALL Pascal Matrices Size 2-12

(0) Enxits the Program

(1) LU Factorization has 4 options:
	0) Go Back
	1) Generate a Pascal Matrix [Decompose/Solve]
        2) Enter a .dat file that contains an nxn matrix [Decompose ONLY]
        3) Enter a .dat file that contains an augmented matrix [Decompose/Solve]

	0- Back to menu
	1- Generates a Pascal Matrix based on user input. 
		Performs LU Factorization and solves LU for b.
	2- Takes in a .dat file that contains a matrix and performs LU Factorization.
	3- Takes in a .dat file that contains an augmented matrix.
		Performs LU Factorization and solves for x.

(2) QR House Holder has 4 options:
	0) Go Back
        1) Generate a Pascal Matrix [Factor/Solve]
        2) Enter a .dat file that contains an nxn matrix [Factor ONLY]
        3) Enter a .dat file that contains an augmented matrix [Factor/Solve]

	0- Back to menu
	1- Generates a Pascal Matrix based on user input. 
		Performs QR House Holder Factorization and solves for b.
	2- Takes in a .dat file that contains a matrix and performs QR House Holder Factorization.
	3- Takes in a .dat file that contains an augmented matrix.
		Performs QR House Holder Factorization and solves for x.

(3) QR Givens has 4 options:
	0) Go Back
        1) Generate a Pascal Matrix [Factor/Solve]
        2) Enter a .dat file that contains an nxn matrix [Factor ONLY]
        3) Enter a .dat file that contains an augmented matrix [Factor/Solve]

	0- Back to menu
	1- Generates a Pascal Matrix based on user input. 
		Performs QR Givens Factorization and solves for b.
	2- Takes in a .dat file that contains a matrix and performs QR Givens Factorization.
	3- Takes in a .dat file that contains an augmented matrix.
		Performs QR Givens Factorization and solves for x.

######################################################################################################
IMPORTANT ABOUT JACOBI/GAUSS
When entering the vector through command line, you NEED to have a space in between the brackets.
[ .1 .1 .1 ]

Typing in the vector without spaces between the brackets will result in an IndexOutOfBounds exception.
DO NOT do this [.1 .1 .1]
Thanks :3
######################################################################################################

(4) Jacobi Iteration
	-Takes in a vector that needs to be manually entered by the user
	-Prints out the Solution Vector x
	-Prints out the number of iterations it took
	-Prints out "Does not converge" 
		if the matrix takes longer than the max number of iterations.

(5) Gauss Seidel Iteration
	-Takes in a vector that needs to be manually entered by the user
	-Prints out the Solution Vector x
	-Prints out the number of iterations it took
	-Prints out "Does not converge" 
		if the matrix takes longer than the max number of iterations.

(6) Power Method
	-Takes in a .dat file with an augmented matrix.
	-Asks the user to manually enter in tolerance and max number of iterations.
	-Prints out the approximate eigenvalue, eigenvectors, 
		and number of iterations taken to complete the power method.
	-Prints out "Does not converge" 
		if the matrix takes longer than the max number of iterations.

(7) Run Decomposition on all Pascal Matrices Size 2-12
	-For each Pascal matrix from sizes 2-12, this options will
	1) Run the LU Factorization and solve it
	2) Run QR House Holder Factorization and solve it
	3) Run QR Givens Factorization and solve it
	4) Prints out the solutions and errors for each

*******************************************************************************************************
MatrixCalculator/Testing
*******************************************************************************************************
In this class, we have all the methods that were required at the very top.
You can call MaxtrixCalculator.method() in your own main method if you
want to test it without using the driver class.

*******************************************************************************************************
Graphing Our Data
*******************************************************************************************************
	For part 1 and part 2, we wrote FactorizationGraph and JacobiGaussGraph.
This class writes the data to a .txt file and then reads from the .txt files and 
writes to an .xsl file. To do this, I downloaded/imported the JExcelAPI. 
It, however, will not compile if you do not have the JExcelAPI downloaded.
For this reason, I have commented out the portion of code that writes to an excel. 
You can still inspect it manually.

	For part 3, I had to change the opacity of each point corresponding to 
different iterations values. In order to do this, I used JavaFX and had to create 
a separate graphing project to do this. I have included this file, but it cannot 
be run because our main project is not a JavaFX project. The file  is called 
ScatterChartSample.java and can be inspected manually. It reads from a .txt file,
which I wrote to in the PowerGraph class.

