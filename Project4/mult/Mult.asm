// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

@R0
D=M
@num1
M=D     //num1=RAM[0]

@R1
D=M
@num2
M=D     //num2=RAM[1]

@R2
M=0     //initialized destination to 0

@result
M=0     //result initialized to 0

@i
M=1    // i initialized to 0

(LOOP)
@i
D=M
@num2
D=D-M
@STOP
D;JGT  // jump to STOP if (i-R1 > 0)

@result
D=M     
@num1
D=D+M
@result
M=D     //result=result+R0

@i
M=M+1   //i=i+1

@LOOP
0;JMP   //Loop ends

(STOP)
@result
D=M
@R2
M=D

(END)
@END
0;JMP   //Infinite Loop