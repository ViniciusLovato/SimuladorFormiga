/*
package graphicinterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GraphicInterface extends JFrame {

	private void initUI() {
		setTitle("GraphicInterface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new Ant());

		setSize(800, 600);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		String fileName = null;
		Integer antId = -1;
		Integer nAnts = 1000;
		Integer nMovements = 200;

		if (args.length == 0) {
			System.out.println("Entre com o nome do arquivo");
			fileName = System.console().readLine();

			System.out.println("Entre com o ID da formiga");
			antId = Integer.parseInt(System.console().readLine());
		} else if (args.length == 2) {
			fileName = args[0];
			antId = Integer.parseInt(args[1]);
		} else {
			System.out.println("ERRO: Numero de Argumentos Invalido");
			System.out.println("USO: programa [arq] [arq]");
			System.exit(1);
		}

		if (fileName == null || antId == -1 || antId > nAnts) {
			System.out.println("fileName == NULL || antId == -1");
			System.exit(1);
		}

		BufferedReader reader = null;
		ArrayList<Integer> movements = new ArrayList();
		try {
			reader = new BufferedReader(new FileReader(fileName));

			int i;
			String line;

			// pula para a formiga de ID passado na linha de comando
			for (i = 0; i < antId; i++) {
				reader.readLine();
			}

			// na linha da formiga que interessa
			line = reader.readLine();
			String[] quebra = line.split(" ");

			// adiciona os movimentos em um ArrayList
			for (i = 0; i < nMovements; i++) {
				movements.add(Integer.parseInt(quebra[i]));
			}
			// fecha o arquivo
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Ant ant = new Ant();
				ant.setVisible(true);
			}
		});
		
		for (int i = 0; i < movements.size(); i++) {
			ant.moveSquare(movements.get(i));
		}
	}
}
*/