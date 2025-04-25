import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.util.Scanner;
import uk.ac.leedsbeckett.oop.LBUGraphics;

public class TurtleGraphics extends LBUGraphics
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in); // for user input
        System.out.println("Type 'about': ");
        String input = scanner.nextLine();
        TurtleGraphics turtle = new TurtleGraphics();
        if (input.equals("about"))
            turtle.about();
    }

    public TurtleGraphics()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        reset(); // Turtle to center, down
        drawOn(); // Pen down for initial state
        setStroke(1); // sets a default value for the penstroke width
    }

    public void about(){
        super.about();
        System.out.print("Henry was here."); //"override" the about function and add in my own name ;)
    }


    public void processCommand(String command)
    {
        String[] parts = command.split(" "); //split the command into two so can get the instruction and the value if there is one
        String cmd = parts[0]; //gets the first part of the command stored in the first index of the string array

        switch (cmd)
        {
            case "about": about(); break;
            case "penup": drawOff(); break;
            case "pendown": drawOn(); break;
            case "left":
            case "right":
            case "move":
            case "reverse":
            case "square":
            case "penwidth":
                if (parts.length > 1) {
                    int param = Integer.parseInt(parts[1]); // turns the string value into an integer so can be used by the LBUgraphics functions
                    if (param <= 0) {
                        System.out.println("Invalid: " + cmd + " needs positive number");
                    } //catching errors
                    else
                        switch (cmd) {
                            case "left":
                                left(param);
                                break;
                            case "right":
                                right(param);
                                break;
                            case "move":
                                forward(param);
                                break;
                            case "reverse":
                                forward(-param); //turns the value to a negative to reverse
                                break;
                            case "square":
                                square(param); //calls my custom made square function
                                break;
                            case "penwidth":
                                setStroke(param);
                                break;
                        }
                }
                else {System.out.println("Invalid: " + cmd + " needs number");}
                break;
            case "black": setPenColour(Color.BLACK); break;
            case "green": setPenColour(Color.GREEN); break;
            case "red": setPenColour(Color.RED); break;
            case "white": setPenColour(Color.WHITE); break;
            case "reset": reset(); drawOn(); break;
            case "clear": clear(); break;
            default: System.out.println("Invalid command: " + command);
        }
    }

    private void square(int length){
        for(int i=0; i < 4; i++){
            forward(length);
            right();
        }
    }
}