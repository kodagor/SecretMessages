import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;

public class SecretMessagesGUI extends JPanel {
	
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	
	public String encode(String message, int k) {
		
		String out = "";
		char key = (char) k;
		for (int x = 0; x < message.length(); x++) {
			char in = message.charAt(x);
			if (in >= 'A' && in <= 'Z') {
				in += key;
				if (in > 'Z' ) {
					in -= 26;
				}
				if (in < 'A') {
					in += 26;
				}
			}
			if (in >= 'a' && in <= 'z') {
				in += key;
				if (in > 'z' ) {
					in -= 26;
				}
				if (in < 'a') {
					in += 26;
				}
			}
			if (in >= '0' && in <= '9') {
				in += (k % 10);		// it returns only 0 ... 9 (ex. 29%10 = 9)
				if (in > '9' ) {
					in -= 10;
				}
				if (in < '0') {
					in += 10;
				}
			}
			out += in;
		}
		return out;
	}
	
	public void crack() {
		// get the message from txtIn field
		String message = txtIn.getText();
		
		// get the key from txtKey
		int key = Integer.parseInt(txtKey.getText());
		
		// encode that message with that key
		String output = encode(message, key);
		
		// show the message in txtOut
		txtOut.setText(output);
	}
	
	public SecretMessagesGUI() {
		setBackground(new Color(152, 251, 152));
		setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setFont(new Font("Lato", Font.PLAIN, 18));
		txtIn.setBounds(12, 13, 426, 110);
		add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setFont(new Font("Lato", Font.PLAIN, 18));
		txtOut.setBounds(12, 197, 426, 110);
		add(txtOut);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(171, 144, 47, 28);
		add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKey.setBounds(230, 144, 39, 28);
		add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnEncodedecode = new JButton("Encode/Decode");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crack();
				slider.setValue(Integer.parseInt(txtKey.getText()));
			}
		});
		btnEncodedecode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEncodedecode.setBounds(281, 147, 157, 25);
		add(btnEncodedecode);
		setPreferredSize(new Dimension(450, 320));
		
		slider = new JSlider();
		slider.setBackground(new Color(152, 251, 152));
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// change the key txt field to match the slider
				txtKey.setText("" + slider.getValue());
				
				crack();
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setValue(0);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(12, 138, 168, 46);
		add(slider);
	}

	public static void main(String[] args) {
		// set up a window JFrame for the app
		JFrame frame = new JFrame("Secret Messages App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add the encoder panel to the frame
		frame.getContentPane().add(new SecretMessagesGUI());
		
		// prepare and show the frame
		frame.pack();
		frame.setVisible(true);

	}
}
