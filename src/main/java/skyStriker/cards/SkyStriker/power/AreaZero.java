package skyStriker.cards.SkyStriker.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.DefaultMod;
import skyStriker.actions.CheckDeckTopAndAddAction;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.powers.SkyStriker.AreaZeroPower;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SpellCard;

public class  AreaZero extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(AreaZero.class.getSimpleName());
    public static final String IMG = makeCardPath("AreaZero.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 1;




    // /STAT DECLARATION/


    public AreaZero() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SkyStriker);
        this.tags.add(SpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AreaZeroPower(p, 0), 1));
        if(upgraded) this.addToBot(new CheckDeckTopAndAddAction(3,1,SkyStriker));

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
    @Override
    public void downgrade() {
        if (upgraded) {
         super.downgrade();
        }
    }

}


