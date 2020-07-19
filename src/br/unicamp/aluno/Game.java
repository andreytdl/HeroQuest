package br.unicamp.aluno;

import java.util.ArrayList;

import br.unicamp.aluno.models.Traceable;
import br.unicamp.aluno.models.Trap;
import br.unicamp.aluno.models.Exceptions.TrapsHurtMeException;
import br.unicamp.aluno.models.Exceptions.YouAreDeadException;
import br.unicamp.aluno.models.Hero.Hero;

public class Game {

	private String[][] map;

	private ArrayList<Traceable> traceablesOnMap;

	private int xMapSize;
	private int yMapSize;
	private Hero hero;

	// Construtor do Jogo
	public Game(Hero player, int xSize, int ySize) {

			//Criando o mapa do jogo
		
			// guardando o tamanho do mapa
			this.map = new String[xSize][ySize];
			this.xMapSize = xSize;
			this.yMapSize = ySize;
			this.hero = player;

			//Iniciando a variável de localizaveis no mapa
			this.traceablesOnMap = new ArrayList<Traceable>();

			// Setando os espaços vazios do mapa
			for (int i = 0; i < xSize; i++) {
				for (int j = 0; j < ySize; j++) {
					this.map[i][j] = "--";
				}
			}

			//Adicionando o Player no mapa
			this.map[hero.getPositionX()][hero.getPositionY()] = hero.toString();

		}

		// Adiciona localizaveis no nosso mapa
		public void addTreaceables(Traceable traceable) {
			//Obtendo a localização do localizavel
			int traceableX = traceable.getPositionX();
			int traceableY = traceable.getPositionY();

			// Adicionando o localizavel no mapa
			this.map[traceableX][traceableY] = traceable.toString();

			// Registrando o localizavel nos traceables no jogo atual
			this.addTraceablesOnGame(traceable);
			
		}

		// Registra os traceables que estão no mapa
		private void addTraceablesOnGame(Traceable traceable) {
			this.traceablesOnMap.add(traceable);
		}
		
		// Remove os localizaveis que estão no jogo (Sem uso por enquanto, mas usaremos para matar monstros, etc depois)
//		private void removeTraceablesOnGame(Traceable traceable) {
//			this.traceablesOnMap.remove(traceable);
//		}

		// verifica a possibilidade e caminha com o player para a posição
		// xNow e yNow dizem a posição atual do player
		// xRequested e yRequested dizem a posiçao solicitada pelo usuário
		public boolean canIWalk(int xRequested, int yRequested) {

			int xNow = hero.getPositionX();
			int yNow = hero.getPositionY();

			// caso tiver o espaço desejado caminharemos com o player
			if (this.map[xRequested][yRequested].equals("--")) {

				// Andando com o player
				this.map[xRequested][yRequested] = hero.toString();
				this.map[xNow][yNow] = "--";

				return true;
			}
			else {
				//Depois tratamos isso com exceptions, deixa assim por enquanto kk
				System.out.println("Não pode andar ai não, a posição ta ocupada!");
				return false;
			}
		}

		// Printa todo conteúdo do mapa
		public void printMap() {

			for (int i = 0; i < this.xMapSize; i++) {
				for (int j = 0; j < this.yMapSize; j++) {
					System.out.print(this.map[i][j]);
				}
				System.out.println("");
			}
		}
		
		//Verifica se o heroi pisou em alguma armadilha e retira vida dele
		private void amIOnSomeTrap() {
			for (Traceable traceable : traceablesOnMap) {
				//Caso a posição do herói seja a mesma que a da armadilha:
				if(hero.getPositionX() == traceable.getPositionX() && hero.getPositionY() == traceable.getPositionY() && traceable instanceof Trap) {
					//Cast
					Trap trap = (Trap)traceable;
					
					//Ativando a armadilha
					trap.active(hero);
					
					//Encerrando o turno do jogador com uma mensagem de erro
					throw new TrapsHurtMeException();
					
				}
			}
		}
		
		//Iniciando o jogo/turno
		public void start() {
			
			boolean exit = false;
			System.out.println("Game started!");
			
			//Ciclo do jogo
			while(!exit) {
				
				//Acontecimentos do jogo
				try {
				
					
				}
				
				//Tratamento de excessões que possam surgir
				catch(TrapsHurtMeException e) {
					System.out.println(e.getMessage());
				}
				catch(YouAreDeadException e) {
					System.out.println(e.getMessage());
					exit = true;
				}
			}
			
			System.out.println("Game termined. Bye!");
			
		}

	}