package skyStriker.cards.commonSpell;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.SkyStriker.attack.SSAbstractAttackCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.SkyStrikerMod.makeCardPath;
public class Raigeki extends SSAbstractAttackCard {

    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(Raigeki.class.getSimpleName());

    public static final String IMG = makeCardPath("Raigeki.png");



    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSkyStriker.Enums.SKY_STRIKER_DEFAULT_COLOR;

    private static final int COST = 3;
    private static final int DAMAGE = 20;


    public Raigeki() {
        super(ID,  IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.tags.add(SkyStrikerCardTags.SSSpellCard);
        this.isMultiDamage=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        TheSkyStriker p1 = (TheSkyStriker) p;
        p1.canAttack=false;
        this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
        this.addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgraded = true;
            baseDamage=30;
            this.upgradedDamage = true;
            initializeDescription();
        }
    }
}
