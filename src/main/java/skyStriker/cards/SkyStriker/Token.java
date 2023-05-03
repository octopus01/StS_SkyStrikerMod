package skyStriker.cards.SkyStriker;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.actions.RmFromPlayAction;
import skyStriker.actions.TagFromExtraDeckToHandAction;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.SkyStrikerMod.makeCardPath;

public class Token extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(Token.class.getSimpleName());
    public static final String IMG = makeCardPath("Token.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.SKY_STRIKER_DEFAULT_COLOR;

    private static final int COST = 0;



    // /STAT DECLARATION/


    public Token() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(SkyStrikerCardTags.SkyStriker);
        this.exhaust = true;
        this.isEthereal = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            this.addToBot(new TagFromExtraDeckToHandAction(1,SkyStrikerCardTags.SkyStrikerAce,1));
            this.addToBot(new GainBlockAction(p,4));
        }
        else
            this.addToBot(new TagFromExtraDeckToHandAction(1,SkyStrikerCardTags.SkyStrikerAce,1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isEthereal = false;
        }
    }
}
