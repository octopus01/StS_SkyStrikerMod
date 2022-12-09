package skyStriker.cards.SkyStriker.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import skyStriker.DefaultMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class HerculesBase extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HerculesBase.class.getSimpleName());
    public static final String IMG = makeCardPath("HerculesBase.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 1;



    // /STAT DECLARATION/


    public HerculesBase() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DoubleTapPower(p, 1), 1));
        if(upgraded) this.addToBot(new DrawCardAction(p, 1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
//            upgradeName();
            this.upgraded=true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
//    @Override
//    public void downgrade() {
//        if (upgraded) {
////            upgradeName();
//            this.upgraded=false;
//            this.rawDescription = cardStrings.DESCRIPTION;
//            initializeDescription();
//        }
//    }
}


