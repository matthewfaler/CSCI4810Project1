import java.awt.*;
import java.awt.image.*;

import java.lang.*;

import java.util.Scanner;

import javax.swing.*;

public class Image {
    
    static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private static JFrame frame;
    private static JLabel label;
    public BufferedImage img;

    public static void main(String[] args) {
        Image im = new Image();
        im.img = new BufferedImage(d.width, d.height - 110, BufferedImage.TYPE_INT_RGB);
        System.out.println("Width: " + d.width + " Height: " + (d.height - 110));
        Scanner s = new Scanner(System.in);
        int alg;
        im.display(im.img);
        System.out.println("Type '1' for Basic Line Drawing. Type '2' for Bresenham.");
        alg = s.nextInt();
        int n;
        if(alg == 1) {
            System.out.println("How many lines would you like to draw?");
            n = s.nextInt();
            if(n <= 0) {
                while(n <= 0) {
                    System.out.println("Invalid number. Please enter a positive number greater than 0.");
                    n = s.nextInt();
                }
            }
            im.display(im.img);
            while(n > 0) {
                im.img = im.basicLine(im.img, (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)), (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)));
                im.display(im.img);
                n--;
            }
        } else if (alg == 2) {
            System.out.println("How many lines would you like to draw?");
            n = s.nextInt();
            if(n <= 0) {
                while(n <= 0) {
                    System.out.println("Invalid number. Please enter a positive number greater than 0.");
                    n = s.nextInt();
                }
            }
            im.display(im.img);
            while(n > 0) {
                im.img = im.bresenham(im.img, (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)), (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)));
                im.display(im.img);
                n--;
            }
        } else {
            System.out.println("You chose neither of the options. Please enter a '1' or a '2'.");
            alg = s.nextInt();
            if(alg != 1 && alg != 2) {
                while(alg != 1 && alg != 2) {
                    System.out.println("Invalid number. Please enter one of the options.");
                    alg = s.nextInt();
                }
            }
            if(alg == 1) {
                System.out.println("How many lines would you like to draw?");
                n = s.nextInt();
                if(n <= 0) {
                    while(n <= 0) {
                        System.out.println("Invalid number. Please enter a positive number greater than 0.");
                        n = s.nextInt();
                    }
                }
                im.display(im.img);
                while(n > 0) {
                    im.img = im.basicLine(im.img, (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)), (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)));
                    im.display(im.img);
                    n--;
                }
            } else {
                System.out.println("How many lines would you like to draw?");
                n = s.nextInt();
                if(n <= 0) {
                    while(n <= 0) {
                        System.out.println("Invalid number. Please enter a positive number greater than 0.");
                        n = s.nextInt();
                    }
                }
                im.display(im.img);
                while(n > 0) {
                    im.img = im.bresenham(im.img, (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)), (int)(Math.random() * d.width), (int)(Math.random() * (d.height - 110)));
                    im.display(im.img);
                    n--;
                }
            }
        }
        im.display(im.img);
    }

    public void display(BufferedImage image){
        if(frame == null){
            frame = new JFrame();
            frame.setTitle("Assignment 1");
            frame.setSize(d.width, d.height);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            label = new JLabel();
            label.setIcon(new ImageIcon(image));
            frame.getContentPane().add(label,BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        } else label.setIcon(new ImageIcon(image));
    }

    public BufferedImage basicLine(BufferedImage bi, int x0, int y0, int x1, int y1) {
        int delX = Math.max(x0, x1) - Math.min(x0, x1);
        int delY = Math.max(y0, y1) - Math.min(y0, y1);
        double m;
        System.out.println("x0: " + x0 + " " + "x1: " + x1 + " " + "y0: " + y0 + " "+ "y1: " + y1);
        m = ((double)(y1 - y0))/((double)(x1 - x0));
        System.out.println(m);
        int x;
        int y;
        if(m <= 0.5 && m > -0.5) {
            if(x0 > x1) {
                int tmp = x0;
                x0 = x1;
                x1 = tmp;
                tmp = y0;
                y0 = y1;
                y1 = tmp;
            }
            for(int i = 0; i <= delX - 1; i++) {
                x = x0 + i;
                y = (int)(m*i) + y0;
                img.setRGB(x, y, 0xFFFFFF);
            }
        } else {
            if(y0 > y1) {
                int tmp = x0;
                x0 = x1;
                x1 = tmp;
                tmp = y0;
                y0 = y1;
                y1 = tmp;
            }
            m = ((double)(x1-x0))/((double)(y1-y0));
            for(int i = 0; i <= delY - 1; i++) {
                y = y0 + i;
                x = (int)(m*i) + x0;
                img.setRGB(x, y, 0xFFFFFF);
            }
        }
        return bi;
    }

    public BufferedImage bresenham(BufferedImage bi, int x0, int y0, int x1, int y1) {
        int delX = Math.max(x0, x1) - Math.min(x0, x1);
        int delY = Math.max(y0, y1) - Math.min(y0, y1);
        System.out.println("x0: " + x0 + " " + "x1: " + x1 + " " + "y0: " + y0 + " "+ "y1: " + y1);
        double m = ((double)(y1 - y0))/((double)(x1 - x0));
        System.out.println(m);
        if(m <= 0.5 && m > -0.5) {
            int E = (2 * delY) - delX;
            int incOne = 2 * delY;
            int incTwo = 2 * (delY - delX);
            if(x0 > x1) {
                int tmp = x0;
                x0 = x1;
                x1 = tmp;
                tmp = y0;
                y0 = y1;
                y1 = tmp;
            }
            int yInc = 1;
            if(m < 0) yInc = -1;
            int x = x0;
            int y = y0;
            while(x < x1) {
                bi.setRGB(x, y, 0xFFFFFF);
                if(E < 0) {
                    E = E + incOne;
                } else {
                    y = y + yInc;
                    E = E + incTwo;
                }
                x++;
            }
        } else {
            int E = (2 * delX) - delY;
            int incOne = 2 * delX;
            int incTwo = 2 * (delX - delY);
            if(y0 > y1) {
                int tmp = x0;
                x0 = x1;
                x1 = tmp;
                tmp = y0;
                y0 = y1;
                y1 = tmp;
            }
            int xInc = 1;
            if(m < 0) xInc = -1;
            int x = x0;
            int y = y0;
            while(y < y1) {
                bi.setRGB(x, y, 0xFFFFFF);
                if(E < 0) {
                    E = E + incOne;
                } else {
                    x = x + xInc;
                    E = E + incTwo;
                }
                y++;
            }
        }
        return bi;
    }
}

/*class ImageCanvas extends Canvas {

    public void paint(Graphics g, BufferedImage i) {
        g.drawImage(i, 0, 0, Color.BLACK, null);
    }
}*/
