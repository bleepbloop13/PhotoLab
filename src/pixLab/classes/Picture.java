package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void zeroRed()
  {
	  Pixel[][] redPixels = this.getPixels2D();
	  for(int row = 0; row < redPixels.length; row++)
	  {
		  for(int col = 0; col < redPixels[0].length; col++)
		  {
			  redPixels[row][col].setRed(0);
		  }
	  }
  }
  
  public void zeroGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void negateFilter()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for(Pixel[] rowArray: pixels)
	  {
		  for(Pixel pixelObj : rowArray)
		  {
			  int newRed = pixelObj.getGreen();
			  int newBlue = pixelObj.getRed();
			  int newGreen = pixelObj.getBlue();
			  
			  pixelObj.setRed(newRed);
			  pixelObj.setBlue(newBlue);
			  pixelObj.setGreen(newGreen);
		  }
	  }
  }
  
  public void keepOnlyGreen()
  {
	  zeroRed();
	  zeroBlue();
  }
  
  public void keepOnlyRed()
  {
	  zeroBlue();
	  zeroGreen();
  }
  
  public void keepOnlyBlue()
  {
	  zeroRed();
	  zeroGreen();
  }
  
  public void greyscaleFilter()
  {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	    	  int grey = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen() / 3); 
	    	  pixelObj.setRed(grey);
	    	  pixelObj.setBlue(grey);
	    	  pixelObj.setGreen(grey);
	      }
	    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalRightToLeft()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int width = pixels[0].length;
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = width - 1; col > width / 2; col--)
		  {
			  rightPixel = pixels[row][col];
			  leftPixel = pixels[row][(width / 2) - (col - width/2)];
			  leftPixel.setColor(rightPixel.getColor());
		  }
	  }
  }
  
  public void mirrorHorizontal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  for (int row = 0; row < height/2; row++)
	  {
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[height - 1 - row][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void mirrorHorizontalBottomToTop()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  for (int row = height - 1; row > height/2; row--)
	  {
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[(height / 2) - (row - height/2)][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void mirrorGull()
  {
	  int mirrorPoint = 330;
	  int mirrorPoint2 = 370;
	  Pixel firstPixel = null;
	  Pixel secondPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  for(int row = 230; row < 340; row++)
	  {
		  for(int col = 230; col < 330; col++)
		  {
			  firstPixel = pixels[row][col];
			  secondPixel = pixels[mirrorPoint + row - mirrorPoint][mirrorPoint2 - col + mirrorPoint2];
			  secondPixel.setColor(firstPixel.getColor());
		  }
	  
	  }
  }
  
  public void fixUnderwater()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for(int row = 0; row < pixels.length; row++)
	  {
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  Pixel currentPixel = pixels[row][col];
			  int currentBlue = currentPixel.getBlue();
			  int currentGreen = currentPixel.getGreen();
			  currentPixel.setGreen(currentGreen - 55);
			  currentPixel.setBlue(currentBlue - 35);
		  }
	  }
  }
  
  public void mirrorArms()
  {
	  int mirrorPoint = 206;
	  int mirrorPoint2 = 192;
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for(int row = 158; row < 194; row++)
	  {
		  for(int col = 102; col < 172; col++)
		  {
			  leftPixel = pixels[row][col];
			  rightPixel = pixels[mirrorPoint2 - row + mirrorPoint2][mirrorPoint - col + mirrorPoint];
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
	  
	  int mirrorPoint3 = 198;
	  for (int row = 168; row < 192; row++)
	  {
		  for(int col = 168; col < 290; col++)
		  {
			  leftPixel = pixels[row][col];
			  rightPixel = pixels[mirrorPoint3 - row + mirrorPoint3][mirrorPoint - col + mirrorPoint];
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture face = new Picture("saitama.jpg");
    Picture faceKeepGreen = new Picture(face);
    faceKeepGreen.keepOnlyGreen();
    Picture faceKeepRed = new Picture(face);
    faceKeepRed.keepOnlyRed();
    Picture faceNoBlue = new Picture(face);
    faceNoBlue.zeroBlue();
    Picture faceNoGreen = new Picture(face);
    faceNoGreen.zeroGreen();
    Picture faceNoRed = new Picture(face);
    faceNoRed.zeroRed();
    this.copy(face,0, 479);
    this.copy(faceKeepRed,100,479);
    this.copy(faceNoBlue,200,479);
    this.copy(faceNoGreen,300,479);
    this.copy(faceNoRed, 400,479);
    this.copy(faceKeepGreen,500,479);
    this.mirrorVerticalRightToLeft();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture saitama = new Picture("saitama_face.jpg");
    Picture saitamaFace = new Picture("saitama.jpg");
    Picture blueMotorcycle = new Picture("blueMotorcycle.jpg");
    Picture temple = new Picture("seagull.jpg");
    Picture snowman = new Picture("snowman.jpg");
    saitama.zeroBlue();
    saitama.copy(saitamaFace, 70, 30);
    saitama.mirrorVertical();
    saitama.explore();
    temple.negateFilter();
    temple.explore();
    
  }
  
} // this } is the end of class Picture, put all new methods before this
