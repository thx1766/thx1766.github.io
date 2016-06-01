/************************************************************************
  			block.java - Copyright schaffna

Here you can write a license for your code, some comments or any other
information you want to have in your generated code. To to this simply
configure the "headings" directory in uml to point to a directory
where you have your heading files.

or you can just replace the contents of this file with your own.
If you want to do this, this file is located at

/usr/share/apps/umbrello/headings/heading.java

-->Code Generators searches for heading files based on the file extension
   i.e. it will look for a file name ending in ".h" to include in C++ header
   files, and for a file name ending in ".java" to include in all generated
   java code.
   If you name the file "heading.<extension>", Code Generator will always
   choose this file even if there are other files with the same extension in the
   directory. If you name the file something else, it must be the only one with that
   extension in the directory to guarantee that Code Generator will choose it.

you can use variables in your heading files which are replaced at generation
time. possible variables are : author, date, time, filename and filepath.
just write %variable_name%

This file was generated on %date% at %time%
The original location of this file is /ilab/users/schaffna/uml-generated-code/block.java
**************************************************************************/


import java.util.*;


/**
 * Class block
 */
public class block {

  //
  // Fields
  //

  protected int pushable;
  protected int multiblock;
  protected int passable;
  
  //
  // Constructors
  //
  public block () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of pushable
   * @param newVar the new value of pushable
   */
  protected void setPushable ( int newVar ) {
    pushable = newVar;
  }

  /**
   * Get the value of pushable
   * @return the value of pushable
   */
  protected int getPushable ( ) {
    return pushable;
  }

  /**
   * Set the value of multiblock
   * @param newVar the new value of multiblock
   */
  protected void setMultiblock ( int newVar ) {
    multiblock = newVar;
  }

  /**
   * Get the value of multiblock
   * @return the value of multiblock
   */
  protected int getMultiblock ( ) {
    return multiblock;
  }

  /**
   * Set the value of passable
   * @param newVar the new value of passable
   */
  protected void setPassable ( int newVar ) {
    passable = newVar;
  }

  /**
   * Get the value of passable
   * @return the value of passable
   */
  protected int getPassable ( ) {
    return passable;
  }

  //
  // Other methods
  //

}
