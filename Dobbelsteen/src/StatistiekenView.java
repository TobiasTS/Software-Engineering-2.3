import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Door: Tobias Schlichter
 * Datum: 09-02-2016
 * 
 * View die de statistieken van de worpen laat zien.
 * Per mogelijkheid van ogen wordt weergegeven hoe vaak die is voorgekomen.
 * Ook is er een counter die telt hoe vaak er gerolt is.
 */
public class StatistiekenView extends JPanel implements ActionListener {
	
	DobbelsteenModel d;
	
	int aantalWorpen = 0;
	int aantalKeerNummer1 = 0;
	int aantalKeerNummer2 = 0;
	int aantalKeerNummer3 = 0;
	int aantalKeerNummer4 = 0;
	int aantalKeerNummer5 = 0;
	int aantalKeerNummer6 = 0;
	
	JLabel worpenLabel = new JLabel();
	JLabel nummer1Label = new JLabel();
	JLabel nummer2Label = new JLabel();
	JLabel nummer3Label = new JLabel();
	JLabel nummer4Label = new JLabel();
	JLabel nummer5Label = new JLabel();
	JLabel nummer6Label = new JLabel();
	
	/*
	 * Constructor van de klasse.
	 * Maakt een GridLayout aan met 7 rijen en 2 kolommen.
	 * Voegt daarna per statistiek JLabel toe die nog niet aan gemaakt is en daarna een JLabel toe die al wel is aangemaakt.
	 * Die nog niet aangemaakt zijn zijn de beschrijvingen van het statistiek.
	 * De al wel aangemaakte JLabels zijn de waardes en worden tijdens het runnen aangepast.
	 */
	public StatistiekenView() {
		setLayout(new GridLayout(7, 2));
		
		add(worpenLabel);
		add(new JLabel("worpen"));
		add(new JLabel("1:"));
		add(nummer1Label);
		add(new JLabel("2:"));
		add(nummer2Label);
		add(new JLabel("3:"));
		add(nummer3Label);
		add(new JLabel("4:"));
		add(nummer4Label);
		add(new JLabel("5:"));
		add(nummer5Label);
		add(new JLabel("6:"));
		add(nummer6Label);
	}

	/*
	 * Deze methode wordt aangeroepen als er een actie plaats vind in de applicatie.
	 * Hier wordt de waarde van de dobbelsteen doorgegeven naar de methode tellersVerhogen die daarmee wordt aangeroepen.
	 * Daarna wordt de methode zetNummers aangeroepen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		d = (DobbelsteenModel) e.getSource();
		verhoogTellers(d.getWaarde());
		zetNummers();
	}
	
	/*
	 * Deze methode update zet de waardes van de JLabels naar de geupdate waardes.
	 */
	private void zetNummers() {
		worpenLabel.setText("" + aantalWorpen);

		nummer1Label.setText(aantalKeerNummer1 + " keer");
		nummer2Label.setText(aantalKeerNummer2 + " keer");
		nummer3Label.setText(aantalKeerNummer3 + " keer");
		nummer4Label.setText(aantalKeerNummer4 + " keer");
		nummer5Label.setText(aantalKeerNummer5 + " keer");
		nummer6Label.setText(aantalKeerNummer6 + " keer");
	}
	
	/*
	 * Deze methode verhoogd de teller die overeenkomt met de waarde van die hij als variabele mee krijgt.
	 * Ook verhoogd deze methode de teller van het aantal worpen.
	 */
	private void verhoogTellers(int dobbelWaarde) {
		aantalWorpen++;
		
		switch(dobbelWaarde){
			case 1:
				aantalKeerNummer1++;
				break;
			case 2:
				aantalKeerNummer2++;
				break;
			case 3:
				aantalKeerNummer3++;
				break;
			case 4:
				aantalKeerNummer4++;
				break;
			case 5:
				aantalKeerNummer5++;
				break;
			case 6:
				aantalKeerNummer6++;
				break;	
		}
	}
}
