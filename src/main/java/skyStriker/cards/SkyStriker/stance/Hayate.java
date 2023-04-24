package skyStriker.cards.SkyStriker.stance;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.powers.SkyStriker.HayatePower;
import skyStriker.stances.HayateStance;

import static skyStriker.SkyStrikerMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SkyStrikerAce;

public class Hayate extends AbstractDynamicCard {
    public static final String ID = SkyStrikerMod.makeID(Hayate.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Hayate.png");

    public Hayate() {
        super(ID, IMG, 2,  CardType.POWER,  TheSkyStriker.Enums.COLOR_LINK, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerAce);
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

//    public AbstractCard makeCopy() {
//        return new Hayate();
//    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}

