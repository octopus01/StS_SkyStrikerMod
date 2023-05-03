package skyStriker.cards.SkyStriker.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skyStriker.SkyStrikerMod;
import skyStriker.actions.TagFromExtraDeckToHandAction;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.SkyStrikerMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SkyStrikerAce;

public class Linkage extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(Linkage.class.getSimpleName());
    public static final String IMG = makeCardPath("Linkage.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.SKY_STRIKER_DEFAULT_COLOR;

    private static final int COST = 1;




    // /STAT DECLARATION/


    public Linkage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerCardTags.SSSpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p,p,1,false));
        this.addToBot(new TagFromExtraDeckToHandAction(1, SkyStrikerAce, 0));
        if(upgraded) {
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,3)));
        }

    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            TheSkyStriker p1 = (TheSkyStriker) p;
            if(p.hand.group.size()==0) return false;
            boolean hasSkyStriker = false;
            for (AbstractCard c : p1.extraDeck.group) {
                if (c.hasTag(SkyStrikerAce)) {
                    hasSkyStriker = true;
                }
            }
            if (!hasSkyStriker) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                canUse = false;
            }

            return canUse;
        }
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
    @Override
    public void downgrade() {
        if (upgraded) {
            super.downgrade();
        }
    }
}
