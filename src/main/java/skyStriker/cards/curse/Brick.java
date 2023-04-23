//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.cards.curse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.SkyStrikerMod;
import skyStriker.cards.AbstractDynamicCard;

import static skyStriker.SkyStrikerMod.makeCardPath;

public class Brick extends AbstractDynamicCard {
    public static final String ID = SkyStrikerMod.makeID(Brick.class.getSimpleName());
    public static final String IMG = makeCardPath("Brick.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public Brick() {
        super(ID, IMG, -2,  CardType.CURSE, CardColor.CURSE, CardRarity.RARE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
    }

}
