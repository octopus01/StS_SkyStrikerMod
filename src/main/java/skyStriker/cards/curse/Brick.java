//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.cards.curse;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.DefaultMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.CommonSpell.PotOfGreed;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.DefaultMod.getModID;
import static skyStriker.DefaultMod.makeCardPath;
public class Brick extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Brick.class.getSimpleName());
    public static final String IMG = makeCardPath("Brick.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public Brick() {
        super(ID, IMG, -2,  CardType.CURSE, CardColor.CURSE, CardRarity.RARE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new Brick();
    }
}
