import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel {
    private JFrame frame1;
    private ArrayList<JLabel> jLabels;
    private ArrayList<JPanel> panels;
    private ArrayList<JButton> jButtons;

   public MenuPanel(){
       this.frame1 = new JFrame();
       this.jLabels = new ArrayList<>();
       this.panels = new ArrayList<>();
       this.jButtons = new ArrayList<>();
   }

   public void showMain(String mainName, String labelName){
       JLabel jLabel1 = new JLabel();
       this.jLabels.add(jLabel1);
       Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
       this.frame1.setTitle(mainName);// set the title of frame
       this.frame1.setLayout(null);
       this.frame1.setSize(800,800);
       this.frame1.setVisible(true);
       this.frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit upon clicking X
       this.frame1.setResizable(false); //do not let frame resize
       this.frame1.add(this.jLabels.get(0));
       this.jLabels.get(0).setText(labelName);
       this.jLabels.get(0).setForeground(Color.BLUE);
       this.jLabels.get(0).setHorizontalAlignment(JLabel.CENTER);
       this.jLabels.get(0).setVerticalAlignment(JLabel.TOP);
       this.jLabels.get(0).setBorder(border);
   }

   public void createButtons(){

   }

   public void createPanel(String text, int x, int y, int w, int h){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.pink);
        jPanel.setBounds(x,y,w,h);
        JLabel jLabel = new JLabel();
        jLabel.setText(text);
        jLabel.setForeground(Color.BLACK);
        jPanel.add(jLabel);
       this.panels.add(jPanel);
       this.frame1.add(jPanel);
   }

   public void createPanels(ArrayList<String> texts, int rowQuantity, int colQuantity){
       int i, j,k = 0;
       int quantity = rowQuantity*colQuantity;
       int x=205, y=205, w=200, h=200;

           for (i=0; i<rowQuantity && k<quantity; i++) {
               for (j = 0; j < colQuantity; j++) {
                   this.createPanel(texts.get(k), x * j, y*i, w, h);
                   k++;
               }
           }
   }

   public void createButton(String bName, int x, int y){
       JButton jButton = new JButton();
       jButton.setBounds(x, y, 245,50);

       JLabel buttonName = new JLabel();
       buttonName.setText(bName);
       buttonName.setForeground(Color.BLACK);
       buttonName.setPreferredSize(new Dimension(50,50));
       jButton.add(buttonName);
       jButton.setBackground(Color.pink);
       this.frame1.add(jButton);
       this.jButtons.add(jButton);
   }

    public ArrayList<JButton> getjButtons() {
        return jButtons;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public JFrame getFrame1() {
        return frame1;
    }
}
