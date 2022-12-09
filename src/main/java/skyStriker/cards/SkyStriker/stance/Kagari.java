package skyStriker.cards.SkyStriker.stance;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.DefaultMod;
import skyStriker.actions.TagFromDiscardPileToHandAction;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.KagariStance;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Kagari extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Kagari.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Kagari.png");

    public Kagari() {
        super(ID, IMG, 2,  CardType.POWER,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SkyStriker);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TagFromDiscardPileToHandAction(1,SkyStriker,0));
        this.addToBot(new ChangeStanceAction(new KagariStance()));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

    public AbstractCard makeCopy() {
        return new Kagari();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}

