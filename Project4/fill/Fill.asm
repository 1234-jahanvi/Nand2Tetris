// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(LOOP)
//Checking keys
@KBD
D=M

@BLACK
D;JGT       //jumps to BLACK if KBD>0

@WHITE
0;JMP      //jumps to WHITE if KBD==0

(BLACK)
@R0
M=-1        // Making it black 1111111111111111
@DRAW
0;JMP

(WHITE)
@R0
M=0         //Making it white 0000000000000000
@DRAW
0;JMP

(DRAW)
@8191
D=A
@R1
M=D

(NEXT)
    //Position is calculated
    @R1
    D=M
    @pos
    M=D
    @SCREEN
    D=A
    @pos
    M=M+D

    //Draws on the screen
    @R0
    D=M
    @pos
    A=M
    M=D

    @R1
    M=M-1   //Decrement counter in R1
    D=M

    @NEXT   //Jump if counter>=0
    D;JGE

@LOOP       //infinite loop
0;JMP