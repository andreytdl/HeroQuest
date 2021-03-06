package br.unicamp.aluno.models.Character.Monster;

import java.util.Random;

import br.unicamp.aluno.models.Character.Character;
import br.unicamp.aluno.models.Item.LongSword;
import br.unicamp.aluno.models.Item.ShortSword;
import br.unicamp.aluno.models.Item.Weapon;

public class Skeleton extends Monster {
	private Weapon weapon;

	public Skeleton(int x, int y) {
		super(x, y, 2, 1, 3, 2); // definir pontos com zero
		weapon = randomWeapon();
	}

	private Weapon randomWeapon(){
		// Gerando uma arma aleatória
		Random random = new Random();

		// Gera um numero aleatório entre 0 e 1
		switch (random.nextInt(2)) {
			// caso 0 criaremos uma LongSword
			case 0:
				return new LongSword();

			// caso 1 criamos uma ShortSword
			case 1:
				return new ShortSword();

			default:
				return null;
		}
	}

	public void hit(Character character) {
		int attackBonus = weapon.getAttackBonus();
		addAttackDice(attackBonus);
		super.hit(character);
		removeAttackDice(attackBonus);

		if (weapon.isDestroyed())
			weapon = null;
	}

	@Override
	public boolean isOnSight(Character character) {
		return onSight(character, weapon);
	}

	@Override
	public String toString() {
		return "SK";
	}

}