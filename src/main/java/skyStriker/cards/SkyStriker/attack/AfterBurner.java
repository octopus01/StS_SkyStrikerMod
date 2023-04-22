package skyStriker.cards.SkyStriker.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.HayateStance;

import java.util.Objects;

import static skyStriker.SkyStrikerMod.makeCardPath;
public class AfterBurner extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(AfterBurner.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("AfterBurner.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;

    private static final int BLOCK = 10;



    public AfterBurner() {
        super(ID, IMG, COST,  TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = 4;
        this.tags.add(CardTags.STARTER_STRIKE); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
        this.tags.add(CardTags.STRIKE);
        this.tags.add(SkyStrikerCardTags.SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(Objects.equals(p.stance.ID, HayateStance.STANCE_ID)) damageTypeForTurn= DamageInfo.DamageType.HP_LOSS;
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new WaitAction(0.1F));
        if(upgraded) this.addToBot(new GainBlockAction(p, p, this.block));
//        if (p != null && m != null) {
//            this.addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
//        }

    }
    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
//            upgradeName();
            baseDamage=10;
            this.upgraded=true;
            this.upgradedDamage = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeTitle();
            initializeDescription();
        }
    }
    @Override
    public void downgrade(){
        if(upgraded){
            super.downgrade();
            baseDamage=8;
            this.upgradedDamage = false;
        }
    }
}
