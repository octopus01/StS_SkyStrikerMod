package skyStriker.cards.SkyStriker;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import skyStriker.DefaultMod;
import skyStriker.actions.TagFromDiscardPileToHandAction;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.KagariStance;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Kagari extends CustomCard {
    public static final String ID = DefaultMod.makeID(Kagari.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Kagari.png");

    public Kagari() {
        super(ID, cardStrings.NAME, IMG, 2, cardStrings.DESCRIPTION, CardType.SKILL,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.BASIC, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SkyStriker);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TagFromDiscardPileToHandAction(1,SkyStriker));
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

