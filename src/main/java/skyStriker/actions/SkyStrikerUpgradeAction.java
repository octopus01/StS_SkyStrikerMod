package skyStriker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skyStriker.cards.SkyStrikerCardTags;

import java.util.Iterator;

import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SpellCard;

public class SkyStrikerUpgradeAction extends AbstractGameAction {
    public SkyStrikerUpgradeAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.upgradeSkyStrikerSpellCards(p.hand);
            this.upgradeSkyStrikerSpellCards(p.drawPile);
            this.upgradeSkyStrikerSpellCards(p.discardPile);
            this.upgradeSkyStrikerSpellCards(p.exhaustPile);
            this.isDone = true;
        }

    }

    private void upgradeSkyStrikerSpellCards(CardGroup cardGroup) {
        Iterator var2 = cardGroup.group.iterator();
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade()&&c.hasTag(SkyStriker)&&c.hasTag(SpellCard)) {
                if (cardGroup.type == CardGroupType.HAND) {
                    c.superFlash();
                }
                c.upgrade();

            }
        }
    }
}
