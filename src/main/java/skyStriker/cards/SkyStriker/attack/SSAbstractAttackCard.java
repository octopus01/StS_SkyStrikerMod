package skyStriker.cards.SkyStriker.attack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.characters.TheSkyStriker;

public abstract class SSAbstractAttackCard extends AbstractDynamicCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("skyStriker:CannotUse");

    public SSAbstractAttackCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        TheSkyStriker p1 = (TheSkyStriker) p;
        if(!p1.canAttack) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p1, m);
    }
}
