import java.util.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageVectorizer{
    
    private BufferedImage bufferedImage;
    private int row;
    private int column;
    private int size;
    private int [][] data;


    public ImageVectorizer(String s){
        //bufferedImage = new BufferedImage(25,25,BufferedImage,TYPE_INT_RGB);
        File file = new File(s);
    try{
        bufferedImage = ImageIO.read(file);
        
        System.out.println("COLOR VALUES AS:");
        Color[] colors = {Color.red,Color.green,Color.blue,Color.white,Color.black}; 
        for(int j=0;j<colors.length;j++){
                System.out.println(colorData(colors[j].getRGB()));
        }

        System.out.println("BUFFERED ASIGNED");
        row = bufferedImage.getWidth();
        column = bufferedImage.getHeight();
        size=row*column;
        System.out.println("Size: "+size);
        System.out.println("Data sets: "+row*(column*2));
        data = new int[row][column*2];
        for(int i = 0;i<column;i++){
            for(int j = 0;j<row;j++){
                data[i][j] = colorData(bufferedImage.getRGB(j ,i));
                data[i][j+1] = colorData(bufferedImage.getRGB(j, i));
            }
        }
        printImage(data);  
    }catch(IOException E){
        System.out.println("UNABLE TO OPEN BUFFER IMAGE");
    }
    }
    public int colorData(int i){
        int v=0;
        int [] colors = new int[3];        
        colors[0]= ((i&0xff0000)>>16)*1000000;
        colors[1]= ((i&0xff00)>>8)*1000;
        colors[2]= i&0xff;
        for(int k = 0;k<colors.length;k++){
            v+=colors[k];
        }
        return v;
    }
    public void printImage(int[][]data){
        for(int i = 0;i<column;i++){
            for(int j = 0;j<row;j++){
                int value = data[i][j];
                if(value>0&&value<50001){//AZULES  1
                    System.out.print("`");System.out.print("`"); 
                }  
                else if(value>50000&&value<100000){//AZULES  2
                    System.out.print("^");System.out.print("^"); 
                }  
                else if(value>99999&&value<50000001){//VERDES 1
                    System.out.print("*");System.out.print("*"); 
                }
                else if(value>50000000&&value<100000000){//VERDES 2
                    System.out.print("/");System.out.print("/"); 
                }
                else if(value>99999999&&value<255255200){//ROJOS
                    System.out.print("+");System.out.print("+"); 
                } 
                else{//WHITE OR HIGHER
                    System.out.print(" ");System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}