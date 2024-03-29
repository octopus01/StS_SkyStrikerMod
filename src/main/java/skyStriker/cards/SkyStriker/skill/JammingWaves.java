package skyStriker.cards.SkyStriker.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;
import skyStriker.powers.SkyStriker.JammingWavesPower;

import static skyStriker.SkyStrikerMod.makeCardPath;

public class JammingWaves extends AbstractDynamicCard {



    // TEXT DECLARATION

    public static final String ID = SkyStrikerMod.makeID(JammingWaves.class.getSimpleName());
    public static final String IMG = makeCardPath("JammingWaves.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/
  
    public JammingWaves() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseBlock = BLOCK;
        baseDamage = 5;
        this.tags.add(CardTags.STARTER_DEFEND);
        this.tags.add(SkyStrikerCardTags.SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        if(upgraded)
        {
            this.addToBot(new ApplyPowerAction(p, p, new JammingWavesPower(p, 1),1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
//            upgradeName();
            this.upgraded=true;
            baseBlock=8;
            upgradedBlock=true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void downgrade() {
        super.downgrade();
        baseBlock=5;
        upgradedBlock=false;
    }
}
