package skyStriker.cards.SkyStriker;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import skyStriker.DefaultMod;
import skyStriker.actions.TagFromDiscardPileToHandAction;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.KainaStance;

import static skyStriker.DefaultMod.makeCardPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;

public class Kaina extends CustomCard {
    public static final String ID =  DefaultMod.makeID(Kaina.class.getSimpleName());
    private static final CardStrings cardStrings;
    public static final String IMG = makeCardPath("Kaina.png");

    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }
    public Kaina() {
        super(ID, cardStrings.NAME, IMG, 2, cardStrings.DESCRIPTION, CardType.SKILL,  TheSkyStriker.Enums.COLOR_GRAY, CardRarity.BASIC, CardTarget.ENEMY);
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

    public AbstractCard makeCopy() {
        return new Kaina();
    }

    static {
         cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}

