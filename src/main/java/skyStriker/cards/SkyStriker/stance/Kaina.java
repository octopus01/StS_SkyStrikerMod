package skyStriker.cards.SkyStriker.stance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.KainaStance;

import static skyStriker.SkyStrikerMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SkyStrikerAce;

public class Kaina extends AbstractDynamicCard {
    public static final String ID =  SkyStrikerMod.makeID(Kaina.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Kaina.png");

    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }
    public Kaina() {
        super(ID,  IMG, 2, CardType.POWER,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
        this.tags.add(SkyStriker);
        this.tags.add(SkyStrikerAce);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -99), -99));
        this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, 99), 99, true, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ChangeStanceAction(new KainaStance()));
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }

//    public AbstractCard makeCopy() {
//        return new Kaina();
//    }

    static {
         cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}

