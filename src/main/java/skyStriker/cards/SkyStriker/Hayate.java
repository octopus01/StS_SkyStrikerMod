package skyStriker.cards.SkyStriker;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import skyStriker.powers.SkyStriker.HayatePower;
import skyStriker.stances.HayateStance;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Hayate extends CustomCard {
    public static final String ID = DefaultMod.makeID(Hayate.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Hayate.png");

    public Hayate() {
        super(ID, cardStrings.NAME, IMG, 2, cardStrings.DESCRIPTION, CardType.SKILL,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.BASIC, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SkyStriker);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,
                new HayatePower(AbstractDungeon.player, 0), 0));
        this.addToBot(new ChangeStanceAction(new HayateStance()));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

    public AbstractCard makeCopy() {
        return new Hayate();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}
