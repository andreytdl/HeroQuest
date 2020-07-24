package br.unicamp.aluno.models.Item;

import br.unicamp.aluno.models.Character.Character;
import br.unicamp.aluno.models.Dice;

public class SimpleHeal extends Spell {
    private Dice dice;

    public SimpleHeal() {
        super(false); // definir se é destruido após o uso
        this.dice = new Dice();
    }

    @Override
    public void cast(Character character) {
        character.addLifePoints(dice.normalDice());
    }
    
    @Override
    public String toString() {
    	return "Simple Heal";
    }
}
