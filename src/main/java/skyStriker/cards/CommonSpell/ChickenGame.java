package skyStriker.cards.CommonSpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.DefaultMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.powers.SkyStriker.ChickenGamePower;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.*;

public class  ChickenGame extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChickenGame.class.getSimpleName());
    public static final String IMG = makeCardPath("ChickenGame.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 0;




    // /STAT DECLARATION/


    public ChickenGame() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SpellCard);
        this.tags.add(FieldSpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ChickenGamePower(p, 0), 0));
        if(upgraded) this.addToBot(new GainEnergyAction(1));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
//            upgradeName();
            this.upgraded=true;
//            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


