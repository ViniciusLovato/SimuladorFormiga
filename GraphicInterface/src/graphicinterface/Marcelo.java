package graphicinterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Marcelo {

	public static void main(String[] args) {

		String fileName = "swarm.txt";
		Integer antId = 3;
		Integer nAnts = 1000;
		Integer nMovements = 200;

		/*
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
		*/
		
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
				System.out.println(Integer.parseInt(quebra[i]) + " ");
			}
			// fecha o arquivo
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		JFrame frame = new JFrame("Teste");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Teste t = new Teste();
		frame.add(t);
		frame.setLocationRelativeTo(null); // coloca a janela no centro da tela
		frame.setSize(800, 600);
		frame.setVisible(true);

		int x = 0;
		int y = 0;
		
		for (int i = 0; i < movements.size(); i++) {

			t.repaint();

			// Delay
			try {
				Thread.sleep(100);                 //100 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			switch (movements.get(i)) {
				case 0:	// direita
					if(x < 15) {
						t.x += t.largura;
						x++;						
					}
					break;
					
				case 1:	// esquerda
					if(x > 0) {
						t.x -= t.largura;
						x--;						
					}
					break;
					
				case 2:	// sobe
					if(y > 0) {
						t.y -= t.altura;
						y--;						
					}
					break;
					
				case 3:	// desce
					if(x < 15) {
						t.y += t.altura;
						y++;						
					}
					break;
					
				default:
					break;
			}
		}
	}
}
