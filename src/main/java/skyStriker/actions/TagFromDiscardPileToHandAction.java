

package skyStriker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.Iterator;

public class TagFromDiscardPileToHandAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private int newCost;
    private boolean setCost;
    public static AbstractCard.CardTags cardTags;
    private AbstractPlayer p;

    public TagFromDiscardPileToHandAction(int numberOfCards, boolean optional, AbstractCard.CardTags cardTags1) {
        cardTags=cardTags1;
        this.newCost = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;;
        this.p = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;
        this.setCost = false;
    }

    public TagFromDiscardPileToHandAction(int numberOfCards,AbstractCard.CardTags cardTags1) {
        this(numberOfCards, false,cardTags1);
    }

    public TagFromDiscardPileToHandAction(int numberOfCards, int newCost) {
        this.newCost = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration =Settings.ACTION_DUR_MED;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = false;
        this.setCost = true;
        this.newCost = newCost;
    }

    public void update() {
        AbstractCard card;
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator var5 = this.p.discardPile.group.iterator();

            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                if (c.hasTag(cardTags)) {
                    tmp.addToRandomSpot(c);
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.discardPile.removeCard(card);
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }
                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.numberOfCards, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    card = (AbstractCard)var1.next();
                    if(card.hasTag(cardTags)){
                        card.unhover();
                        if (this.p.hand.size() == 10) {
                            
                            this.p.createHandIsFullDialog();
                        } else {
                            this.p.discardPile.removeCard(card);
                            this.p.hand.addToTop(card);
                        }

                        this.p.hand.refreshHandLayout();
                        this.p.hand.applyPowers();
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }


    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
