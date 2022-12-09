package skyStriker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import java.util.Iterator;

import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SpellCard;

public class SkyStrikerDowngradeAction extends AbstractGameAction {
    public SkyStrikerDowngradeAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.DowngradeSkyStrikerSpellCards(p.hand);
            this.DowngradeSkyStrikerSpellCards(p.drawPile);
            this.DowngradeSkyStrikerSpellCards(p.discardPile);
            this.DowngradeSkyStrikerSpellCards(p.exhaustPile);
            this.isDone = true;
        }

    }

    private void DowngradeSkyStrikerSpellCards(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.upgraded && c.hasTag(SkyStriker)) {
                AbstractDynamicCard s=(AbstractDynamicCard)c;
                s.downgrade();
                s.initializeDescription();
            }
        }
    }
}
