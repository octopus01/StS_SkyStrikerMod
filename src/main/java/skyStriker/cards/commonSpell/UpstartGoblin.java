package skyStriker.cards.commonSpell;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.SkyStrikerMod.makeCardPath;

public class UpstartGoblin extends AbstractDynamicCard {



    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(UpstartGoblin.class.getSimpleName());
    public static final String IMG = makeCardPath("UpstartGoblin.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.SKY_STRIKER_DEFAULT_COLOR;


    private static final int COST =0;

    // /STAT DECLARATION/


    public UpstartGoblin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SkyStrikerCardTags.SSSpellCard);
        if(upgraded) this.baseMagicNumber=1;
        else this.baseMagicNumber=3;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p,1));
        this.addToBot(new HealAction(m,p,baseMagicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
//            upgradeName();
            this.upgraded=true;
            this.baseMagicNumber=1;
            initializeDescription();
        }
    }
}