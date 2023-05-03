package skyStriker.cards.commonSpell;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;
import static skyStriker.SkyStrikerMod.makeCardPath;
import com.megacrit.cardcrawl.localization.CardStrings;
public class PotOfGreed extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(PotOfGreed.class.getSimpleName());
    public static final String IMG = makeCardPath("PotOfGreed.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.SKY_STRIKER_DEFAULT_COLOR;

    private static final int COST = 0;
//    private static final int BLOCK = 5;
//    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public PotOfGreed() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(SkyStrikerCardTags.SSSpellCard);
    //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new VFXAction(new AdrenalineEffect(), 0.15F));

        this.addToBot(new DrawCardAction(p, 2));
    }
//    public AbstractCard makeCopy() {
//        return new PotOfGreed();
//    }
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }
}



