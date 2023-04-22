package skyStriker.cards.SkyStriker.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.actions.tagFromDeckToHandAction;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import java.util.Iterator;

import static skyStriker.SkyStrikerMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Engage extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(Engage.class.getSimpleName());
    public static final String IMG = makeCardPath("Engage.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 0;




    // /STAT DECLARATION/


    public Engage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new tagFromDeckToHandAction(1,SkyStriker,0,cardID,false));;
        if(upgraded) this.addToBot(new DrawCardAction(p, 1));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            boolean hasSkyStriker = false;
            Iterator var5 = p.drawPile.group.iterator();

            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                if (c.hasTag(SkyStriker)) {
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

