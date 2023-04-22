package skyStriker.cards.SkyStriker.stance;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.powers.SkyStriker.ShizukuPower;
import skyStriker.stances.ShizukuStance;

import static skyStriker.SkyStrikerMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SkyStrikerAce;

public class Shizuku extends AbstractDynamicCard {
    public static final String ID = SkyStrikerMod.makeID(Shizuku.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Shizuku.png");

    public Shizuku() {
        super(ID, IMG, 2, CardType.POWER,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerAce);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));
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

//    public AbstractCard makeCopy() {
//        return new Shizuku();
//    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}

