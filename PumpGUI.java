package insulinpump;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.Math;
/**
 *
 * 
 */
public class PumpGUI extends JFrame implements ActionListener{
    
    InsulinPumpSettings pumpSettings = new InsulinPumpSettings();
    
    double carbs, BG, carbCorrect, BGCorrect, totalBolus;
    double target = 120;
    
    //Start Panel
    JPanel startPanel = new JPanel();
    JTextArea startWelcome = new JTextArea("Welcome to your insulin pump! \n" + "Select whether you're a new user or have used this pump before.");
    JButton startNewUser = new JButton ("New User");
    JButton startPreExist = new JButton ("User Strub");
    JButton startExit = new JButton ("Exit");
    
    //New User Panel
    JPanel newUserPanel = new JPanel();
    JLabel newUserICLabel = new JLabel("Insulin/Carb Ratio:");
    JTextField newUserICText = new JTextField(7);
    JLabel newUserISFLabel = new JLabel("Insulin Sensitivity Factor:");
    JTextField newUserISFText = new JTextField(7);
    JButton newUserEnter = new JButton("Enter");
    JButton newUserBack = new JButton("Back");
    JTextArea newUserTip = new JTextArea("Tip:\n" + "The insulin-to-carb ratio is 1 unit of insulin for each amount of carbohydrate.\n" 
                                            + "Your insulin sensitivity factor is the mg/dl drop in blood glucose due to 1 unit of insulin.");
    
    //pre existing
    JPanel preExistPanel = new JPanel();
    JLabel preExisLabel = new JLabel ("Hello " + pumpSettings.getPreExistName() + "!");
    JLabel preExistIC = new JLabel("Your insulin/carb ratio is " + pumpSettings.getInsulinToCarb());
    JLabel preExistISF = new JLabel ("Your insulin sensitivity factor is " + pumpSettings.getInsulinSensitivity());
    JButton preExistContinue = new JButton("Continue");
    JButton preExistBack = new JButton("Back");
    
    //Main Menu
    JPanel mainMenuPanel = new JPanel();
    JLabel mainMenuLabel = new JLabel("Main Menu:");
    JButton mainMenuBolus = new JButton("Bolus Wizard");
    JButton mainMenuSettings = new JButton("Settings");
    JButton mainMenuBack = new JButton("Back");
    JButton mainMenuExit = new JButton("Exit");
    
    //Bolus Wizard Panel
    JPanel bolusPanel = new JPanel();
    JLabel bolusBGLabel = new JLabel("BG:");
    JTextField bolusBGText = new JTextField(10);
    JLabel bolusCarbsLabel = new JLabel("Carbs:");
    JTextField bolusCarbsText = new JTextField(10);
    JButton bolusNext = new JButton("Next");
    JLabel bolusTotal = new JLabel("Bolus Suggestion:");
    JLabel bolusBGWarning = new JLabel (" ");
    JButton bolusConfirm = new JButton ("Confirm");
    JButton bolusCancel = new JButton ("Cancel");
    
    //Confirm Panel
    JPanel confirmPanel = new JPanel();
    JLabel confirmAreYouSure = new JLabel ("");
    JButton confirmYes = new JButton ("Deliver");
    JButton confirmBack = new JButton ("Back");
    JLabel confirmLabel = new JLabel("");
    JButton confirmButton = new JButton("Done");
    
    //Settings
    JPanel settingsPanel = new JPanel();
    JLabel settingsIC = new JLabel("Insulin/Carb Ratio: ");
    JLabel settingsISF = new JLabel ("Insulin Sensitivity Factor: ");
    JButton settingsChange = new JButton ("Change");
    JButton settingsBack = new JButton ("Back");
    
    //Change Settings Panel
    JPanel changePanel = new JPanel(); 
    JLabel changeICLabel = new JLabel("Insulin/Carb Ratio");
    JTextField changeICText = new JTextField(10);
    JLabel changeISFLabel = new JLabel("Insulin Sensitivty Factor");
    JTextField changeISFText = new JTextField(10);
    JButton changeSubmit = new JButton("Enter");
    JButton changeCancel = new JButton("Cancel");
    
    public PumpGUI(){
        
    //Frame Contents
    this.setTitle("Insulin Pump");
    this.setVisible(true);
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //Start Panel
    this.getContentPane().add(startPanel);
    startPanel.setSize(500,500);
    startPanel.setBackground(Color.LIGHT_GRAY);
    startPanel.add(startWelcome);
    
    startPanel.add(startNewUser);
    startNewUser.addActionListener(this);
    
    startPanel.add(startPreExist);
    startPreExist.addActionListener(this);
    
    startPanel.add(startExit);
    startExit.addActionListener(this);
    
    //New User Panel
    this.getContentPane().add(newUserPanel);
    newUserPanel.setSize(500,500);
    newUserPanel.setBackground(Color.LIGHT_GRAY);
    
    newUserPanel.add(newUserICLabel);
    newUserPanel.add(newUserICText);
    
    newUserPanel.add(newUserISFLabel);
    newUserPanel.add(newUserISFText);
    
    newUserPanel.add(newUserEnter);
    newUserEnter.addActionListener(this);
    
    newUserPanel.add(newUserBack);
    newUserBack.addActionListener(this);
    
    newUserPanel.add(newUserTip);
    
    //Pre Exist User
    this.getContentPane().add(preExistPanel);
    preExistPanel.setSize(500,500);
    preExisLabel.setBackground(Color.LIGHT_GRAY);
    
    preExistPanel.add(preExisLabel);
    preExistPanel.add(preExistIC);
    preExistPanel.add(preExistISF);
    preExistPanel.add(preExistContinue);
    preExistContinue.addActionListener(this);
    preExistPanel.add(preExistBack);
    preExistBack.addActionListener(this);
    
    //Main Menu
    this.getContentPane().add(mainMenuPanel);
    mainMenuPanel.setSize(500,500);
    mainMenuPanel.setBackground(Color.LIGHT_GRAY);
    
    mainMenuPanel.add(mainMenuLabel);
    mainMenuPanel.add(mainMenuBolus);
    mainMenuBolus.addActionListener(this);
    mainMenuPanel.add(mainMenuSettings);
    mainMenuSettings.addActionListener(this);
    mainMenuPanel.add(mainMenuBack);
    mainMenuBack.addActionListener(this);
    mainMenuPanel.add(mainMenuExit);
    mainMenuExit.addActionListener(this);
   
    //Bolus Wizard Panel
    this.getContentPane().add(bolusPanel);
    bolusPanel.setSize(500,500);
    bolusPanel.setBackground(Color.LIGHT_GRAY);
    
    bolusPanel.add(bolusBGLabel);
    bolusPanel.add(bolusBGText);
    bolusPanel.add(bolusCarbsLabel);
    bolusPanel.add(bolusCarbsText);
    bolusPanel.add(bolusNext);
    bolusNext.addActionListener(this);
    bolusPanel.add(bolusTotal);
    bolusPanel.add(bolusBGWarning);
    bolusBGWarning.setVisible(false);
    bolusPanel.add(bolusConfirm);
    bolusConfirm.addActionListener(this);
    bolusPanel.add(bolusCancel);
    bolusCancel.addActionListener(this);
    
    //Confirm Panel
    this.getContentPane().add(confirmPanel);
    confirmPanel.setSize(500,500);
    confirmPanel.setBackground(Color.LIGHT_GRAY);
    
    confirmPanel.add(confirmAreYouSure);
    confirmPanel.add(confirmYes);
    confirmYes.addActionListener(this);
   
    confirmPanel.add(confirmBack);
    confirmBack.addActionListener(this);
    
    confirmPanel.add(confirmLabel);
    confirmLabel.setVisible(false);
    
    confirmPanel.add(confirmButton);
    confirmButton.addActionListener(this);
    
    //Settings Panel
    this.getContentPane().add(settingsPanel);
    settingsPanel.setSize(500,500);
    settingsPanel.setBackground(Color.LIGHT_GRAY);
    
    settingsPanel.add(settingsIC);
    settingsPanel.add(settingsISF);
    settingsPanel.add(settingsChange);
    settingsChange.addActionListener(this);
    settingsPanel.add(settingsBack);
    settingsBack.addActionListener(this);
    
    //Change Settings Panel
    this.getContentPane().add(changePanel);
    changePanel.setSize(500,500);
    changePanel.setBackground(Color.LIGHT_GRAY); 
    changePanel.add(changeICLabel);
    changePanel.add(changeICText);
    changePanel.add(changeISFLabel);
    changePanel.add(changeISFText);
    changePanel.add(changeSubmit);
    changeSubmit.addActionListener(this);
    changePanel.add(changeCancel);
    changeCancel.addActionListener(this);
    
    
    //setVisible
    startPanel.setVisible(true);
    newUserPanel.setVisible(false);
    preExistPanel.setVisible(false);
    mainMenuPanel.setVisible(false);
    bolusPanel.setVisible(false);
    confirmPanel.setVisible(false);
    settingsPanel.setVisible(false);
    changePanel.setVisible(false);
        
    }
    
    
    public void actionPerformed (ActionEvent e){
        //Start Panel actions
        if (e.getSource().equals(startNewUser)){
            startPanel.setVisible(false);
            newUserPanel.setVisible(true);
        }
        
        if (e.getSource().equals(startPreExist)){
            pumpSettings.setPreExistName("Brandon Strub");
            pumpSettings.setInsulinToCarb(9.00);
            pumpSettings.setInsulinSensitivity(30.00);
            
            startPanel.setVisible(false);
            preExistPanel.setVisible(true);
            
            preExisLabel.setText("Hello " + pumpSettings.getPreExistName() + "!");
            preExistIC.setText("Your insulin/carb ratio is " + pumpSettings.getInsulinToCarb() + ".");
            preExistISF.setText("Your insulin sensitivity factor is " + pumpSettings.getInsulinSensitivity() + ".");
            
            settingsIC.setText("Insulin/Carb Ratio: " + pumpSettings.getInsulinToCarb());
            settingsISF.setText("Insulin Sensitivity Factor: " + pumpSettings.getInsulinSensitivity());
        }
        
        if (e.getSource().equals(startExit)){
            System.exit(0);
        }
        
        //new user actions
        if (e.getSource().equals(newUserEnter)){
            double itc = Double.parseDouble(newUserICText.getText());
            double isf = Double.parseDouble(newUserISFText.getText());
            pumpSettings.setInsulinToCarb(itc);
            pumpSettings.setInsulinSensitivity(isf);
            //System.out.println (pumpSettings.getInsulinToCarb());
            //System.out.println (pumpSettings.getInsulinSensitivity());
            settingsIC.setText("Insulin/Carb Ratio: " + pumpSettings.getInsulinToCarb());
            settingsISF.setText("Insulin Sensitivity Factor: " + pumpSettings.getInsulinSensitivity());
            newUserPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
        if (e.getSource().equals(newUserBack)){
            newUserPanel.setVisible(false);
            startPanel.setVisible(true);
        }
        
        //pre existing user acions
        if (e.getSource().equals(preExistContinue)){
            preExistPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
        if (e.getSource().equals(preExistBack)){
            preExistPanel.setVisible(false);
            startPanel.setVisible(true);
        }
        
        //Main Menu
        if (e.getSource().equals(mainMenuBolus)){
            mainMenuPanel.setVisible(false);
            bolusPanel.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuSettings)){
            mainMenuPanel.setVisible(false);
            settingsPanel.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuBack)){
            mainMenuPanel.setVisible(false);
            startPanel.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuExit)){
            System.exit(0);
        }
        
        //Bolus Wizard
        if (e.getSource().equals(bolusNext)){
            BG = Double.parseDouble(bolusBGText.getText());
            carbs = Double.parseDouble(bolusCarbsText.getText());
            carbCorrect = carbs / pumpSettings.getInsulinToCarb();
            BGCorrect = (BG - target) / pumpSettings.getInsulinSensitivity();
            totalBolus = Math.floor((carbCorrect + BGCorrect) * 100) / 100 ;
            bolusTotal.setText("Bolus Suggestion: " + totalBolus + "U");
            
            if (BG >= 250){
                bolusBGWarning.setVisible(true);
                bolusBGWarning.setText("Warning: High BG Alert!");
            }
            if (BG <= 80){
                bolusBGWarning.setVisible(true);
                bolusBGWarning.setText("Warning: Low BG Alert!");
            }
            if (BG > 80 && BG < 250){
                bolusBGWarning.setVisible(false);
            }
            confirmAreYouSure.setText(totalBolus + "U. Deliver?");
           
            
        }
        if (e.getSource().equals(confirmYes))
        {
                confirmAreYouSure.setVisible(false);
                confirmYes.setVisible(false);
                confirmBack.setVisible(false);
                confirmLabel.setVisible(true);
                confirmLabel.setText(totalBolus + " Units Delivered !");
        }
        
        if (e.getSource().equals(confirmBack)){
            confirmPanel.setVisible(false);
            bolusPanel.setVisible(true);
        }
        
        if (e.getSource().equals(bolusConfirm)){
            bolusPanel.setVisible(false);
            confirmPanel.setVisible(true);
        }
        
        if (e.getSource().equals(bolusCancel)){
            bolusPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
        //confirm action
        if (e.getSource().equals(confirmButton)){
            confirmPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
        //Settings
        if (e.getSource().equals(settingsChange)){
            settingsPanel.setVisible(false);
            changePanel.setVisible(true);
            
            
        }
        
        if (e.getSource().equals(settingsBack)){
            settingsPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
        
        //Change Settings - 
        if(e.getSource().equals(changeSubmit)){
            double newIC = Double.parseDouble(changeICText.getText());
            double newISF = Double.parseDouble(changeISFText.getText());
            pumpSettings.setInsulinToCarb(newIC);
            pumpSettings.setInsulinSensitivity(newISF);
            changePanel.setVisible(false);
            settingsPanel.setVisible(true);
            settingsIC.setText("Insulin/Carb Ratio: " + pumpSettings.getInsulinToCarb());
            settingsISF.setText("Insulin Sensitivity Factor: " + pumpSettings.getInsulinSensitivity());
            
        }
        if(e.getSource().equals(changeCancel)){
            changePanel.setVisible(false);
            settingsPanel.setVisible(true);
        }
    }
}
