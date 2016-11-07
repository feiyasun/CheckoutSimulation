import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread.*; 
import javax.swing.border.Border;

public class test {
    static JFrame frame;

    public final static int checkOuts = 8;
    public final static int maxQueueSize = 6;
    private JPanel queuePanel[] = new JPanel[checkOuts];
    private CustomerLocation queue[][] = new CustomerLocation[checkOuts][maxQueueSize];
    private JPanel checkOutsPanel;
    private JPanel infoPanel;

    public test() 
    {
        checkOutsPanel = new JPanel();
        checkOutsPanel.setLayout(new GridLayout(checkOuts+1,1));
        infoPanel = new JPanel();
        checkOutsPanel.add(infoPanel);

        // Create panel for each queue
        for (int count = 0; count < checkOuts; count++)
        {
           queuePanel[count] = new JPanel();
           queuePanel[count].setLayout(new GridLayout(1,maxQueueSize+1));
           queuePanel[count].add(new JLabel("Check Out " + count));

           for (int i = 0; i < maxQueueSize; i++) // now add queue locations for each queue
           {
             queue[count][i] = new CustomerLocation();
             queuePanel[count].add(queue[count][i]);
           }

           checkOutsPanel.add(queuePanel[count]);
        }        
    }

    public static void main(String s[]) {
	test mainObj = new test();
        mainObj.createFrame();
    }

    void createFrame()
    {
	frame = new JFrame("test");
	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {System.exit(0);}
	});
	Container mainPane = frame.getContentPane();
	mainPane.add(checkOutsPanel);
        frame.setSize(800, 600);


        /* queue 1  pos 1 */
        queue[1][1].setNumberofGoods(42);
        queue[1][0].setNumberofGoods(142); 
        queue[0][0].setNumberofGoods(44); 


	frame.setVisible(true);
    }

}

class CustomerLocation extends JPanel
{
   final private static Border blackline = BorderFactory.createLineBorder(Color.black);
   final private static ImageIcon trollyGIF = new ImageIcon("trolly.gif");
   final private JLabel cust = new JLabel("",trollyGIF,SwingConstants.CENTER);
   final private JLabel noCust = new JLabel(" Empty");

   private int numberofGoods = 0;

   public CustomerLocation()
   {
      setBackground(Color.black);
      setLayout(new BorderLayout());
      JLabel cust = new JLabel("Shopper",trollyGIF,SwingConstants.CENTER);
      setBorder(blackline);
      setNumberofGoods(0);
   }

   public void setNumberofGoods(int inGoods)
   {
      numberofGoods = inGoods;
      removeAll(); // Clear panel
      if (numberofGoods > 0)
      {
         add(cust);
         JLabel products = new JLabel("#"+numberofGoods);
         add(products,BorderLayout.EAST);
      }
      else
      {
         add(noCust);
      }
   }

   public int getNumberofGoods()
   {
     return numberofGoods;
   }
}

