package br.unicamp.aluno.models.Exceptions;

//Exception que é lançada quando o jogador é pego em alguma armadilha
public class TrapsHurtMeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	//Mensagem lançada
	public TrapsHurtMeException() {
		super("Seu turno acaba por aqui, você foi pego por uma armadilha!");
	}
	
}
