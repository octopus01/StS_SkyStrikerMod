package skyStriker.cards.SkyStriker;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


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
import com.megacrit.cardcrawl.powers.RetainCardPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import skyStriker.actions.TagFromDiscardPileToHandAction;
import skyStriker.powers.SkyStriker.ShizukuPower;
import skyStriker.stances.ShizukuStance;

import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Shizuku extends AbstractCard {
    public static final String ID = "Shizuku";
    private static final CardStrings cardStrings;

    public Shizuku() {
        super(ID, cardStrings.NAME, "", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.BASIC, CardTarget.SELF);
        this.baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));
//        this.addToBot(new TagFromDiscardPileToHandAction(1,SkyStriker));
        this.addToBot(new ApplyPowerAction(p, p, new ShizukuPower(p, 1), 1));
        this.addToBot(new ChangeStanceAction(new ShizukuStance()));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

    public AbstractCard makeCopy() {
        return new Shizuku();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Vigilance");
    }
}

